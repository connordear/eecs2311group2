package authoringApp;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class ReadInteractionView extends InteractionView {

	private JTextArea dataField;
	private ReadInteraction readModel;
	JLabel dataLabel = new JLabel("Data: ");
	
	public ReadInteractionView(ReadInteraction r) {
		super(r.getInteraction());
		GridBagConstraints c = super.c;
		this.readModel = r;
		
		// Data Field
		this.dataField = new JTextArea(this.readModel.getData());
		this.dataField.getAccessibleContext().setAccessibleDescription("Type the text to be read to a user here.");
		this.dataField.setLineWrap(true);
	    this.dataField.setWrapStyleWord(true);
		JScrollPane sp = new JScrollPane(this.dataField);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(sp, c);
		
		// Data Label
		this.dataLabel.setLabelFor(this.dataField);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		super.addRow(this.dataLabel, c);
		
		this.dataField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try {
					ReadInteractionView.this.readModel.setData(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(ReadInteractionView.this.getData());
			}
			public void removeUpdate(DocumentEvent e) {
				try {
					ReadInteractionView.this.readModel.setData(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Remove Update" + ReadInteractionView.this.getData());
			}
			public void insertUpdate(DocumentEvent e) {
				try {
					ReadInteractionView.this.readModel.setData(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Insert Update: " + ReadInteractionView.this.getData());
			}
		});
	}
		
	public String getData() {
		return this.readModel.getData();
	}
	
	public boolean setData(String d) {
		this.readModel.setData(d);
		return true;
	}

	

}
