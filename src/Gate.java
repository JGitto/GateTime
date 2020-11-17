import java.util.ArrayList;

public abstract class Gate {

    private String name;

    protected Connection[] inputs;
    protected OutputNode[] outputs;

    public Gate(String name, int inputSize, int outputSize) {
        this.name = name;
        inputs = new Connection[inputSize];
        outputs = new OutputNode[outputSize];
    }

    public int getInputSize() {
        return this.inputs.length;
    }

    public int getOutputSize() {
        return this.outputs.length;
    }

    public abstract void compute();

    public String getName() {
        return this.name;
    }
}
