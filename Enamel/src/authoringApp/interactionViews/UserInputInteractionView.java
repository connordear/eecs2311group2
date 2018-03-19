package authoringApp.interactionViews;

import java.awt.GridBagConstraints;

import authoringApp.interactionModels.UserInputInteraction;

public class UserInputInteractionView extends InteractionView {

	private UserInputInteraction userInputModel;
	
	public UserInputInteractionView(UserInputInteraction ui) {
		super(ui.getInteraction());
		this.userInputModel = ui;
	}

}
