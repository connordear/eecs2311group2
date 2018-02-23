package authoringApp;

public class ResetButtonInteractionView extends InteractionView {

	private ResetButtonInteraction rbiModel;
	
	public ResetButtonInteractionView(ResetButtonInteraction rbi) {
		super(rbi.getInteraction());
		this.rbiModel = rbi;
	}

	@Override
	public String getTitle() {
		return this.rbiModel.getTitle();
	}

	@Override
	public boolean setTitle(String s) {
		return this.rbiModel.setTitle(s);
	}

	@Override
	public String getType() {
		return this.rbiModel.getType();
	}

}
