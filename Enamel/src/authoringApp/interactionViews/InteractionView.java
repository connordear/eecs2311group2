package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import authoringApp.interactionModels.Interaction;

public class InteractionView {

	protected Interaction interactionModel;
	protected JPanel interactionView;
	protected GridBagConstraints c;
	private int currentRow;

	public InteractionView(Interaction i) {
		this.currentRow = 0;
		this.interactionModel = i;
		this.interactionView = new JPanel();
		this.interactionView.setLayout(new GridBagLayout());
		this.c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		c.ipadx = 10;
		c.ipady = 10;
		c.gridheight = 1;
		c.gridwidth = 1;
		// Title Field
		JTextField titleField = new JTextField(this.interactionModel.getTitle());
		titleField.getAccessibleContext().setAccessibleDescription("Type the title of this Interaction here.");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0;
		this.interactionView.add(titleField, c);

		// Title Label
		JLabel titleLabel = new JLabel("Title: ");
		titleLabel.setLabelFor(titleField);
		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		this.interactionView.add(titleLabel, c);
		this.currentRow++;

		titleField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try {
					InteractionView.this.interactionModel
							.setTitle(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(InteractionView.this.getTitle());
			}

			public void removeUpdate(DocumentEvent e) {
				try {
					InteractionView.this.interactionModel
							.setTitle(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Remove Update, new title is: " + InteractionView.this.getTitle());
			}

			public void insertUpdate(DocumentEvent e) {
				try {
					InteractionView.this.interactionModel
							.setTitle(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Insert Update, new title is: " + InteractionView.this.getTitle());
			}

			public void warn() {
				System.out.println("Problem");
			}
		});
	}

	public JPanel getInteractionView() {
		return this.interactionView;
	};

	public void addRow(JComponent j, GridBagConstraints c) {
		// Need to change Read View to adding in a JFrame instead of Components?
		// c.gridy = currentRow;
		this.interactionView.add(j, c);
		this.currentRow++;
	}

	public String getTitle() {
		return this.interactionModel.getTitle();
	};

	public boolean setTitle(String s) {
		return this.interactionModel.setTitle(s);
	};

	public String getType() {
		return this.interactionModel.getType();
	};

}
