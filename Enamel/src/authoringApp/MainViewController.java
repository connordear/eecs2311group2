package authoringApp;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import enamel.ScenarioParser;

public class MainViewController  {
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

	public void openScenario() {
		int save = 0;
		if (this.appModel.isInEditingMode()) {
			save = JOptionPane.showConfirmDialog(this.view, "Would you like to save changes to the current scenario?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (save == JOptionPane.YES_OPTION) {
				saveScenario();
			} else {
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

	public void saveScenario() {
		if (!this.appModel.isInEditingMode()) return;
		saveFile();
	}

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

	public void exit() {
		this.view.exit();
	}

	public void showCard(String cardName) {
		this.view.showCard(cardName);
	}
	
	public void openEditor(Scenario s) {
		EditorController econtroller = new EditorController();
		econtroller.setModel(s);
		this.appModel.setEditingMode(true);
		this.view.setEditingMode(true);
		this.view.openEditor(econtroller);
		
	}

	public void createScenario(int cells, int buttons) {
		scenarioModel = new Scenario(cells, buttons);
		openEditor(scenarioModel);
	}

	public void goBack() {
		showCard(MainView.MAIN_PANE);
		this.appModel.setEditingMode(false);
	}
	
	public void saveFile() {
		if (new File(this.scenarioModel.getPath()).isFile()) {
			try {
				this.scenarioModel.generateScenarioText();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error",
						"Error saving scenario file!",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		} else {
			JFileChooser fileChooser = new JFileChooser();
			FileFilter txtFilter = new FileFilter() {
				@Override
				public String getDescription() {
					return "Text File (*.TXT)";
				}

				@Override
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					} else {
						return file.getName().toLowerCase().endsWith(".txt");
					}
				}
			};

			fileChooser.setFileFilter(txtFilter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			
			int userChoice = fileChooser.showSaveDialog(null);
			if (userChoice == JFileChooser.APPROVE_OPTION) {
				String saveFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				if (new File(saveFilePath).isFile()) {
					int overwriteExistingFile = 0;
					overwriteExistingFile = JOptionPane.showConfirmDialog(null, "The file already exists. Replace existing file?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (overwriteExistingFile == JOptionPane.YES_OPTION) {
						if (!saveFilePath.toLowerCase().endsWith(".txt")) {
							saveFilePath += ".txt";
						}
						this.scenarioModel.setPath(saveFilePath);
						
						try {
							this.scenarioModel.generateScenarioText();
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null, "Error",
									"Error saving scenario file!",
									JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						}
					} else {
						return;
					}
				}
			}
		}
	}
}
