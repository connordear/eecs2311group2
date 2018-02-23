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
			
			c.gridy = (i / 2) + 1;
			c.gridx = (i % 2) + 1;
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
