import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.*;

public class Main extends PApplet {
    private CircuitBoard board;

    private int prevX = 0;
    private int prevY = 0;

    private final int circleSize = 20;

    private Gate selectedGate = null;
    private Node selectedNode = null;
    private Point startingPoint = null;
    private Point offset = null;

    public void settings() {
        board = new CircuitBoard();
        board.AddInputNode();
        board.AddInputNode();
        board.AddOutputNode();
        AND and = new AND();
        AND and2 = new AND();
        board.gates.add(and);
        board.gates.add(and2);

        size(1600, 900);

    }

    public void draw() {
        background(0xB0B0B0);

        strokeWeight(3);
        for (Gate g : board.gates) {

            fill(55, 102, 184);
            rect(g.position.x, g.position.y, g.width, g.height);


            fill(255, 255, 255);
            for (int i = 0; i < g.inputs.length; i++) {
                //DONT REMOVE! VERY MATH3MATICAL
                ellipse(g.position.x,
                        g.position.y + g.height / 2 - 15 * (g.inputs.length - 1) + i * 30, 20, 20);
            }

            for (int i = 0; i < g.outputs.length; i++) {
                //DONT REMOVE! VERY MATH3MATICAL
                ellipse(g.position.x + g.width,
                        g.position.y + g.height / 2 - 15 * (g.outputs.length - 1) + i * 30, 20, 20);
            }
            fill(0);
            textAlign(CENTER, CENTER);
            text(g.getName(), g.position.x + g.width / 2, g.position.y + g.height / 2);
        }

        for (Gate g : board.gates) {
            strokeWeight(7);
            for (OutputNode on : g.outputs) {
                for (Connection c : on.outputConnections) {
                    Gate fromGate = c.from;
                    Gate toGate = c.to;
                    Point fromPoint = new Point(fromGate.position.x + fromGate.width,
                            fromGate.position.y + fromGate.height / 2 - 15 * (fromGate.outputs.length - 1) + c.fromIndex * 30);
                    Point toPoint = new Point(toGate.position.x,
                            toGate.position.y + toGate.height / 2 - 15 * (toGate.inputs.length - 1) + c.toIndex * 30);
                    //Draw line of connection
                    fill(c.getValue() ? 0xff1111 : 0x505050);
                    line(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
                }
            }
        }
        if (selectedNode != null) {
            Point point;
            if (selectedNode.side == Node.Side.Input) {
                point = new Point(selectedNode.gate.position.x,
                        selectedNode.gate.position.y + selectedNode.gate.height / 2 - 15 * (selectedNode.gate.inputs.length - 1) + selectedNode.index * 30);
            } else {
                point = new Point(selectedNode.gate.position.x + selectedNode.gate.width,
                        selectedNode.gate.position.y + selectedNode.gate.height / 2 - 15 * (selectedNode.gate.outputs.length - 1) + selectedNode.index * 30);
            }
            fill(0x00ff00);
            ellipse(point.x, point.y, circleSize, circleSize);
        }

        frameRate(60);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Node n = getSelectedNode(event.getX(), event.getY());

        if (selectedNode == null) {
            selectedNode = n;
        } else {
            if (n == null) {
                //If we dont click on another node after selecting the first one discard
                selectedNode = null;
                return;
            }

            Connection c;
            if (selectedNode.side == Node.Side.Output && n.side == Node.Side.Input) {
                c = new Connection(selectedNode.gate, selectedNode.index, n.gate, n.index);
                if (n.gate.inputs[n.index] == null) {
                    selectedNode.gate.outputs[selectedNode.index].outputConnections.add(c);
                    n.gate.inputs[n.index] = c;
                    println("Connected em");
                } else if (n.gate.inputs[n.index].equals(c)){
                    selectedNode.gate.outputs[selectedNode.index].outputConnections.remove(c);
                    n.gate.inputs[n.index] = null;
                    println("uhm no");
                }

            } else if (selectedNode.side == Node.Side.Input && n.side == Node.Side.Output) {
                c = new Connection(n.gate, n.index, selectedNode.gate, selectedNode.index);
                if (selectedNode.gate.inputs[selectedNode.index] == null) {
                    n.gate.outputs[n.index].outputConnections.add(c);
                    selectedNode.gate.inputs[selectedNode.index] = c;
                    println("Connected em");
                } else if (selectedNode.gate.inputs[selectedNode.index].equals(c)){
                    n.gate.outputs[n.index].outputConnections.remove(c);
                    selectedNode.gate.inputs[selectedNode.index] = null;
                    println("uhm no");
                }

            }

            selectedNode = null;
        }


    }

    public Node getSelectedNode(int x, int y) {
        int index = -1;
        Gate gate = null;
        boolean isInput = false;

        for (Gate g : board.gates) {
            for (int i = 0; i < g.inputs.length; i++) {
                //DONT REMOVE! VERY MATH3MATICAL
                int yPos = g.position.y + g.height / 2 - 15 * (g.inputs.length - 1) + i * 30;
                int distanceSquared = (x - g.position.x) * (x - g.position.x) + (y - yPos) * (y - yPos);
                if (distanceSquared <= circleSize * circleSize) {
                    index = i;
                    gate = g;
                    isInput = true;
                    println("here goes the left");
                    break;
                }
            }

            for (int i = 0; i < g.outputs.length && index == -1; i++) {
                //DONT REMOVE! VERY MATH3MATICAL
                int yPos = g.position.y + g.height / 2 - 15 * (g.inputs.length - 1) + i * 30;
                int distanceSquared = (x - (g.position.x + g.width)) * (x - (g.position.x + g.width))
                        + (y - yPos) * (y - yPos);
                if (distanceSquared <= circleSize * circleSize) {
                    index = i;
                    gate = g;
                    isInput = false;
                    println("here goes the right");
                    break;
                }
            }
        }
        if (index == -1) {
            return null;
        }
        println("i got a node");
        return new Node(index, gate, isInput ? Node.Side.Input : Node.Side.Output);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (selectedNode != null) {
            return;
        }

        selectedGate = getDraggedGate(event.getX(), event.getY());

        if (selectedGate != null && startingPoint == null) {
            startingPoint = new Point(event.getX(), event.getY());
            offset = new Point(event.getX() - selectedGate.position.x, event.getY() - selectedGate.position.y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        selectedGate = null;
        startingPoint = null;
        offset = null;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (event.getButton() != LEFT || selectedGate == null) {
            return;
        }
        int newX = Math.max(0, Math.min(width - 5, startingPoint.x - (startingPoint.x - event.getX()) - offset.x));
        int newY = Math.max(0, Math.min(height - 5, startingPoint.y - (startingPoint.y - event.getY()) - offset.y));
        selectedGate.position.setLocation(newX, newY);

    }

    public Gate getDraggedGate(int x, int y) {
        Gate gate = null;
        for (Gate g : board.gates) {
            if (x >= g.position.x && x <= g.position.x + g.width && y >= g.position.y && y <= g.position.y + g.height) {
                gate = g;
                break;
            }
        }
        return gate;
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
