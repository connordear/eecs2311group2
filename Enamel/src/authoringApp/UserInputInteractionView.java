package authoringApp;

import java.awt.GridBagConstraints;

public class UserInputInteractionView extends InteractionView {

	private UserInputInteraction userInputModel;
	
	public UserInputInteractionView(UserInputInteraction ui) {
		super(ui.getInteraction());
		this.userInputModel = ui;
	}

	@Override
	public String getTitle() {
		return this.userInputModel.getTitle();
	}

	@Override
	public boolean setTitle(String s) {
		return this.userInputModel.setTitle(s);
	}

	@Override
	public String getType() {
		return this.userInputModel.getType();
	}

}
