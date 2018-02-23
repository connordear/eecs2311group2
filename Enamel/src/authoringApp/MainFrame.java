package authoringApp;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame implements MainView {
	private static final String APPLICATION_TITLE = "Braille Author";
	private MainViewController controller;
	
	private CardLayout cards;
	private JPanel containerPanel, startupPane, newScenarioPane, editorPane;
	private JMenuBar menuBar;
	private JMenu fileMenu, scenarioMenu;
	private JMenuItem newFile, openFile, saveFile, saveAsFile, exit;
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
    	saveAsFile = new JMenuItem("Save As");
    	exit = new JMenuItem("Exit");
    	run = new JMenuItem("Run");

    	fileMenu.add(newFile);
    	fileMenu.add(openFile);
    	fileMenu.addSeparator();
    	fileMenu.add(saveFile);
    	fileMenu.add(saveAsFile);
    	fileMenu.addSeparator();
    	fileMenu.add(exit);
    	
    	scenarioMenu.add(run);

    	menuBar.add(fileMenu);
	}
	
	public void setMainFrameController(MainViewController listener) {
		this.controller = listener;
	}

	@Override
	public void newScenario() {
		this.controller.newScenario();
	}
	
	@Override
	public void saveScenario() {
	}

	@Override
	public void saveScenarioAs() {
	}

	@Override
	public void runScenario() {
	}

	@Override
	public void exit() {
		this.dispose();
	}
	
	public void addCard(JPanel pane, String cardName) {
		containerPanel.add(pane, cardName);
	}

	@Override
	public void showCard(String cardName) {
		cards.show(containerPanel, cardName);
	}
	
	@Override
	public void openScenario() {
		
	}
	
	public void openEditor(EditorController econtroller) {
		editorPane = new EditorPane(econtroller);
		addCard(editorPane, MainView.EDITOR_PANE);
		showCard(MainView.EDITOR_PANE);
	}
}
