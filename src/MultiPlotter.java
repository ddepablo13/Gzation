import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MultiPlotter extends Plotter {
    private final List<FunctionInterface> functions = new ArrayList<>();

    public MultiPlotter() {
        super();
    }

    // Override init to perform any necessary initialization
    @Override
    public void init() {
        super.init(); // Call the base class init
        initMultiPlotter(); // This method will set up the functions
    }

    // This method is intended to be overridden by subclasses to add functions
    protected void initMultiPlotter() {
        // By default, nothing is done here. Subclasses should add functions.
    }

    // Add a function to the plotter
    public void addFunction(FunctionInterface function) {
        functions.add(function);
    }

    // Override paint to draw all functions
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the base class paint method to set up the drawing
        // Now draw each function
        for (FunctionInterface function : functions) {
            plotFunction(g, function);
        }
    }
}
