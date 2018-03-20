package authoringApp.interactionModels;

import authoringApp.Interaction;
import authoringApp.Interaction.InteractionType;

public class UserInputInteraction extends Interaction {
	
	
	public UserInputInteraction() {
		super("Wait for user input");
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
