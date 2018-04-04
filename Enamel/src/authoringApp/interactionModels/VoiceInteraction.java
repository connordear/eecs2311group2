package authoringApp.interactionModels;

public class VoiceInteraction extends Interaction {

	private String fileName;
	
	public VoiceInteraction() {
		super("Voice Interaction", 0, 0);
		this.fileName = this.getTitle();
	}

	@Override
	public String generateScenarioText() {
		String base = "/~sound:";
		return base + this.fileName;
	}

	@Override
	public String getType() {
		return Interaction.InteractionType.VOICE.getDescription();
	}
	
	public String getSoundFilePath() {
		return this.fileName;
	}
	
	public void setSoundFilePath(String filePath) {
		this.fileName = filePath;
	}
}
