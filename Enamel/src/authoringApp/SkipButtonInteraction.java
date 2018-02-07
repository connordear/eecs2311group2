package authoringApp;

public class SkipButtonInteraction extends Interaction {

	public String keyword;
	public int button;
	
	public SkipButtonInteraction(int button, String keyword) {
		super();
		this.setButton(button);
		this.setKeyword(keyword);
	}
	
	public void setButton(int button) {
		// No way to verify which button they are pressing?
		this.button = button;
	}
	
	public int getButton() {
		return this.button;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~skip-button:";
		return base + this.getButton() + " " + this.getKeyword();
	}

}
