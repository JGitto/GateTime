public class Connection {

    public Gate from;
    public Gate to;

    private boolean value = false;

    public Connection(Gate from, Gate to) {
        this.from = from;
        this.to = to;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        to.compute();
    }
}
