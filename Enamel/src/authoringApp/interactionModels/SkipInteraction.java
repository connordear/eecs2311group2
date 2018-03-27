package authoringApp.interactionModels;

public class SkipInteraction extends Interaction {
		
	private String skipWord;
	
	public SkipInteraction(String title, String skipWord, int cells, int buttons) {
		super(title, cells, buttons);
		this.skipWord = skipWord;
	}

	@Override
	public String generateScenarioText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
