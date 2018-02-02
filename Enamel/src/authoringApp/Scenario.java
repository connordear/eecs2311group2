package authoringApp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Scenario {
	
	private static int counter = 0;
	
	protected InteractionList interactionList;
	protected String title;
	private final int id;
	
	/*
	 * Default Constructor
	 * Sets title to Untitled_id
	 */
	public Scenario() {
		this("Untitled_" + counter);
	}
	
	/*
	 * Title Constructor
	 * - sets interaction list to empty
	 */
	public Scenario(String title) {
		setTitle(title);
		this.interactionList = new InteractionList();
		this.id = counter;
		Scenario.counter++;
	}
	
	/*
	 * Getter for this.title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/*
	 * Setter for this.title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * Getter for this.id
	 */
	public int getId() {
		return this.id;
	}
	
	/*
	 * Add a new Interaction to the scenario
	 */
	public void addInteraction(Interaction e) {
		this.interactionList.add(e);
	}
	
	/*
	 * Generate the scenario text
	 */
	public void generateScenarioText() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("FactoryScenarios/" + this.getTitle()));
		writer.write(this.getTitle() + "\n");
		for(Interaction i : this.interactionList.getList()) {
			String nextLine = i.generateScenarioText();
		    writer.append(nextLine + "\n");
		}
	    writer.close();
	}
}
