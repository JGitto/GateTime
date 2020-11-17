import processing.core.PApplet;

public class Main extends PApplet {
    private CircuitBoard board;

    public void settings () {
        board = new CircuitBoard();
        board.AddInputNode();
        board.AddInputNode();
        board.AddOutputNode();
        AND and = new AND();
        and.inputs[0] = board.inputs.get(0);
        and.inputs[1] = board.inputs.get(1);
        and.outputs[0] = board.outputNodes.get(0);
        board.gates.add(and);

        size(200,200);
    }

    public void draw() {

    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }

}
