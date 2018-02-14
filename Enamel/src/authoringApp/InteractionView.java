package authoringApp;

import javax.swing.JPanel;

public abstract class InteractionView {
	
	public static final String READ = "READ";
	public static final String VOICE = "VOICE";
	public static final String DISPLAY_BRAILLE = "DISPLAY BRAILLE";
	public static final String KEYWORD = "KEYWORD";
	public static final String PAUSE = "PAUSE";
	public static final String SKIP_BUTTON = "SKIP BUTTON";
	public static final String USER_INPUT = "USER INPUT";
	public static final String RESET_BUTTONS = "RESET BUTTONS";
	public static final String CLEAR_BRAILLE = "CLEAR BRAILLE";
	protected JPanel interactionView;
	
	public InteractionView() {
		this.interactionView = new JPanel();
	}
	
	
	public JPanel getInteractionView() {
		return this.interactionView;
	};
	
	abstract public String getType();

}
