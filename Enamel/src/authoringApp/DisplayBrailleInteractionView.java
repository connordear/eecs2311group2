package authoringApp;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

public class DisplayBrailleInteractionView extends InteractionView {

	private JRadioButton[] buttonsArray;
	protected DisplayBrailleInteraction dbiModel;
	
	
	public DisplayBrailleInteractionView(DisplayBrailleInteraction d) {
		super(d.getInteraction());
		GridBagConstraints c = super.c;
		c.weightx = 0.5;
		c.weighty = 0.5;
		this.dbiModel = d;
		this.buttonsArray = new JRadioButton[8];
		for (int i = 0; i < buttonsArray.length; i++) {
			final int currentPin = i;
			buttonsArray[i] = new JRadioButton();
			buttonsArray[i].setSelected(dbiModel.getPin(i));
			buttonsArray[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
				       dbiModel.setPin(currentPin, true);
				    }
				    else if (e.getStateChange() == ItemEvent.DESELECTED) {
				        dbiModel.setPin(currentPin, false);
				    }
				}
			});
			
			if (currentPin == 0) {
				c.gridx = 0;
				c.gridy = 1;
			} else if (currentPin == 1) {
				c.gridx = 0;
				c.gridy = 2;
			} else if (currentPin == 2) {
				c.gridx = 0;
				c.gridy = 3;
			} else if (currentPin == 3) {
				c.gridx = 1;
				c.gridy = 1;
			} else if (currentPin == 4) {
				c.gridx = 1;
				c.gridy = 2;
			} else if (currentPin == 5) {
				c.gridx = 1;
				c.gridy = 3;
			} else if (currentPin == 6) {
				c.gridx = 0;
				c.gridy = 4;
			} else if (currentPin == 7) {
				c.gridx = 1;
				c.gridy = 4;
			}
			super.addRow(buttonsArray[i], c);
		}
		
	}

	@Override
	public String getTitle() {
		return this.dbiModel.getTitle();
	}

	@Override
	public boolean setTitle(String s) {
		return this.dbiModel.setTitle(s);
	}

	@Override
	public String getType() {
		return this.dbiModel.getType();
	}

}
