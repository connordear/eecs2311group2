package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import authoringApp.interactionModels.VoiceInteraction;
import authoringApp.AudioPlayer;
import authoringApp.RecordTimer;
import authoringApp.SoundRecordingUtil;

public class VoiceInteractionView extends InteractionView {

	private VoiceInteraction voiceModel;
	
	private SoundRecordingUtil recorder;
	private AudioPlayer player;
	private Thread playbackThread;
	private RecordTimer timer;
	private boolean isRecording = false;
	private boolean isPlaying = false;
	
	private JLabel promptLbl, recordTimeLbl;
	private JButton recordBtn, playBtn, openFileBtn;
	private JScrollPane scrollPan;
	private JTable soundInfoTbl;
	private DefaultTableModel soundData;
	
	private ImageIcon iconRecord, iconStop, iconPause, iconPlay, iconBrowse;
	
	public VoiceInteractionView(VoiceInteraction v) {
		super(v.getInteraction());
		GridBagConstraints c = super.c;
		this.voiceModel = v;
		
		this.recorder = new SoundRecordingUtil();
		this.player = new AudioPlayer();
		
		iconRecord = new ImageIcon(getClass().getResource("/assets/icon-record.png"));
		iconStop = new ImageIcon(getClass().getResource("/assets/icon-stop.png"));
		iconPause = new ImageIcon(getClass().getResource("/assets/icon-pause.png"));
		iconPlay = new ImageIcon(getClass().getResource("/assets/icon-play.png"));
		iconBrowse = new ImageIcon(getClass().getResource("/assets/icon-browse.png"));
		
		promptLbl = new JLabel("<html>Record or upload a sound to play back to user.<br>Sound file must be .wav file format.<html>");
		recordTimeLbl = new JLabel("<html><strong>Record Time: 00:00:00</strong></html>");
		
		recordBtn = new JButton("Record");
		recordBtn.setIcon(iconRecord);
		recordBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isRecording) {
					startRecording();
				} else {
					stopRecording();
				}
			}
		});
		
		playBtn = new JButton("Play");
		playBtn.setIcon(iconPlay);
		playBtn.setEnabled(false);
		playBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isPlaying) {
					playBack();
				} else {
					stopPlaying();
				}
			}
		});

		openFileBtn = new JButton("Browse");
		openFileBtn.setIcon(iconBrowse);
		openFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		
		String fileName = "";
		String filePath = "";
		try {
			fileName = voiceModel.getSoundFilePath().substring(voiceModel.getSoundFilePath().lastIndexOf('/'));
			filePath = voiceModel.getSoundFilePath();
		} catch (java.lang.StringIndexOutOfBoundsException ex) { }
		
		soundData = new DefaultTableModel(new Object[][] {
		      { fileName , filePath } },
		      new Object[] { "Name", "File Path" });
		soundInfoTbl = new JTable(soundData);
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
		super.addRow(playBtn, c);
		
		c.gridx = 2;
		super.addRow(openFileBtn, c);
		
		c.gridy = 3;
		c.gridx = 0;
		super.addRow(recordTimeLbl, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.BOTH;
		super.addRow(scrollPan, c);
		
	}
	
	/**
	 * Start recording sound, the time will count up.
	 */
	private void startRecording() {
		Thread recordThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					isRecording = true;
					recordBtn.setText("Stop");
					recordBtn.setIcon(iconStop);
					playBtn.setEnabled(false);

					recorder.start();

				} catch (LineUnavailableException ex) {
					JOptionPane.showMessageDialog(null,
							"Error", "Could not start recording sound!",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		recordThread.start();
		timer = new RecordTimer(recordTimeLbl);
		timer.start();
	}
	
	/**
	 * Stop recording and save the sound into a WAV file
	 */
	private void stopRecording() {
		isRecording = false;
		try {
			timer.cancel();
			recordBtn.setText("Record");
			recordBtn.setIcon(iconRecord);
			
			recorder.stop();

			saveFile();

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error",
					"Error stopping sound recording!",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Start playing back the sound.
	 */
	private void playBack() {
		timer = new RecordTimer(recordTimeLbl);
		timer.start();
		isPlaying = true;
		playbackThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					playBtn.setText("Stop");
					playBtn.setIcon(iconStop);
					recordBtn.setEnabled(false);

					player.play(voiceModel.getSoundFilePath());
					timer.reset();

					playBtn.setText("Play");
					playBtn.setIcon(iconPlay);
					recordBtn.setEnabled(true);
					isPlaying = false;

				} catch (UnsupportedAudioFileException ex) {
					ex.printStackTrace();
				} catch (LineUnavailableException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		});

		playbackThread.start();
	}

	/**
	 * Stop playing back.
	 */
	private void stopPlaying() {
		timer.reset();
		timer.interrupt();
		player.stop();
		playbackThread.interrupt();
	}

	/**
	 * Save the recorded sound into a WAV file.
	 */
	private void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileFilter wavFilter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Sound file (*.WAV)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".wav");
				}
			}
		};

		fileChooser.setFileFilter(wavFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int userChoice = fileChooser.showSaveDialog(null);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			String saveFilePath = fileChooser.getSelectedFile().getAbsolutePath();
			if (!saveFilePath.toLowerCase().endsWith(".wav")) {
				saveFilePath += ".wav";
			}
			voiceModel.setSoundFilePath(saveFilePath);
			File wavFile = new File(voiceModel.getSoundFilePath());

			try {
				recorder.save(wavFile);

				JOptionPane.showMessageDialog(null,
						"Saved recorded sound to:\n" + voiceModel.getSoundFilePath());

				playBtn.setEnabled(true);

			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error",
						"Error saving to sound file!",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Load an existing WAV sound file.
	 */
	private void openFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileFilter wavFilter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Sound file (*.WAV)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".wav");
				}
			}
		};

		fileChooser.setFileFilter(wavFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int userChoice = fileChooser.showOpenDialog(null);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			int overwriteExistingSound = 0;
			if (voiceModel.getSoundFilePath() != null || voiceModel.getSoundFilePath() != "" || !voiceModel.getSoundFilePath().toLowerCase().endsWith(".wav")) {
				overwriteExistingSound = JOptionPane.showConfirmDialog(null, "Replace existing sound file? This will delete the saved recording as well.", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (overwriteExistingSound == JOptionPane.YES_OPTION) {
					voiceModel.setSoundFilePath(fileChooser.getSelectedFile().getAbsolutePath());
					playBtn.setEnabled(true);
					updateSoundData();
				} else {
					return;
				}
			}
		}
	}
	
	private void updateSoundData() {
		String fileName = "";
		String filePath = "";
		try {
			fileName = voiceModel.getSoundFilePath().substring(voiceModel.getSoundFilePath().lastIndexOf('\\') + 1);
			filePath = voiceModel.getSoundFilePath();
		} catch (java.lang.StringIndexOutOfBoundsException ex) {
			System.out.println(ex.toString());
		}
		
		soundData.setValueAt(fileName, 0, 0);
		soundData.setValueAt(filePath, 0, 1);
//		soundData.addRow(new Object[] { fileName, filePath });
//		try {
//			soundData.removeRow(0);
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//		}
	}
}
