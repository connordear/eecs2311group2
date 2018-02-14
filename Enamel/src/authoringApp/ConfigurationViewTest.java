package authoringApp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ConfigurationViewTest {
	private static JFrame frame;
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	private static JMenuItem newFile, openFile, saveFile, saveAsFile, exit;
	public static void main(String[] args) {

		ReadInteraction r = new ReadInteraction("Test");
		ReadInteractionView rv = new ReadInteractionView(r);
		rv.getInteractionView().setBounds(0, 0, 200, 200);
		rv.getInteractionView().setVisible(true);
		// menuBar = new JMenuBar();
    	fileMenu = new JMenu("File");
    	fileMenu.getAccessibleContext().setAccessibleDescription(
    			"File menu which contains options to create new scenario file or open an existing one.");
    	newFile = new JMenuItem("New");
    	openFile = new JMenuItem("Open");
    	saveFile = new JMenuItem("Save");
    	saveAsFile = new JMenuItem("Save As");
    	exit = new JMenuItem("Exit");
    	
//    	menuBar.add(fileMenu);
    	
        frame = new JFrame("Braille Author");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(rv.getInteractionView());        

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
		
	}

}
