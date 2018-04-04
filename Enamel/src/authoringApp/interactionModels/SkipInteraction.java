package authoringApp.interactionModels;

public class SkipInteraction extends Interaction {
		
	private String skipWord;
	
	public SkipInteraction(String skipWord, int cells, int buttons) {
		super("Skip", cells, buttons);
		this.skipWord = skipWord;
	}

	public String getSkipWord() {
		return new String(this.skipWord);
	}
	
	public void setSkipWord(String s) {
		this.skipWord = s;
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~skip:";
		return base + this.skipWord;
	}

	@Override
	public String getType() {
		return Interaction.InteractionType.SKIP.getDescription();
	}

}