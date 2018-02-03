package authoringApp;

public class DisplayBrailleInteraction extends Interaction {
	
	/*
	 * Pins are stored as a STRING representation of the binary.
	 */
	private String pins;
	
	public DisplayBrailleInteraction(String title, String pins) {
		super(title);
		this.pins = pins;
	}
	
	public DisplayBrailleInteraction(String pins) {
		this("", pins);
	}
	
	public DisplayBrailleInteraction() {
		this("", "00000000");
	}
	
	
	public String getPins() {
		return this.pins;
	}
	/*
	 * Need to output text in this format:/~disp-cell-pins:0 00011000
	 * @see authoringApp.Interaction#generateScenarioText()
	 */
	@Override
	public String generateScenarioText() {
		String base = "/~disp-cell-pins:0 ";
		return base +  this.getPins();
	}

}
