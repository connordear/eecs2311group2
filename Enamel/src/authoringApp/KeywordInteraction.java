package authoringApp;

public class KeywordInteraction extends Interaction {

	public String keyword;
	
	public KeywordInteraction(String keyword) {
		super();
		this.keyword = keyword;
	}
	
	public KeywordInteraction() {
		this("UNDEFINED");
	}
	
	public void setKeyword(String newKeyword) {
		this.keyword = newKeyword;
	}
	
	public String getKeyword() {
		return this.keyword;
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~";
		return base + this.getKeyword();
	}

}
