package authoringApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Scenario {
	
	private static int counter = 0;
	
	protected InteractionList interactionList;
	protected String title;
	protected int cells;
	protected int buttons;
	private final int id;
	
	/*
	 * Default Constructor
	 * Sets title to Untitled_id, number of cells to one, and 4 buttons.
	 */
	public Scenario() {
		this("Untitled_" + counter, 1, 4);
	}
	
	/*
	 * All Constructor
	 * - sets interaction list to empty
	 */
	public Scenario(String title, int cells, int buttons) {
		setTitle(title);
		this.setButtons(buttons);
		this.setCells(cells);
		this.interactionList = new InteractionList();
		this.id = counter;
		Scenario.counter++;
	}
	
	/*
	 * Title Constructor
	 */
	public Scenario(String title) {
		this(title, 1, 4);
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
	 * Getter for number of cells
	 */
	
	public int getCells() {
		return this.cells;
	}
	
	/*
	 *  Setter for number of cells
	 *  @throws - IllegalArgumentException if # of cells is < 1
	 */
	public void setCells(int cells) {
		if (cells < 1) {
			throw new IllegalArgumentException();
		}
		this.cells = cells;
	}
	
	/*
	 * Get the number of buttons in this scenario
	 */
	public int getButtons() {
		return this.buttons;
	}
	
	/*
	 *  Set the number of buttons in this scenario
	 *  @throws new IllegalArgumentException if # of buttons < 1
	 */
	public void setButtons(int buttons) {
		if (buttons < 1) {
			throw new IllegalArgumentException();
		}
		this.buttons = buttons;
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
	 * A way to convert the Scenario Text file to an InteractionList object
	 * MAYBE A BAD IDEA? THIS SHOULD GENERATE A SCENARIO OBJECT -- THIS SHOULD PROBABLY BE A CONSTRUCTOR?
	 */
	public InteractionList generateInteractionList(File scenarioFile) {
		InteractionList newList = new InteractionList();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(scenarioFile));
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("/~")) {
					// Then we have a command
				} else {
					// We have just text.
				}
				reader.readLine();
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return newList;
	}
	/*
	 * Generate the scenario text
	 */
	public void generateScenarioText() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("FactoryScenarios/" + this.getTitle()));
		writer.write("Cell " + this.getCells() + "\n");
		writer.write("Button " + this.getButtons() + "\n");
		writer.write(this.getTitle() + "\n\n");
		for(Interaction i : this.interactionList.getList()) {
			String nextLine = i.generateScenarioText();
		    writer.append(nextLine + "\n");
		}
	    writer.close();
	}
	
}
