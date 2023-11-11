public class SineCosinePlotter extends MultiPlotter {

    @Override
    protected void initMultiPlotter() {
        addFunction(new SineFunction());
        addFunction(new CosineFunction());
    }
}
