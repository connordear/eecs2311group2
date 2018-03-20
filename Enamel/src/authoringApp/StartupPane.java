package authoringApp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartupPane extends JPanel {
	
	private MainViewController controller;
	private JLabel welcomeLbl, statusLbl;
	private JButton newBtn, openBtn;
	private JFileChooser fc;
	private GridBagConstraints gbc;
	
	public StartupPane(MainViewController controller) {
		setStartupViewController(controller);
		setLayout(new GridBagLayout());
		
		welcomeLbl = new JLabel("<html><h2>Welcome to the Braille Author application!</h2>To create a new series of user interactions for the user (called a scenario), click <strong>New</strong>.<br>To open and edit and existing scenario file, click <strong>Open</strong>.</html>");
		statusLbl = new JLabel("");
		newBtn = new JButton("New");
		openBtn = new JButton("Open");
		fc = new JFileChooser();
		
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createNewScenarioSelected();
			}
		});
		openBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openScenarioSelected();
			}
		});
		
		// Welcome label
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		add(welcomeLbl, gbc);
		
		// New and Open buttons
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		gbc.gridwidth = 1;
		add(newBtn, gbc);
		gbc.gridy++;
		add(openBtn, gbc);
		
		// Status label (for error messages in case scenario file failed to load)
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		add(statusLbl, gbc);
	}

	public void createNewScenarioSelected() {
		this.controller.newScenario();
	}
	
	public void openScenarioSelected() {
		this.controller.openScenario();
	}

	public void loadScenarioSucceeded() {
		statusLbl.setText("<html><h4>Loading scenario file..</h4></html>");
		statusLbl.setForeground(Color.GREEN);
	}

	public void loadScenarioFailed() {
		statusLbl.setText("<html><h4>File selected is not valid scenario file.</h4></html>");
		statusLbl.setForeground(Color.RED);
	}

	public MainViewController getStartupViewController() {
		return controller;
	}

	public void setStartupViewController(MainViewController controller) {
		this.controller = controller;
	}
}