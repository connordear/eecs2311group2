package authoringApp;

public class CellClearInteraction extends Interaction {
	
	int cellNumber;
	
	public CellClearInteraction(int cellNumber) {
		super("Clear Cell " + cellNumber);
		this.cellNumber = cellNumber;
	}
	
	public CellClearInteraction() {
		super("Clear Cell " + 0);
		this.cellNumber = 0;
	}
	
	
	public int getCellNumber() {
		return this.cellNumber;
	}

	public boolean setCellNumber(int cellNumber) {
		if (cellNumber < 0) {
			return false;
		}
		this.cellNumber = cellNumber;
		return true;
	}
	
	@Override
	public String getType() {
		return Interaction.CLEAR_BRAILLE;
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~disp-cell-clear:";
		return base + this.getCellNumber();
	}

}
