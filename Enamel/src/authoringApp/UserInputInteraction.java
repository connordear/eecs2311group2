package authoringApp;

public class UserInputInteraction extends Interaction {
	
	
	public UserInputInteraction() {
		super();
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~user-input";
		return base;
	}

}
