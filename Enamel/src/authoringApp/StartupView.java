package authoringApp;

public interface StartupView {
	
	public String getScenarioFileName();
	
	public void createNewScenarioSelected();
	public void openScenarioSelected();
	
	public void loadScenarioSucceeded();
	public void loadScenarioFailed();
	
	public StartupViewController getStartupViewController();
	public void setStartupViewController(StartupViewController listener);
	
	
}
