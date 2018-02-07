package authoringApp;

public class UserInputInteraction extends Interaction {
	
	
	public UserInputInteraction() {
		super("Wait for user input");
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~user-input";
		return base;
	}

}
