package authoringApp.interactionModels;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import authoringApp.Interaction;

public class VoiceInteraction extends Interaction {

	private File file;
	private Map<String, String> soundFileProperties;
	
	public VoiceInteraction() {
		super("Voice Interaction");
		
		soundFileProperties = new LinkedHashMap<String, String>();
		soundFileProperties.put("Name", "");
		soundFileProperties.put("Folder path", "");
		soundFileProperties.put("Length", "");
		soundFileProperties.put("Bit rate", "");
		soundFileProperties.put("Size", "");
		soundFileProperties.put("Item type", "");
		
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
	
	private Object[][] createSoundProperties(HashMap map) {
		Object[][] arr = new Object[map.size()][];
		Set entries = map.entrySet();
		Iterator entriesIterator = entries.iterator();
		
		int i = 0;
		while (entriesIterator.hasNext()) {
			Map.Entry mapping = (Map.Entry) entriesIterator.next();
			
			arr[i][0] = mapping.getKey();
			arr[i][1] = mapping.getValue();
			i++;
		}
		return arr;
	}
	
	public Map<String, String> getSoundProperties() {
		return this.soundFileProperties;
	}
	
	public File getSoundFile() {
		return this.file;
	}
	
	public void setSoundFile(File f) {
		this.file = f;
	}

}
