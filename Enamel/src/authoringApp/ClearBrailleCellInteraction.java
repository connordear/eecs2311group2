package authoringApp;

public class ClearBrailleCellInteraction extends Interaction {
	
	private int cellNumber;
	
	public ClearBrailleCellInteraction(int cellNumber) {
		super();
		this.setCellNumber(cellNumber);
	}
	
	public ClearBrailleCellInteraction() {
		this(0);
	}
	
	public void setCellNumber(int cellNumber) {
		if (cellNumber < 0) {
			throw new IllegalArgumentException();
		}
		this.cellNumber = cellNumber;
	}
	
	public int getCellNumber() {
		return this.cellNumber;
	}
	
	@Override
	public String generateScenarioText() {
		return "/~disp-cell-clear:" + this.getCellNumber();
	}

}
