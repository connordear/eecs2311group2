package enamel;

import java.io.File;

import javax.swing.JFileChooser;


public class ToyAuthoring {

    public static void main(String[] args) { 	  	
    	    ScenarioParser s = new ScenarioParser(true);
    	    
    	    //Create a file chooser
    	    final JFileChooser fc = new JFileChooser();
    	    fc.getAccessibleContext().setAccessibleName("Let's choose a file!");
    	    fc.getAccessibleContext().setAccessibleDescription("YUP CHOOSE");
    	    //In response to a button click:
    	    int returnVal = fc.showOpenDialog(null);

    	       if (returnVal == JFileChooser.APPROVE_OPTION) {
    	           String fileName = fc.getSelectedFile().getPath();
    	           s.setScenarioFile(fileName);
    	       }
    	    
    	    //s.setScenarioFile("FactoryScenarios/Scenario_" + 1 + ".txt");
    }
}
