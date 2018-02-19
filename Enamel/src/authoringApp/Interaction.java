package authoringApp;

import java.util.HashMap;
import java.util.Map;

/*
 * Class is meant to be inherited and used to create the other Interaction classes
 */
public abstract class Interaction {
	
	private static int id_counter = 0;
	private int id;
	private String title;
	protected enum InteractionType {
		READ, VOICE, DISPLAY_BRAILLE,
		KEYWORD, PAUSE, SKIP_BUTTON,
		USER_INPUT, RESET_BUTTONS, CLEAR_BRAILLE
	}
	
	protected static final Map<InteractionType, String> InteractionTypes = createMap();
	private static Map<InteractionType, String> createMap() {
		Map<InteractionType, String> map = new HashMap<InteractionType, String>();
		map.put(InteractionType.READ, "READ");
		map.put(InteractionType.VOICE, "VOICE");
		map.put(InteractionType.DISPLAY_BRAILLE, "DISPLAY BRAILLE");
		map.put(InteractionType.KEYWORD, "KEYWORD");
		map.put(InteractionType.PAUSE, "PAUSE");
		map.put(InteractionType.SKIP_BUTTON, "SKIP BUTTON");
		map.put(InteractionType.USER_INPUT, "USER INPUT");
		map.put(InteractionType.RESET_BUTTONS, "RESET BUTTONS");
		map.put(InteractionType.CLEAR_BRAILLE, "CLEAR BRAILLE");
		return map;
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
		return this.getType();
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
