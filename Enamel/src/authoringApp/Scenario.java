package authoringApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	 * Need to find a way to fix the default naming
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
	 * A way to convert the Scenario Text file to an Scenario object
	 * @throws - IllegalArgumentException if File isn't properly formatted.
	 */
	public Scenario(File scenarioTextFile) throws IllegalArgumentException{
		this();
		InteractionList newList = new InteractionList();
		BufferedReader reader;
		if (Scenario.isValidScenarioFile(scenarioTextFile)) {
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
						else if (line.startsWith("/~pause:")) {
							newList.add(new PauseInteraction(Integer.parseInt(line.substring(8))));
							System.out.println("FOUND PAUSE");
						}
						// Check for Reset Button Interaction
						else if (line.startsWith("/~reset-buttons:")) {
							newList.add(new ResetButtonInteraction());
						}
						// Check for Cell Clear Interaction
						else if (line.startsWith("/~disp-cell-clear:")) {
							newList.add(new CellClearInteraction(Integer.parseInt(line.substring(18))));
						}
						// Check for Skip Button Interaction
						else if (line.startsWith("/~skip-button:")) {
							newList.add(new SkipButtonInteraction(Integer.parseInt(line.substring(14, 15)), line.substring(16)));
						}
						// Check for User Input Interaction
						else if (line.startsWith("/~user-input")) {
							newList.add(new UserInputInteraction());
						}
						// Finally check for keyword
						else if(line.startsWith("/~")){
							newList.add(new KeywordInteraction(line.substring(2)));
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
		} else {
			throw new IllegalArgumentException("Not a valid file format");
		}
		this.interactionList = newList;
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

	public static boolean isValidScenarioFile(File f) {
		BufferedReader reader;
		boolean isValid = true;
		try {
			reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			// We know this first line should contain the number of cells
			if(!line.substring(0, 4).equals("Cell")) {
				System.out.println(line.substring(0, 4));
				System.out.println("Problem with Cell");
				isValid = false;
			}
			line = reader.readLine();
			// 2nd line should contain number of buttons
			if (!line.substring(0, 6).equals("Button")) {
				System.out.println("Problem with Button");
				isValid = false;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValid = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValid = false;
		}
		return isValid;
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
