package authoringApp;

import java.awt.GridBagConstraints;

public class UserInputInteractionView extends InteractionView {

	private UserInputInteraction userInputModel;
	
	public UserInputInteractionView(UserInputInteraction ui) {
		super(ui.getInteraction());
		this.userInputModel = ui;
	}

}
