package authoringApp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainView {
	private static JFrame frame;
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	private static JMenuItem newFile, openFile, saveFile, saveAsFile, exit;
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */	
    private static void createAndShowGUI() {
    	// Create and set up the window
    	menuBar = new JMenuBar();
    	fileMenu = new JMenu("File");
    	fileMenu.getAccessibleContext().setAccessibleDescription(
    			"File menu which contains options to create new scenario file or open an existing one.");
    	newFile = new JMenuItem("New");
    	openFile = new JMenuItem("Open");
    	saveFile = new JMenuItem("Save");
    	saveAsFile = new JMenuItem("Save As");
    	exit = new JMenuItem("Exit");
    	
    	menuBar.add(fileMenu);
    	
        frame = new JFrame("Braille Author");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
