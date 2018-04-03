package authoringApp;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final String APPLICATION_TITLE = "Braille Author";
	private MainViewController controller;
	
	private CardLayout cards;
	private JPanel containerPanel, startupPane, newScenarioPane, editorPane;
	private JMenuBar menuBar;
	private JMenu fileMenu, scenarioMenu;
	private JMenuItem newFile, openFile, saveFile, saveFileAs, exit;
	private JMenuItem run;
	private JFileChooser fc;
	
	private GridBagConstraints gbc;
	
	public MainFrame(MainViewController controller) {
		controller.setView(this);
		gbc = new GridBagConstraints();
		initializeMenu();
		containerPanel = new JPanel(new CardLayout());
		cards = (CardLayout) containerPanel.getLayout();
		startupPane = new StartupPane(controller);
		newScenarioPane = new NewScenarioConfigPane(controller);
		containerPanel.add(startupPane, MainView.MAIN_PANE);
		containerPanel.add(newScenarioPane, MainView.NEW_PANE);
		cards.show(containerPanel, MainView.MAIN_PANE);
		
		gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		setTitle(APPLICATION_TITLE);
		setMainFrameController(controller);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setJMenuBar(menuBar);
		setLayout(new GridBagLayout());
		getContentPane().add(containerPanel, gbc);
		setSize(1000, 600);
		//pack();
		setVisible(true);
	}
	
	public void initializeMenu() {
		menuBar = new JMenuBar();
    	fileMenu = new JMenu("File");
    	scenarioMenu = new JMenu("Scenario");

    	newFile = new JMenuItem("New");
    	openFile = new JMenuItem("Open");
    	saveFile = new JMenuItem("Save");
    	saveFileAs = new JMenuItem("Save As");
    	exit = new JMenuItem("Exit");
    	run = new JMenuItem("Run");
    	
    	newFile.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) { newScenario(); }
    	});
    	openFile.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) { openScenario(); }
    	});
    	saveFile.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) { saveScenario(); }
    	});
    	saveFileAs.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) { saveScenarioAs(); }
    	});
    	exit.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) { exit(); }
    	});
    	run.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {  }
    	});
    	
    	fileMenu.add(newFile);
    	fileMenu.add(openFile);
    	fileMenu.addSeparator();
    	fileMenu.add(saveFile);
    	fileMenu.add(saveFileAs);
    	fileMenu.addSeparator();
    	fileMenu.add(exit);
    	
    	scenarioMenu.add(run);

    	menuBar.add(fileMenu);
    	setEditingMode(false);
	}
	
	public void setMainFrameController(MainViewController listener) {
		this.controller = listener;
	}

	public void newScenario() {
		this.controller.newScenario();
	}
	
	public void saveScenario() {
		if (!this.controller.getModel().isInEditingMode()) return;
		this.controller.saveFile();
	}

	public void saveScenarioAs() {
		if (!this.controller.getModel().isInEditingMode()) return;
		this.controller.saveFileAs();
	}

	public void exit() {
		this.dispose();
	}
	
	public void addCard(JPanel pane, String cardName) {
		containerPanel.add(pane, cardName);
	}

	public void showCard(String cardName) {
		cards.show(containerPanel, cardName);
	}
	
	public void openScenario() {
		this.controller.openScenario();
	}
	
	public void openEditor(EditorController econtroller) {
		editorPane = new EditorPane(econtroller);
		addCard(editorPane, MainView.EDITOR_PANE);
		showCard(MainView.EDITOR_PANE);
	}
	
	public void setEditingMode(boolean isEditing) {
		saveFile.setEnabled(isEditing);
    	saveFileAs.setEnabled(isEditing);
    	run.setEnabled(isEditing);
	}
}
