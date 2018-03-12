package authoringApp;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Class is meant to be inherited and used to create the other Interaction classes
 */
public abstract class Interaction {
	
	private static int id_counter = 0;
	private int id;
	private String title;
	
	public enum InteractionType {
		READ("Read"), VOICE("Voice"), DISPLAY_BRAILLE("Display braille"),
		KEYWORD("Keyword"), PAUSE("Pause"), SKIP_BUTTON("Skip button"),
		USER_INPUT("User input"), RESET_BUTTONS("Reset button"), CLEAR_BRAILLE("Clear braille");
		
		private String description;
		InteractionType(String desc) {
			this.description = desc;
		}
		public String getDescription() {
			return this.description;
		}
	}
	
	public Interaction() {
		this("Untitled");
	}
	
	public Interaction(String title) {
		this.title = title;
		this.id = id_counter;
		id_counter++;
	}
	
	public boolean setTitle(String newTitle) {
		this.title = newTitle;
		return (newTitle != null);
	}
	
	public String getTitle() {
		return this.title;
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
