import java.util.ArrayList;

public class OutputNode {
    public ArrayList<Connection> outputConnections;

    public OutputNode() {
        this.outputConnections = new ArrayList<>();
    }

    public void setValue(boolean value) {
        for(Connection c : outputConnections) {
            c.setValue(value);
        }
    }
}
