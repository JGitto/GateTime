import java.util.ArrayList;

public class CircuitBoard {
    public ArrayList<Gate> gates;
    public ArrayList<Connection> inputs;
    public ArrayList<OutputNode> outputNodes;

    public CircuitBoard() {
        this.gates = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.outputNodes = new ArrayList<>();
    }

    public void AddInputNode() {
        inputs.add(new Connection(null, null));
    }

    public void AddOutputNode() {
        outputNodes.add(new OutputNode());
    }
}
