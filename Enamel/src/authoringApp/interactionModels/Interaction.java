package authoringApp.interactionModels;

/*
 * Class is meant to be inherited and used to create the other Interaction classes
 */
public abstract class Interaction {
	
	private static int id_counter = 0;
	private int id;
	private int num_cells;
	private int num_buttons;
	private String title;
	
	public enum InteractionType {
		READ("Read"), VOICE("Voice"), DISPLAY_BRAILLE("Display braille"),
		KEYWORD("Keyword"), PAUSE("Pause"), SKIP_BUTTON("Skip button"),
		USER_INPUT("User input"), RESET_BUTTONS("Reset button"), CLEAR_BRAILLE("Clear braille"), SKIP("Skip");
		// No Question for now.
		
		private String description;
		InteractionType(String desc) {
			this.description = desc;
		}
		public String getDescription() {
			return this.description;
		}
	}
	
	public Interaction(String title, int cells, int buttons) {
		this.title = title;
		this.id = id_counter;
		this.num_cells = cells;
		this.num_buttons = buttons;
		id_counter++;
	}
	
	public boolean setTitle(String newTitle) {
		this.title = newTitle;
		return (newTitle != null);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getNumButtons() {
		return this.num_buttons;
	}
	
	public int getNumCells() {
		return this.num_cells;
	}
	
	@Override
	public String toString() {
		//return this.getType();
		return this.getTitle();
	}
	
	public int getId() {
		return this.id;
	}
	
	public Interaction getInteraction() {
		return this;
	}
	abstract public String generateScenarioText();
	
	abstract public String getType();
}
