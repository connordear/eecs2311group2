package authoringApp;

public class ReadInteraction extends Interaction {

	private String data;
	
	public ReadInteraction() {
		super("Read");
		this.data = "";
	}
	
	public ReadInteraction(String title) {
		super(title);
		this.data = "";
	}
	
	public ReadInteraction(ReadInteraction toCopy) {
		super(toCopy.getTitle());
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
		return Interaction.InteractionTypes.get(Interaction.InteractionType.READ);
	}
	
	@Override
	public String generateScenarioText() {
		return this.getData();
	}

}
