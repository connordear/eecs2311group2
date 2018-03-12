package authoringApp;

import java.io.File;

public class VoiceInteraction extends Interaction {

	private File file;
	
	public VoiceInteraction() {
		super("Voice Interaction");
	}


	@Override
	public String generateScenarioText() {
		String base = "/~sound:";
		return base + this.file.getPath();
	}


	@Override
	public String getType() {
		return Interaction.InteractionType.VOICE.getDescription();
	}

}
