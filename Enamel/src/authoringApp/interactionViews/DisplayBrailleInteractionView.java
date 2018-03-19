package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import authoringApp.interactionModels.DisplayBrailleInteraction;

public class DisplayBrailleInteractionView extends InteractionView {

	private JRadioButton[] buttonsArray;
	private JComboBox<Integer> brailleCellComboBox;
	protected DisplayBrailleInteraction dbiModel;
	
	
	public DisplayBrailleInteractionView(DisplayBrailleInteraction d) {
		super(d.getInteraction());
		GridBagConstraints c = super.c;
		c.weightx = 1;
		c.weighty = 1;
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
			String brailleString = "";
			if (currentPin == 0) {
				c.gridx = 0;
				c.gridy = 1;
				brailleString = "top left";
			} else if (currentPin == 1) {
				c.gridx = 0;
				c.gridy = 2;
				brailleString = "second from the top, left";
			} else if (currentPin == 2) {
				c.gridx = 0;
				c.gridy = 3;
				brailleString = "third from the top, left";
			} else if (currentPin == 3) {
				c.gridx = 1;
				c.gridy = 1;
				brailleString = "first from the top, right";
			} else if (currentPin == 4) {
				c.gridx = 1;
				c.gridy = 2;
				brailleString = "second from the top, right";
			} else if (currentPin == 5) {
				c.gridx = 1;
				c.gridy = 3;
				brailleString = "third from the top, right";
			} else if (currentPin == 6) {
				c.gridx = 0;
				c.gridy = 4;
				brailleString = "fourth from the top, left";
			} else if (currentPin == 7) {
				c.gridx = 1;
				c.gridy = 4;
				brailleString = "fourth from the top, right";
			}
			buttonsArray[i].getAccessibleContext().setAccessibleDescription("Toggle this button to display/hide the " + brailleString + "most pin.");
			super.addRow(buttonsArray[i], c);
			
			this.brailleCellComboBox = new JComboBox<Integer>();
			for (int cellNumber = 0; cellNumber < 10; cellNumber++) {
				this.brailleCellComboBox.addItem(cellNumber);
			}
			this.brailleCellComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					dbiModel.setCellNumber(brailleCellComboBox.getSelectedIndex());
				}
				
			});
			
			c.gridx = 0;
			c.gridy = 5;
			c.fill = GridBagConstraints.HORIZONTAL;
			super.addRow(this.brailleCellComboBox, c);
			
		}
		
	}
}
