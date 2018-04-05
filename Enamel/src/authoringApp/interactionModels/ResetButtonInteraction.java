package authoringApp.interactionModels;


public class ResetButtonInteraction extends Interaction {

	public ResetButtonInteraction() {
		super("Reset Buttons", 0 , 0);
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.RESET_BUTTONS.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		return "/~reset-buttons";
	}

}