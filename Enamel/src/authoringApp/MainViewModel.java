package authoringApp;

import java.io.File;

public class MainViewModel {
	private boolean isEditing;
	private File scenarioFile;
	
	public MainViewModel() {
		this.isEditing = false;
		this.scenarioFile = null;
	}
	
	public boolean isInEditingMode() {
		return this.isEditing;
	}
	
	public void setEditingMode(boolean isEditing) {
		this.isEditing = isEditing;
	}
	
	public void setScenarioFile(File scenarioFile) {
		this.scenarioFile = scenarioFile;
	}
	
	public File getScenarioFile() {
		return this.scenarioFile;
	}
}
