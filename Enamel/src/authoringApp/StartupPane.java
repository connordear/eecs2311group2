package authoringApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartupPane extends JPanel {
	
	private MainViewController controller;
	private JLabel welcomeLbl, statusLbl;
	private JButton newBtn, openBtn, exitBtn;
	private GridBagConstraints gbc;
	
	private ImageIcon iconNew, iconOpen, iconExit;
	
	public StartupPane(MainViewController controller) {
		setStartupViewController(controller);
		setLayout(new GridBagLayout());
		
		iconNew = new ImageIcon(getClass().getResource("/assets/icon-new.png"));
		iconOpen = new ImageIcon(getClass().getResource("/assets/icon-browse.png"));
		iconExit = new ImageIcon(getClass().getResource("/assets/icon-exit.png"));
		
		welcomeLbl = new JLabel("<html><h2>Welcome to the Braille Author application!</h2>To create a new series of user interactions for the user (called a scenario), click <strong>New</strong>.<br>To open and edit and existing scenario file, click <strong>Open</strong>.</html>");
		welcomeLbl.setFont(new Font("Verdana", Font.PLAIN, 20));
		statusLbl = new JLabel("");
		newBtn = new JButton("New");
		newBtn.setIcon(iconNew);
		newBtn.getAccessibleContext().setAccessibleName("New scenario");
		newBtn.getAccessibleContext().setAccessibleDescription("Create a new Scenario");
		openBtn = new JButton("Open");
		openBtn.setIcon(iconOpen);
		openBtn.getAccessibleContext().setAccessibleName("Open scenario");
		openBtn.getAccessibleContext().setAccessibleDescription("Open an existing Scenario");
		exitBtn = new JButton("Exit");
		exitBtn.setIcon(iconExit);
		exitBtn.getAccessibleContext().setAccessibleName("Exit");
		exitBtn.getAccessibleContext().setAccessibleDescription("Exit the application");
		
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
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.exit();
			}
		});
		
		// Welcome label
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.weightx = 0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		add(welcomeLbl, gbc);
		
		// New and Open buttons
		gbc.weightx = 1.0;
		gbc.gridx += 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(newBtn, gbc);
		gbc.gridy++;
		add(openBtn, gbc);
		gbc.gridy++;
		add(exitBtn, gbc);
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
		statusLbl.getAccessibleContext().setAccessibleDescription("File selected is not valid");
		statusLbl.setForeground(Color.RED);
	}

	public MainViewController getStartupViewController() {
		return controller;
	}

	public void setStartupViewController(MainViewController controller) {
		this.controller = controller;
	}
}