package authoringApp.interactionModels;

public class QuestionInteraction extends Interaction {
	
	private class ResponseInteraction extends Interaction{
			
		SkipButtonInteraction skipB;
		KeywordInteraction kw;
		ReadInteraction read;
		
		public ResponseInteraction(String title, int cells, int buttons, SkipButtonInteraction sbi, ReadInteraction r) {
			super(title, cells, buttons);
			this.skipB = sbi;
			this.read = r;
			this.kw = new KeywordInteraction(sbi.keyword);
		}

		public String generateScenarioTextSkipButton() {
			return this.skipB.generateScenarioText();
		}
		
		@Override
		public String generateScenarioText() {
			String res = "";
			res += this.kw.generateScenarioText() + "\n";
			// Voice/Sound goes here
			res += this.read.generateScenarioText() + "\n";
			return res;
		}

		@Override
		public String getType() {
			return Interaction.InteractionType.RESPONSE.getDescription();
		}
		
	}
	
	public static final KeywordInteraction END_KEYWORD = new KeywordInteraction("END_OF_QUESTION");
	
	PauseInteraction initPause;
	CellClearInteraction initClear;
	DisplayBrailleInteraction dispBraille;
	UserInputInteraction userInput;
	ReadInteraction prompt;
	ResponseInteraction[] responses;
	

	public QuestionInteraction(String title, int numCells, int numButtons) {
		super(title, numCells, numButtons);
		responses = new ResponseInteraction[numButtons];
		for (int i = 0; i < numButtons; i++) {
			SkipButtonInteraction skipB = new SkipButtonInteraction(i, Integer.toString(i), numCells, numButtons);
			responses[i] = new ResponseInteraction(title, numCells, numButtons, skipB, new ReadInteraction());
		}
		this.initPause = new PauseInteraction(1);
		this.initClear = new CellClearInteraction(0, numCells, numButtons);
		this.dispBraille = new DisplayBrailleInteraction(0, "00000000", numCells, numButtons);
		this.userInput = new UserInputInteraction();
		this.prompt = new ReadInteraction();
	}

	@Override
	public String generateScenarioText() {
		String res = "";
		res += this.initPause.generateScenarioText() + "\n";
		res += this.initClear.generateScenarioText() + "\n";
		res += this.dispBraille.generateScenarioText() + "\n";
		res += this.prompt.generateScenarioText() + "\n";
		for (ResponseInteraction rs : responses) {
			res += rs.generateScenarioTextSkipButton() + "\n";
		}
		res += this.userInput.generateScenarioText() + "\n";
		for (ResponseInteraction r : responses) {
			res += r.generateScenarioText() + "\n";
			res += "/~skip:" + QuestionInteraction.END_KEYWORD.getKeyword() + "\n";
		}
		res += QuestionInteraction.END_KEYWORD.generateScenarioText() + "\n";
	
		return res;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
