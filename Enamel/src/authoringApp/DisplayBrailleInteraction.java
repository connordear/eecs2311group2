package authoringApp;

public class DisplayBrailleInteraction extends Interaction {
	
	/*
	 * Pins are stored as a STRING representation of the binary.
	 * Cell number is a 0 based index for which cell to display the braille on.
	 */
	private String pins;
	private int cellNumber;
	
	public DisplayBrailleInteraction(String title, int cellNumber, String pins) {
		super(title);
		this.setPins(pins);
		this.setCellNumber(cellNumber);
	}
	
	public DisplayBrailleInteraction(int cellNumber, String pins) {
		this("", cellNumber, pins);
	}
	
	/*
	 * Default Constructor, sets the 1st cell title to empty, the raised buttons to 00000000
	 */
	public DisplayBrailleInteraction() {
		this("", 0, "00000000");
	}
	
	/*
	 * Return the raised pins in this interaction
	 */
	public String getPins() {
		return this.pins;
	}
	
	/*
	 * Set the raised pins for this interaction
	 */
	public void setPins(String pins) {
		this.pins = pins;
	}
	
	/*
	 * Set the cell number (which cell will display the interaction)
	 */
	public void setCellNumber(int num) {
		if (num < 0) {
			throw new IllegalArgumentException();
		}
		this.cellNumber = num;
	}
	
	public int getCellNumber() {
		return this.cellNumber;
	}
	
	/*
	 * Need to output text in this format:/~disp-cell-pins:0 00011000
	 * @see authoringApp.Interaction#generateScenarioText()
	 */
	@Override
	public String generateScenarioText() {
		String base = "/~disp-cell-pins:";
		return base + this.getCellNumber() + " " + this.getPins();
	}

}
