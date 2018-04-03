package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import authoringApp.interactionModels.SkipButtonInteraction;

public class SkipButtonInteractionView extends InteractionView {

	private SkipButtonInteraction skipModel;
	private JTextField keywordField;
	private JComboBox<Integer> buttonComboBox;
	public JLabel buttonLabel = new JLabel("Button: ");
	public JLabel keywordLabel = new JLabel("Keyword: ");

	public SkipButtonInteractionView(SkipButtonInteraction sb) {
		super(sb.getInteraction());
		this.skipModel = sb;
		GridBagConstraints c = super.c;
		
		this.buttonComboBox = new JComboBox<Integer>();
		this.buttonComboBox.getAccessibleContext().setAccessibleDescription("Specify which button you wish to link here.");
		for (int i = 0; i < this.skipModel.getNumButtons(); i++) {
			this.buttonComboBox.addItem(i + 1);
		}
		this.buttonComboBox.setSelectedIndex(this.skipModel.getButton());
		this.buttonComboBox.addItemListener(new ItemListener() {
			@Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		          Object item = event.getItem();
		          skipModel.setButton((Integer) item - 1);
		       }
		    }    
		});
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(this.buttonComboBox, c);
		c.gridx = 0;
		c.gridy = 1;
		super.addRow(this.buttonLabel, c);
		
		this.keywordField = new JTextField(this.skipModel.getKeyword());
		this.keywordField.getAccessibleContext().setAccessibleDescription("Specify which keyword you wish to link with this button press here.");
		this.keywordField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  try {
						skipModel.setKeyword(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					System.out.println("Insert Update: " + skipModel.getKeyword());
			  }
			  public void removeUpdate(DocumentEvent e) {
				  try {
						skipModel.setKeyword(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					System.out.println("Insert Update: " + skipModel.getKeyword());
			  }
			  public void insertUpdate(DocumentEvent e) {
				  try {
						skipModel.setKeyword(e.getDocument().getText(0, e.getDocument().getLength()));
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					System.out.println("Insert Update: " + skipModel.getKeyword());
			  }
		});
		c.gridx = 1;
		c.gridy = 2;
		super.addRow(this.keywordField, c);
		
		c.gridx = 0;
		super.addRow(this.keywordLabel, c);
		
	}
}
