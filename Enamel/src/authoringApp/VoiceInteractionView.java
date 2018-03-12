package authoringApp;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

public class VoiceInteractionView extends InteractionView {

	private VoiceInteraction voiceModel;
	JLabel fileLabel = new JLabel("UNDER CONSTRUCTION");
	
	public VoiceInteractionView(VoiceInteraction v) {
		super(v.getInteraction());
		GridBagConstraints c = super.c;
		this.voiceModel = v;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		super.addRow(fileLabel, c);
	}

	@Override
	public String getTitle() {
		return this.voiceModel.getTitle();
	}

	@Override
	public boolean setTitle(String s) {
		return this.voiceModel.setTitle(s);
	}

	@Override
	public String getType() {
		return this.voiceModel.getType();
	}

}
