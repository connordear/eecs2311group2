package authoringApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Scenario {
	
	private static int counter = 0;
	
	public InteractionList interactionList;
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
	
	public File getFile() throws URISyntaxException {
		URL url = this.getClass().getResource("FactoryScenarios/" + this.getTitle() + ".txt");
		return new File(url.toURI());
	}
	
	/*
	 * Add a new Interaction to the scenario
	 */
	public void addInteraction(Interaction e) {
		this.interactionList.add(e);
	}
	
	/*
	 * A way to convert the Scenario Text file to an Scenario object
	 */
	public Scenario(File scenarioTextFile) {
		this();
		InteractionList newList = new InteractionList();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(scenarioTextFile));
			String line = reader.readLine();
			// We know this first line contains the number of cells
			this.setCells(Integer.parseInt(line.substring(5)));
			reader.readLine();
			// 2nd line contains number of buttons
			this.setButtons(Integer.parseInt(line.substring(5)));
			reader.readLine();
			// Third line contains Title
			this.setTitle(line);
			// now go through the rest of the lines
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("/~")) {
					// Then we have a command
					// Check for Braille Interaction
					if(line.startsWith("/~disp-cell-pins:")) {
						newList.add(new DisplayBrailleInteraction(Integer.parseInt(line.substring(17, 18)), line.substring(19)));
						System.out.println("FOUND DISP");
					}
					// Check for Pause Interaction
					if (line.startsWith("/~pause:")) {
						newList.add(new PauseInteraction(Integer.parseInt(line.substring(8))));
						System.out.println("FOUND PAUSE");
					}
					// Check for Reset Button Interaction
					if (line.startsWith("/~reset-buttons")) {
						newList.add(new ResetButtonInteraction());
					}
					// Check for Cell Clear Interaction
					if (line.startsWith("/~disp-cell-clear")) {
						newList.add(new CellClearInteraction(Integer.parseInt(line.substring(18))));
					}
				} else {
						System.out.println("NEW READ");
						ReadInteraction newRead = new ReadInteraction();
						newRead.setData(line);
						newList.add(newRead);
						
				
					
				}
			}
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.interactionList = newList;
	}
	/*
	 * Generate the scenario text
	 */
	public void generateScenarioText() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("FactoryScenarios/" + this.getTitle() + ".txt"));
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
