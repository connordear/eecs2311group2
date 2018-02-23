package authoringApp;

public class EditorController implements EditorView {
	private EditorPane view;
	private Scenario model;
	
	public EditorController() {
	}
	
	public void setView(EditorPane view) {
		this.view = view;
	}
	
	public void setModel(Scenario model) {
		this.model = model;
	}
	
	public EditorPane getView() {
		return this.view;
	}
	
	public Scenario getModel() {
		return this.model;
	}

	public void listItemSelected(int selectedItemIndex) {
		Interaction currentInteraction = model.interactionList.get(selectedItemIndex);
		String interactionId = Integer.toString(currentInteraction.getId());
		this.view.showCard(interactionId);
	}

	public void showCard(String cardName) {
		this.view.showCard(cardName);
	}
	
	private Interaction getInteraction(int index) {
		Interaction i = null;
		switch (Interaction.InteractionType.values()[index]) {
			case READ:
				i = new ReadInteraction();
				break;
			case VOICE:
				// No VoiceInteraction yet?
				//i = new VoiceInteraction();
				break;
			case DISPLAY_BRAILLE:
				i = new DisplayBrailleInteraction();
				break;
			case KEYWORD:
				i = new KeywordInteraction();
				break;
			case PAUSE:
				i = new PauseInteraction();
				break;
			case SKIP_BUTTON:
				i = new SkipButtonInteraction();
				break;
			case USER_INPUT:
				i = new UserInputInteraction();
				break;
			case RESET_BUTTONS:
				i = new ResetButtonInteraction();
				break;
			case CLEAR_BRAILLE:
				i = new CellClearInteraction();
				break;
			default:
				break;
		}
		return i;
	}

	public void addInteraction(int selectedItemIndex) {
		Interaction i = getInteraction(selectedItemIndex);
		if (i != null) {
			this.model.addInteraction(i);
			this.view.addInteractionCard(i);
			this.view.showCard(Integer.toString(i.getId()));
		}
	}
	
	public void addInteraction(int selectedItemIndex, int insertIdx) {
		Interaction i = getInteraction(selectedItemIndex);
		if (i != null) {
			this.model.addInteraction(i, insertIdx);
			this.view.addInteractionCard(i);
		}
	}

	public void deleteInteraction(int selectedListItemIndex) {
		this.model.removeInteraction(selectedListItemIndex);
		//this.view.deleteInteractionCard((Interaction)this.model.getInteractionList().get(selectedListItemIndex));
	}

	public void moveUp(int itemIndex) {
		this.model.swapInteractionOrder(itemIndex, itemIndex-1);
	}

	public void moveDown(int itemIndex) {
		this.model.swapInteractionOrder(itemIndex, itemIndex+1);
	}
	
	public void createInteractionList() {
		this.view.createInteractionList(this.model.interactionList);
	}

	
}
