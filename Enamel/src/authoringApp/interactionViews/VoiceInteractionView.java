package authoringApp.interactionViews;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import authoringApp.interactionModels.VoiceInteraction;

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
}
