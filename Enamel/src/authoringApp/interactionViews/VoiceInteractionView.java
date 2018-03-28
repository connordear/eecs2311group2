package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import authoringApp.interactionModels.VoiceInteraction;

public class VoiceInteractionView extends InteractionView {

	private VoiceInteraction voiceModel;
	
	private JLabel promptLbl, recordTimeLbl;
	private JToggleButton recordBtn, pauseBtn;
	private JButton openFileBtn;
	private JScrollPane scrollPan;
	private JTable soundInfoTbl;
	
	private ImageIcon iconRecord, iconStop, iconPause, iconResume;
	
	public VoiceInteractionView(VoiceInteraction v) {
		super(v.getInteraction());
		GridBagConstraints c = super.c;
		this.voiceModel = v;
		
		promptLbl = new JLabel("<html>Record or upload a sound to play back to user.<br>Sound file must be .wav file format.<html>");
		recordTimeLbl = new JLabel("<html><strong>Record Time: 00:00:00</strong></html>");
		
		recordBtn = new JToggleButton("Record");
		recordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (recordBtn.isSelected()) {
					recordBtn.setText("Stop");
					//startRecording();
				} else {
					recordBtn.setText("Record");
					//stopRecording();
				}
			}
		});
		
		pauseBtn = new JToggleButton("Pause");
		pauseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pauseBtn.isSelected()) {
					pauseBtn.setText("Resume");
					//pauseRecording();
				} else {
					pauseBtn.setText("Pause");
					//resumeRecording();
				}
			}
		});
		
		openFileBtn = new JButton("Browse");
		openFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = null;
				JFileChooser fc = new JFileChooser();
				//fc.setFileFilter(new FileNameExtensionFilter("Wave File", "wav"));
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					f = fc.getSelectedFile();
				} else {
					return;
				}
				
				int overwriteExistingSound = 0;
				if (voiceModel.getSoundFile() != null) {
					overwriteExistingSound = JOptionPane.showConfirmDialog(null, "Replace existing sound file? This will delete the saved recording as well.", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (overwriteExistingSound == JOptionPane.YES_OPTION) {
						voiceModel.setSoundFile(f);
					} else {
						return;
					}
				}
			}
		});
		
//		Map<String, String> soundFileProperties = this.voiceModel.getSoundProperties();
//		Object[][] soundData = new Object[soundFileProperties.size()][1];
//		Object[] columnNames = new Object[soundFileProperties.size()];
//		int i = 0;
//		for (String key : soundFileProperties.keySet()) {
//			columnNames[i] = key;
//			i++;
//		}
		String[][] soundData = {{"", "", "", "", "", ""}};
		String[] columnNames = {"Name", "Folder path", "Length", "Bit rate", "Size", "Item type"};
		soundInfoTbl = new JTable(soundData, columnNames);
		scrollPan = new JScrollPane(soundInfoTbl);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		super.addRow(promptLbl, c);
		
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 1;
		super.addRow(recordBtn, c);
		
		c.gridx = 1;
		super.addRow(pauseBtn, c);
		
		c.gridx = 2;
		super.addRow(openFileBtn, c);
		
		c.gridy = 3;
		c.gridx = 0;
		super.addRow(recordTimeLbl, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		super.addRow(scrollPan, c);
		
	}
}
