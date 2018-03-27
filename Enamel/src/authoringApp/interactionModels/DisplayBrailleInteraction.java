package authoringApp.interactionModels;

import java.util.HashMap;

public class DisplayBrailleInteraction extends Interaction {
	
	/*
	 * Pins are stored as a STRING representation of the binary.
	 * Cell number is a 0 based index for which cell to display the braille on.
	 */
	private String pins;
	private boolean[] pinsArray;
	private int cellNumber;
	private static HashMap<Character, String> alphabet = new HashMap<Character, String>();
	
	public DisplayBrailleInteraction(int cellNumber, String pins, int numCells, int numButtons) {
		super("Display Braille", numCells, numButtons);
		this.initializeAlphabet();
		this.setCellNumber(cellNumber);
		this.pinsArray = new boolean[8];
		if (pins.length() > 1) {
			for (int i = 0; i < pins.length(); i++) {
				if(pins.substring(i, i+1).equals("1")) {
					this.pinsArray[i] = true;
				} else {
					this.pinsArray[i] = false;
				}
				this.setPins(pins);
			}
		} else if (pins.length() == 1) {
			Character req = pins.charAt(0);
			String binRep = DisplayBrailleInteraction.alphabet.get(req);
			for (int i = 0; i < binRep.length(); i++) {
				if(binRep.substring(i, i+1).equals("1")) {
					this.pinsArray[i] = true;
				} else {
					this.pinsArray[i] = false;
				}
			}
			this.pins = binRep;
			this.setPins(this.pins);
		} else {
			this.setPins(pins);
		}
		
 	}
	
	
	private void initializeAlphabet() {
		alphabet.put('a', "10000000");
		alphabet.put('b', "11000000");
		alphabet.put('c', "10100000");
		alphabet.put('d', "10011000");
		alphabet.put('e', "10001000");
		alphabet.put('f', "11010000");
		alphabet.put('g', "11011000");
		alphabet.put('h', "11001000");
		alphabet.put('i', "01010000");
		alphabet.put('j', "01011000");
		alphabet.put('k', "10100000");
		alphabet.put('l', "11100000");
		alphabet.put('m', "10110000");
		alphabet.put('n', "10111000");
		alphabet.put('o', "10101000");
		alphabet.put('p', "11110000");
		alphabet.put('q', "11111000");
		alphabet.put('r', "11101000");
		alphabet.put('s', "01110000");
		alphabet.put('t', "01111000");
		alphabet.put('u', "10100100");
		alphabet.put('v', "11100100");
		alphabet.put('w', "01011100");
		alphabet.put('x', "10110100");
		alphabet.put('y', "10111100");
		alphabet.put('z', "10101100");
		alphabet.put(' ', "11111111");
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
