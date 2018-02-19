package authoringApp;

import javax.swing.JFrame;

public class ConfigurationViewTest {
	private static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame("Read Interaction Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        frame.setSize(500, 500);
        
        
		ReadInteraction r = new ReadInteraction();
		r.setTitle("Text One");
		r.setData("This is the data");
		ReadInteractionView rv = new ReadInteractionView(r);
		
		
		
        
        frame.getContentPane().add(rv.getInteractionView());
        //Display the window.
        
        frame.setVisible(true);
        
        
	}

}
