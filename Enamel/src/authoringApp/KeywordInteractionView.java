package authoringApp;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class KeywordInteractionView extends InteractionView {

	private JTextField keywordTextField;
	private KeywordInteraction keymodel;
	
	public KeywordInteractionView(KeywordInteraction k) {
		super(k.getInteraction());
		GridBagConstraints c = super.c;
		this.keymodel = k;
		
		this.keywordTextField = new JTextField(this.keymodel.getKeyword());
		this.keywordTextField.getAccessibleContext().setAccessibleDescription("Enter the keyword you wish to specify here.");
		this.keywordTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					keymodel.setKeyword(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				} 
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					keymodel.setKeyword(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					keymodel.setKeyword(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			
		});
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

}
