package authoringApp.interactionModels;

public class DisplayBrailleInteraction extends Interaction {
	
	/*
	 * Pins are stored as a STRING representation of the binary.
	 * Cell number is a 0 based index for which cell to display the braille on.
	 */
	private String pins;
	private boolean[] pinsArray;
	
	private int cellNumber;
	
	public DisplayBrailleInteraction(int cellNumber, String pins, int numCells, int numButtons) {
		super("Display Braille", numCells, numButtons);
		this.setPins(pins);
		this.setCellNumber(cellNumber);
		this.pinsArray = new boolean[8];
		for (int i = 0; i < pins.length(); i++) {
			if(pins.substring(i, i+1).equals("1")) {
				this.pinsArray[i] = true;
			} else {
				this.pinsArray[i] = false;
			}
		}
 	}
	

	/*
	 * Return the raised pins in this interaction
	 */
	public String getPins() {
		return this.pins;
	}
	
	public boolean[] getPinsArray() {
		return this.pinsArray;
	}
	
	
	public boolean getPin(int index) {
		return this.pinsArray[index];
	}
	
	public void setPin(int index, boolean val) {
		this.pinsArray[index] = val;
		this.pins = "";
		for (int i = 0; i < this.pinsArray.length; i++) {
			if (this.pinsArray[i]) {
				this.pins += "1";
			} else {
				this.pins += "0";
			}
		}
		System.out.println(this.pins);
	}
	
	/*
	 * Set the raised pins for this interaction
	 */
	public boolean setPins(String pins) {
		if (pins.length() < 8) {
			this.pins = "00000000";
			return false;
		}
		this.pins = pins;
		return true;
	}
	
	/*
	 * Set the cell number (which cell will display the interaction)
	 */
	public boolean setCellNumber(int num) {
		if (num < 0) {
			return false;
		}
		this.cellNumber = num;
		return true;
	}
	
	public int getCellNumber() {
		return this.cellNumber;
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.DISPLAY_BRAILLE.getDescription();
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
