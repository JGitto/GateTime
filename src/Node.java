public class Node {

    public int index;
    public Gate gate;
    public Side side;

    public Node(int index, Gate gate, Side side) {
        this.index = index;
        this.gate = gate;
        this.side = side;
    }

    public enum Side {
        Input, Output;
    }
}

