public class Connection {

    public Gate from;
    public Gate to;
    public int fromIndex;
    public int toIndex;

    private boolean value = false;

    public Connection(Gate from, int fromIndex, Gate to, int toIndex) {
        this.from = from;
        this.to = to;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        to.compute();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Connection) {
            Connection c = (Connection) o;
            try {
                if (this.fromIndex == c.fromIndex
                        && this.toIndex == c.toIndex
                        && this.from.equals(c.from)
                        && this.to.equals(c.to)) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }
}
