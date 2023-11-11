import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Create an instance of your plotter
        SineCosinePlotter plotter = new SineCosinePlotter();

        // Initialize the plotter (size, functions, etc.)
        plotter.init();

        // Set up the main window (JFrame)
        JFrame frame = new JFrame("Function Plotter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(plotter); // Add the plotter to the frame
        frame.setSize(800, 600); // Set the window size
        frame.setVisible(true); // Make the window visible
    }
}
