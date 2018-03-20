package authoringApp.interactionModels;

import authoringApp.Interaction;
import authoringApp.Interaction.InteractionType;

public class SkipButtonInteraction extends Interaction {

	public String keyword;
	public int button;
	
	public SkipButtonInteraction(int button, String keyword) {
		super("Skip Button");
		this.setButton(button);
		this.setKeyword(keyword);
	}

	
	public SkipButtonInteraction() {
		super("Skip Button");
		this.setButton(1);
		this.setKeyword("Skip");
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
	public String getType() {
		return Interaction.InteractionType.SKIP_BUTTON.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~skip-button:";
		return base + this.getButton() + " " + this.getKeyword();
	}
	

}
