package authoringApp;

public class UserInputInteraction extends Interaction {
	
	
	public UserInputInteraction() {
		super("Wait for user input");
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionTypes.get(Interaction.InteractionType.USER_INPUT);
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~user-input";
		return base;
	}

}
