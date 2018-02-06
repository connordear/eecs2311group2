package authoringApp;

public class CellClearInteraction extends Interaction {
	
	int cellNumber;
	
	public CellClearInteraction(int cellNumber) {
		super();
		this.cellNumber = cellNumber;
	}
	
	public CellClearInteraction() {
		super();
		this.cellNumber = 0;
	}
	
	public int getCellNumber() {
		return this.cellNumber;
	}
	
	@Override
	public String generateScenarioText() {
		String base = "/~disp-cell-clear:";
		return base + this.getCellNumber();
	}

}
