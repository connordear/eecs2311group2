package authoringApp;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class KeywordInteractionView extends InteractionView {

	private String keyword;
	private JTextField keywordTextField;
	private KeywordInteraction keymodel;
	
	public KeywordInteractionView(KeywordInteraction k) {
		super(k.getInteraction());
		GridBagConstraints c = super.c;
		this.keymodel = k;
		
		this.keywordTextField = new JTextField(this.keymodel.getKeyword());
		this.keywordTextField.getAccessibleContext().setAccessibleDescription("Enter the keyword you wish to specify here.");
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(this.keywordTextField, c);
		
		JLabel keywordLabel = new JLabel("Keyword: ");
		keywordLabel.setLabelFor(keywordTextField);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		super.addRow(keywordLabel, c);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setTitle(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
