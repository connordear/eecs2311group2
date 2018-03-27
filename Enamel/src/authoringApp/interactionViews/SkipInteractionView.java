package authoringApp.interactionViews;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import authoringApp.interactionModels.SkipInteraction;

public class SkipInteractionView extends InteractionView {

	private JTextField skipTextField;
	private SkipInteraction skipModel;
	
	public SkipInteractionView(SkipInteraction s) {
		super(s.getInteraction());
		GridBagConstraints c = super.c;
		this.skipModel = s;
		
		this.skipTextField = new JTextField(this.skipModel.getSkipWord());
		this.skipTextField.getAccessibleContext().setAccessibleDescription("Enter the keyword you wish to specify here.");
		this.skipTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					skipModel.setSkipWord((e.getDocument().getText(0, e.getDocument().getLength())));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				} 
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					skipModel.setSkipWord((e.getDocument().getText(0, e.getDocument().getLength())));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					skipModel.setSkipWord((e.getDocument().getText(0, e.getDocument().getLength())));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(this.skipTextField, c);
		
		JLabel skipLabel = new JLabel("Skip To: ");
		skipLabel.setLabelFor(skipTextField);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		super.addRow(skipLabel, c);
	}

}
