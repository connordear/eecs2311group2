package authoringApp;

public class UserInputInteraction extends Interaction {
	
	
	public UserInputInteraction() {
		super("Wait for user input");
	}
	
	@Override
	public String getType() {
		return Interaction.USER_INPUT;
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~user-input";
		return base;
	}

}
