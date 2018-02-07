package authoringApp;


/*
 * Class is meant to be inherited and used to create the other Interaction classes
 */
public abstract class Interaction {
	
	private static int ID_COUNTER = 0;
	protected int id;
	protected String title;
	
	
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

}
