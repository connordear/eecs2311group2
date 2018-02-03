package authoringApp;

public class PauseInteraction extends Interaction {

	private int length;
	
	public PauseInteraction() {
		this("", 1);
	}
	
	public PauseInteraction(int length) {
		this("", length);
	}
	
	public PauseInteraction(String title, int length) {
		super(title);
		this.length = length;
	}
	public int getLength() {
		return this.length;
	}
	
	
	@Override
	public String generateScenarioText() {
		return "/~pause:" + this.length;
	}

}
