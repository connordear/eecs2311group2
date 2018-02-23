package authoringApp;

public class PauseInteraction extends Interaction {

	private int length;
	
	public PauseInteraction() {
		this("Pause: 1s", 1);
	}
	
	public PauseInteraction(int length) {
		this("Pause: " + length + "s", length);
	}
	
	public PauseInteraction(String title, int length) {
		super(title);
		this.length = length;
	}
	public int getLength() {
		return this.length;
	}
	
	public void setLength(int s) {
		this.length = s;
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.PAUSE.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		return "/~pause:" + this.length;
	}

}
