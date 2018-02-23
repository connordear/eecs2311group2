package authoringApp;

import java.io.File;
import java.io.IOException;

class ScenarioTest {
	
	public static void main(String[] args) throws IOException {
//		FIRST BIT IS TO SHOW HOW TO CREATE A SCENARIO OBJECT AND THEN A SCENARIO FILE FROM THAT OBJECT
		Scenario s = new Scenario("Lesson One");
		ReadInteraction firstRead = new ReadInteraction("Welcome");
		firstRead.setData("Hi and welcome to the first lesson.");		
		ReadInteraction secondRead = new ReadInteraction("Q1");
		secondRead.setData("Which buttons are currenlty being displayed?");
		DisplayBrailleInteraction firstDisplay = new DisplayBrailleInteraction(0, "11101100");
		PauseInteraction firstPause = new PauseInteraction(1);
		CellClearInteraction clear = new CellClearInteraction(0);
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
		s.getInteractionList().toString();
//		SECOND BIT IS TO SHOW HOW TO CREATE A SCENARIO OBJECT FROM A TEXT FILE (AND MAKE A NEW FILE WITH A DIFFERENT TITLE)
		Scenario s2 = new Scenario(new File("./FactoryScenarios/Scenario_1.txt"));
		s2.getInteractionList().toString();
		s2.setTitle("My Copy of Scenario_1");
		s2.generateScenarioText();
	}

}
