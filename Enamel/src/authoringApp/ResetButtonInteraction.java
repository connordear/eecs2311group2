package authoringApp;

public class ResetButtonInteraction extends Interaction {

	public ResetButtonInteraction() {
		super("Reset Buttons");
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
