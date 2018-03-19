package authoringApp;

public class ResetButtonInteractionView extends InteractionView {

	private ResetButtonInteraction rbiModel;
	
	public ResetButtonInteractionView(ResetButtonInteraction rbi) {
		super(rbi.getInteraction());
		this.rbiModel = rbi;
	}

}
