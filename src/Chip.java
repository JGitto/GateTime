import java.util.ArrayList;

public class Chip extends Gate {

    public ArrayList<Gate> gates;

    public Chip(String name, int inputSize, int outputSize, ArrayList<Gate> gates) {
        super(name, inputSize, outputSize);
        this.gates = gates;
    }

    @Override
    public void compute() {}
}
