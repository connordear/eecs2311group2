package authoringApp;

public class ResetButtonInteraction extends Interaction {

	public ResetButtonInteraction() {
		super("Reset Buttons");
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionTypes.get(Interaction.InteractionType.RESET_BUTTONS);
	}
	
	@Override
	public String generateScenarioText() {
		return "/~reset-buttons";
	}

}
