package authoringApp.interactionModels;

public class CellClearInteraction extends Interaction {
	
	private int cellNumber;

	public CellClearInteraction(int cellNumber, int numCells, int numButtons) {
		super("Clear Cell " + cellNumber, numCells, numButtons);
		this.cellNumber = cellNumber | 0;
	}
	
	
	public int getCellNumber() {
		return this.cellNumber;
	}

	public boolean setCellNumber(int cellNumber) {
		if (cellNumber < 0) {
			return false;
		}
		this.cellNumber = cellNumber;
		System.out.println("Changed to " + cellNumber);
		return true;
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.CLEAR_BRAILLE.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~disp-cell-clear:";
		return base + this.getCellNumber();
	}

}
