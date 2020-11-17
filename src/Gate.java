import java.awt.*;
import java.util.ArrayList;

public abstract class Gate {

    private String name;

    protected Connection[] inputs;
    protected OutputNode[] outputs;

    public int width = 60;
    public int height;

    public Point position = new Point(0,0);

    public Gate(String name, int inputSize, int outputSize) {
        this.name = name;
        inputs = new Connection[inputSize];
        outputs = new OutputNode[outputSize];
        for (int i = 0; i < outputSize; i++) {
            outputs[i] = new OutputNode();
        }
        height = 30 * Math.max(inputs.length, outputs.length);
    }

    public int getInputSize() {
        return this.inputs.length;
    }

    public int getOutputSize() {
        return this.outputs.length;
    }

    public void compute() {};

    public String getName() {
        return this.name;
    }
}
