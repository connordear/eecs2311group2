package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.Insets;
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
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.ipadx = 10;
		c.ipady = 10;
		c.gridwidth = 2;
		c.weightx = 0;
		c.weighty = 4;
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
				c.weightx = 0;
				c.gridx = 0;
				c.gridy = 1;
				brailleString = "top left";
			} else if (currentPin == 1) {
				c.weightx = 0;
				c.gridx = 0;
				c.gridy = 2;
				brailleString = "second from the top, left";
			} else if (currentPin == 2) {
				c.weightx = 0;
				c.gridx = 0;
				c.gridy = 3;
				brailleString = "third from the top, left";
			} else if (currentPin == 3) {
				c.weightx = 0;
				c.gridx = 1;
				c.gridy = 1;
				brailleString = "first from the top, right";
			} else if (currentPin == 4) {
				c.weightx = 0;
				c.gridx = 1;
				c.gridy = 2;
				brailleString = "second from the top, right";
			} else if (currentPin == 5) {
				c.weightx = 0;
				c.gridx = 1;
				c.gridy = 3;
				brailleString = "third from the top, right";
			} else if (currentPin == 6) {
				c.weightx = 0;
				c.gridx = 0;
				c.gridy = 4;
				brailleString = "fourth from the top, left";
			} else if (currentPin == 7) {
				c.weightx = 0;
				c.gridx = 1;
				c.gridy = 4;
				brailleString = "fourth from the top, right";
			}
			c.fill = GridBagConstraints.CENTER;
			buttonsArray[i].getAccessibleContext().setAccessibleDescription("Toggle this button to display/hide the " + brailleString + "most pin.");
			super.addRow(buttonsArray[i], c);
			this.brailleCellComboBox = new JComboBox<Integer>();
			for (int cellNumber = 1; cellNumber <= dbiModel.getNumCells(); cellNumber++) {
				this.brailleCellComboBox.addItem(cellNumber);
			}
			this.brailleCellComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						dbiModel.setCellNumber((Integer) e.getItem() - 1);
					}
					
				}
				
			});
			
			c.gridx = 0;
			c.gridy = 5;
			c.fill = GridBagConstraints.HORIZONTAL;
			super.addRow(this.brailleCellComboBox, c);
			
		}
		
	}
}
