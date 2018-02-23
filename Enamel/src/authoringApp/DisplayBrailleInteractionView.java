package authoringApp;

import java.awt.GridBagConstraints;

import javax.swing.JRadioButton;

public class DisplayBrailleInteractionView extends InteractionView {

	private JRadioButton leftOne;
	private JRadioButton rightOne;
	private JRadioButton leftTwo;
	private JRadioButton rightTwo;
	private JRadioButton leftThree;
	private JRadioButton rightThree;
	private JRadioButton leftFour;
	private JRadioButton rightFour;
	private DisplayBrailleInteraction dbiModel;
	
	
	public DisplayBrailleInteractionView(DisplayBrailleInteraction d) {
		super(d.getInteraction());
		GridBagConstraints c = super.c;
		this.dbiModel = d;
		
		this.leftOne = new JRadioButton();
		this.rightOne = new JRadioButton();
		this.leftTwo = new JRadioButton();
		this.rightTwo = new JRadioButton();
		this.leftThree = new JRadioButton();
		this.rightThree = new JRadioButton();
		this.leftFour = new JRadioButton();
		this.rightFour = new JRadioButton();
		
		c.gridy = 1;
		c.gridx = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		this.leftOne.setAlignmentX(0);
		super.addRow(this.leftOne, c);
		c.gridx = 2;
		super.addRow(this.rightOne, c);
		
		c.gridy = 2;
		c.gridx = 1;
		super.addRow(this.leftTwo, c);
		c.gridx = 2;
		super.addRow(this.rightTwo, c);
		
		c.gridy = 3;
		c.gridx = 1;
		super.addRow(this.leftThree, c);
		c.gridx = 2;
		super.addRow(this.rightThree, c);
		
		c.gridy = 4;
		c.gridx = 1;
		super.addRow(this.leftFour, c);
		c.gridx = 2;
		super.addRow(this.rightFour, c);
		
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setTitle(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
