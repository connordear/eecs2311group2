package authoringApp;

import java.io.File;
import java.io.IOException;

class ScenarioTest {
	
	public static void main(String[] args) {
//		Scenario s = new Scenario("Lesson One");
//		ReadInteraction firstRead = new ReadInteraction("Welcome");
//		firstRead.setData("Hi and welcome to the first lesson.");		
//		ReadInteraction secondRead = new ReadInteraction("Q1");
//		secondRead.setData("Which buttons are currenlty being displayed?");
//		DisplayBrailleInteraction firstDisplay = new DisplayBrailleInteraction(0, "11101100");
//		PauseInteraction firstPause = new PauseInteraction(1);
//		ClearBrailleCellInteraction clear = new ClearBrailleCellInteraction(0);
//		s.addInteraction(firstDisplay);
//		s.addInteraction(firstRead);
//		s.addInteraction(secondRead);
//		s.addInteraction(firstPause);
//		s.addInteraction(clear);
//		try {
//			s.generateScenarioText();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		s.interactionList.toString();
		Scenario s2 = new Scenario(new File("./FactoryScenarios/Scenario_1.txt"));
		s2.interactionList.toString();
	}

}
