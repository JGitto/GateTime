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
        //inputs.add(new Connection(null, 3, null, 3));
    }

    public void AddOutputNode() {
        outputNodes.add(new OutputNode());
    }

    public Chip compressIntoChip(String name) {
        return new Chip(name, inputs.size(), outputNodes.size(), gates);
    }
}
