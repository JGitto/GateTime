public class NOT extends Gate {

    public NOT() {
        super("NOT", 1, 1);
    }

    @Override
    public void compute() {
        outputs[0].setValue(!inputs[0].getValue());
    }
}