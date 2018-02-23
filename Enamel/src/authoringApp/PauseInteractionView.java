package authoringApp;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

public class PauseInteractionView extends InteractionView {

	private PauseInteraction pauseModel;
	private JComboBox<Integer> secondsComboBox;
	JLabel pauseLabel = new JLabel("Seconds: ");
	
	public PauseInteractionView(PauseInteraction p) {
		super(p.getInteraction());
		GridBagConstraints c = super.c;
		this.pauseModel = p;
		
		this.secondsComboBox = new JComboBox<Integer>();
		for (int i = 0; i < 20; i++) {
			this.secondsComboBox.addItem(i);
		}
		this.secondsComboBox.setSelectedIndex(this.pauseModel.getLength());
		this.secondsComboBox.addItemListener(new ItemListener() {
			@Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		          Object item = event.getItem();
		          pauseModel.setLength((Integer) item);
		       }
		    }    
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(this.secondsComboBox, c);
		
		
		c.gridx = 0;
		c.gridy = 1;
		super.addRow(this.pauseLabel, c);
	}

	@Override
	public String getTitle() {
		return this.pauseModel.getTitle();
	}

	@Override
	public boolean setTitle(String s) {
		return this.pauseModel.setTitle(s);
	}

	@Override
	public String getType() {
		return this.pauseModel.getType();
	}

}
