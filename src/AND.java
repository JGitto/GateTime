public class AND extends Gate {

    public AND() {
        super("AND", 2, 1);
    }

    @Override
    public void compute() {
        outputs[0].setValue(inputs[0].getValue() && inputs[1].getValue());
    }
}
