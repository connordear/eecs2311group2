package authoringApp.interactionModels;import java.io.File;

public class VoiceInteraction extends Interaction {

	private String fileName;
	
	public VoiceInteraction() {
		super("Voice Interaction", 0, 0);
		this.fileName = this.getTitle();
	}
	
	public VoiceInteraction(String fileName) {
		super("Voice Interaction", 0, 0);
		if (fileName.equals("correct.wav") || fileName.equals("wrong.wav")) {
			this.fileName =  "./FactoryScenarios/AudioFiles/" + fileName;
		}
		else {
			this.fileName = fileName;
		}
	
		
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
