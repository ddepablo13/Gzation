import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

// Ensure that Plotter extends NoApplet.
public abstract class Plotter extends NoApplet {
    protected Dimension dim;
    protected Color color = Color.black;
    protected int xorigin, yorigin;
    protected int xratio = 100, yratio = 100;

    // Default constructor
    public Plotter() {
        super();
    }

    // Constructor that accepts command-line arguments
    public Plotter(String[] args) {
        super(args);
    }

    // Initialization method
    @Override
    public void init() {
        super.init(); // Call NoApplet's init method
        this.dim = getSize(); // Make sure to use 'this' to refer to the instance variable

        // It's good practice to ensure that the size is non-zero
        if (dim.width == 0 || dim.height == 0) {
            this.dim = new Dimension(800, 600); // Provide a default size
        }

        // The rest of the attributes are initialized based on parameters or defaults
        String att = getParameter("xratio");
        this.xratio = (att != null) ? Integer.parseInt(att) : this.xratio;

        att = getParameter("yratio");
        this.yratio = (att != null) ? Integer.parseInt(att) : this.yratio;

        att = getParameter("xorigin");
        this.xorigin = (att != null) ? Integer.parseInt(att) : this.dim.width / 2;

        att = getParameter("yorigin");
        this.yorigin = (att != null) ? Integer.parseInt(att) : this.dim.height / 2;

        // Ensure that the component has a preferred size set, which influences layout decisions
        setPreferredSize(this.dim);
    }

    // This method needs to be present to satisfy the abstract method in NoApplet
    // but it doesn't need to do anything if you're not using an Applet context
    @Override
    public void start() {
        // Empty if not needed
    }

    // The actual paint method in AWT is paint(Graphics g), not paintComponent(Graphics g)
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the superclass method to ensure proper painting sequence
        drawCoordinates(g); // Draw the coordinate system
    }

    // Method to plot a function given a FunctionInterface
    // This method doesn't need to be abstract anymore because it's using FunctionInterface
    protected void plotFunction(Graphics g, FunctionInterface function) {
        // Ensure we're using the correct color for plotting
        g.setColor(this.color);
        // Plot points for each pixel across the width of the component
        for (int px = 0; px < this.dim.width; px++) {
            double x = (px - this.xorigin) / (double)this.xratio;
            double y = function.apply(x);
            int py = this.yorigin - (int) (y * this.yratio);
            // Plot a small rectangle (or point) at the function value location
            g.fillRect(px, py, 1, 1); // Changed from fillOval to fillRect for a clearer plot point
        }
    }

    // Method to draw the coordinate system
    protected void drawCoordinates(Graphics g) {
        // Clear the background
        g.setColor(Color.white);
        g.fillRect(0, 0, this.dim.width, this.dim.height);

        // Draw the axes
        g.setColor(this.color);
        g.drawLine(0, this.yorigin, this.dim.width, this.yorigin);
        g.drawLine(this.xorigin, 0, this.xorigin, this.dim.height);

        // Draw the axis labels
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        int px, py;
        int i = 1;
        py = this.yorigin + 12;
        g.drawString("0", this.xorigin + 2, py);
        for (px = this.xorigin + this.xratio; px < this.dim.width; px += this.xratio) {
            g.drawString(Integer.toString(i++), px - 2, py);
            g.drawLine(px, this.yorigin - 2, px, this.yorigin + 2);
        }

        i = -1;
        for (px = this.xorigin - this.xratio; px >= 0; px -= this.xratio) {
            g.drawString(Integer.toString(i--), px - 2, py);
            g.drawLine(px, this.yorigin - 2, px, this.yorigin + 2);
        }

        i = 1;
        px = this.xorigin + 4;
        for (py = this.yorigin - this.yratio; py >= 0; py -= this.yratio) {
            g.drawString(Integer.toString(i++), px, py + 4);
            g.drawLine(this.xorigin - 2, py, this.xorigin + 2, py);
        }

        i = -1;
        for (py = this.yorigin + this.yratio; py < this.dim.height; py += this.yratio) {
            g.drawString(Integer.toString(i--), px, py + 4);
            g.drawLine(this.xorigin - 2, py, this.xorigin + 2, py);
        }
    }
}
