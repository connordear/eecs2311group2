package authoringApp.interactionModels;


public class KeywordInteraction extends Interaction {

	private String keyword;
	
	public KeywordInteraction(String keyword) {
		super("Keyword Interaction", 0, 0);
		this.keyword = keyword;
	}
	
	public KeywordInteraction() {
		this("UNDEFINED");
	}
	
	public void setKeyword(String newKeyword) {
		this.keyword = newKeyword;
	}
	
	public String getKeyword() {
		return new String(this.keyword);
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.KEYWORD.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~";
		return base + this.getKeyword();
	}

}
