package authoringApp;


/*
 * Class is meant to be inherited and used to create the other Interaction classes
 */
public abstract class Interaction {
	
	private static int ID_COUNTER = 0;
	protected int id;
	protected String title;
	public static final String READ = "READ";
	public static final String VOICE = "VOICE";
	public static final String DISPLAY_BRAILLE = "DISPLAY BRAILLE";
	public static final String KEYWORD = "KEYWORD";
	public static final String PAUSE = "PAUSE";
	public static final String SKIP_BUTTON = "SKIP BUTTON";
	public static final String USER_INPUT = "USER INPUT";
	public static final String RESET_BUTTONS = "RESET BUTTONS";
	public static final String CLEAR_BRAILLE = "CLEAR BRAILLE";	
	public Interaction() {
		this("Untitled");
	}
	
	public Interaction(String title) {
		this.title = title;
		this.id = ID_COUNTER;
		ID_COUNTER++;
	}
	
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getId() {
		return this.id;
	}
	
	abstract public String generateScenarioText();
	
	abstract public String getType();
}
