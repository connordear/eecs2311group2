package authoringApp;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import enamel.ScenarioParser;

public class MainViewController implements MainView {
	private MainFrame view;
	private MainViewModel appModel;
	private Scenario scenarioModel;
	
	public MainViewController() {
	}
	
	public void setView(MainFrame mainFrame) {
		this.view = mainFrame;
	}
	
	public MainFrame getView() {
		return this.view;
	}
	
	public void setModel(MainViewModel s) {
		this.appModel = s;
	}
	
	public MainViewModel getModel() {
		return this.appModel;
	}

	@Override
	public void newScenario() {
		int save = 0;
		if (this.appModel.isInEditingMode()) {
			save = JOptionPane.showConfirmDialog(null, "Would you like to save changes to the current scenario?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (save == JOptionPane.YES_OPTION) {
				saveScenario();
			} else if (save == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		showCard(MainView.NEW_PANE);
	}

	@Override
	public void openScenario() {
		int save = 0;
		if (this.appModel.isInEditingMode()) {
			save = JOptionPane.showConfirmDialog(this.view, "Would you like to save changes to the current scenario?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (save == JOptionPane.YES_OPTION) {
				saveScenario();
			} else if (save == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
		
		File scenarioFile= null;
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
		int returnVal = fc.showOpenDialog(this.view);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			scenarioFile = fc.getSelectedFile();
		} else {
			return;
		}
		
		if (!Scenario.isValidScenarioFile(scenarioFile)) {
			JOptionPane.showMessageDialog(null, 
                              "Invalid file selected. Please select a valid scenario file.", 
                              "Invalid File", 
                              JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		openEditor(new Scenario(scenarioFile));
	}

	@Override
	public void saveScenario() {
		if (!this.appModel.isInEditingMode()) return;
		try {
			scenarioModel.generateScenarioText();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void saveScenarioAs() {
		if (!this.appModel.isInEditingMode()) return;
		saveScenario();
	}

	@Override
	public void runScenario() {
		if (!this.appModel.isInEditingMode()) return;
		Thread starterCodeThread = new Thread("Starter Code Thread") {
		    public void run(){    
		        ScenarioParser s = new ScenarioParser(true);
		        s.setScenarioFile(appModel.getScenarioFile().getAbsolutePath());
		    }
		};
		starterCodeThread.start();
	}

	@Override
	public void exit() {
		this.view.exit();
	}

	@Override
	public void showCard(String cardName) {
		this.view.showCard(cardName);
	}
	
	public void openEditor(Scenario s) {
		EditorController econtroller = new EditorController();
		econtroller.setModel(s);
		this.view.openEditor(econtroller);
		this.appModel.setEditingMode(true);
		this.view.setEditingMode(true);
	}

	public void createScenario(String title, int cells, int buttons) {
		String pwd = System.getProperty("user.dir") + "/";
		String fname = pwd + title + ".txt";
		if (new File(fname) != null) {
			scenarioModel = new Scenario(title, cells, buttons);
			openEditor(scenarioModel);
		}
	}

	public void goBack() {
		showCard(MainView.MAIN_PANE);
		this.appModel.setEditingMode(false);
	}
}
