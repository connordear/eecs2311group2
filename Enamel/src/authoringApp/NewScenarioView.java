package authoringApp;

public interface NewScenarioView {
	static final int ERROR_INVALID_TITLE = 0;
	static final int ERROR_FILE_EXISTS = 1;
	
	public void createScenario();
	public void createScenarioSucceeded();
	public void createScenarioFailed(int errorCode);
	public void back();
}
