package enamel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ToyAuthoring {

    public static void main(String[] args) { 	  	
    	    ScenarioParser s = new ScenarioParser(true);
    	    
    	    //Create a file chooser and set dialog box name (screen reader seems to only read dialog box name)
    	    final JFileChooser fc = new JFileChooser();
    	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
    	    	    "Text File", "txt");
    	    fc.setFileFilter(filter);
    	    fc.setDialogTitle("Open Scenario File");
    	    
    	    // Set accessible descriptions for screen reader (screen reader is not reading these)
    	    fc.getAccessibleContext().setAccessibleName("File chooser dialog box");
    	    fc.getAccessibleContext().setAccessibleDescription("Please choose a scenario file from this file dialog box.");
    	    
    	    int returnVal = fc.showOpenDialog(null);

    	    if (returnVal == JFileChooser.APPROVE_OPTION) {
    	    	String fileName = fc.getSelectedFile().getPath();
    	    	
    	    	// Call to begin drawing UI
    	        s.setScenarioFile(fileName);
    	    }
    	    
    	    //s.setScenarioFile("FactoryScenarios/Scenario_" + 1 + ".txt");
    }
}
