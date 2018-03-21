package authoringApp.interactionModels;

public class ReadInteraction extends Interaction {

	private String data;
	
	public ReadInteraction() {
		super("Read", 0, 0);
		this.data = "";
	}
	
	public ReadInteraction(String title) {
		super(title, 0, 0);
		this.data = "";
	}
	
	public ReadInteraction(ReadInteraction toCopy) {
		super(toCopy.getTitle(), 0, 0);
		this.data = new String(toCopy.data);
	}
	
	public String getData() {
		return new String(this.data);
	}
	
	public void setData(String d) {
		this.data = d;
	}
	
	@Override
	public String getType() {
		return Interaction.InteractionType.READ.getDescription();
	}
	
	@Override
	public String generateScenarioText() {
		return this.getData();
	}

}
