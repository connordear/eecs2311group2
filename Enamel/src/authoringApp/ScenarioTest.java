package authoringApp;

import java.io.IOException;

class ScenarioTest {
	
	public static void main(String[] args) {
		Scenario s = new Scenario("Lesson One");
		ReadInteraction firstRead = new ReadInteraction("Welcome");
		firstRead.setData("Hi and welcome to the first lesson.");		
		ReadInteraction secondRead = new ReadInteraction("Q1");
		secondRead.setData("Which buttons are currenlty being displayed?");
		DisplayBrailleInteraction firstDisplay = new DisplayBrailleInteraction("11101100", 0);
		PauseInteraction firstPause = new PauseInteraction(1);
		ClearBrailleCellInteraction clear = new ClearBrailleCellInteraction(0);
		s.addInteraction(firstDisplay);
		s.addInteraction(firstRead);
		s.addInteraction(secondRead);
		s.addInteraction(firstPause);
		s.addInteraction(clear);
		try {
			s.generateScenarioText();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
