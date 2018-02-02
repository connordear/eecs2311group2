package authoringApp;

import java.io.IOException;

class ScenarioTest {
	
	public static void main(String[] args) {
		Scenario s = new Scenario("Lesson One");
		ReadInteraction firstRead = new ReadInteraction("Welcome");
		firstRead.setData("Hi and welcome to the first lesson.");
		
		ReadInteraction secondRead = new ReadInteraction("Q1");
		secondRead.setData("Which buttons are currenlty being displayed?");
		s.addInteraction(firstRead);
		System.out.println(s.interactionList.getList().get(0).generateScenarioText());
		s.addInteraction(secondRead);
		try {
			System.out.println("Should create a file");
			s.generateScenarioText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			System.out.println("We had a problem");
			e.printStackTrace();
		}
	}

}
