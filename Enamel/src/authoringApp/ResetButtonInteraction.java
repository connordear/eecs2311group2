package authoringApp;

public class ResetButtonInteraction extends Interaction {

	public ResetButtonInteraction() {
		super("Reset Buttons");
	}
	
	@Override
	public String getType() {
		return Interaction.RESET_BUTTONS;
	}
	
	@Override
	public String generateScenarioText() {
		return "/~reset-buttons";
	}

}
