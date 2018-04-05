package authoringApp.interactionModels;


public class UserInputInteraction extends Interaction {
	
	
	public UserInputInteraction() {
		super("Wait for user input", 0, 0);
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.USER_INPUT.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~user-input";
		return base;
	}

}