package authoringApp;

public interface MainView {
	public static final String MAIN_PANE = "Main";
	public static final String NEW_PANE = "New";
	public static final String EDITOR_PANE = "Editor";
	
	public void newScenario();
	public void openScenario();
	public void saveScenario();
	public void saveScenarioAs();
	public void runScenario();
	public void exit();
	
	public void showCard(String cardName);
	
}
