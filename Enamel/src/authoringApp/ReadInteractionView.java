package authoringApp;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class ReadInteractionView extends InteractionView {

	private JTextField titleField;
	private JTextField dataField;
	private ReadInteraction readModel;
	
	public ReadInteractionView(ReadInteraction r) {
		super();
		this.readModel = r;
		this.titleField = new JTextField(this.readModel.getTitle());
		this.dataField = new JTextField(this.readModel.getData());
		this.interactionView.add(this.titleField);
		this.interactionView.add(this.dataField);
		this.titleField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try {
					ReadInteractionView.this.readModel.setData(e.getDocument().getText(0, e.getDocument().getLength() - 1));
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void removeUpdate(DocumentEvent e) {
//				try {
//					ReadInteractionView.this.readModel.setData(e.getDocument().getText(0, e.getDocument().getLength() - 1));
//				} catch (BadLocationException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			  }
			  public void insertUpdate(DocumentEvent e) {
//				  try {
//						ReadInteractionView.this.readModel.setData(e.getDocument().getText(0, e.getDocument().getLength() - 1));
//					} catch (BadLocationException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
			  }

			  public void warn() {
			     System.out.println("Problem");
			  }
		});
		
		
		this.dataField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
			     System.out.println("Problem");
			  }
		});
		
		
		
	}
	
	public String getTitle() {
		return this.readModel.getTitle();
	}
	
	public void setTitle(String s) {
		this.readModel.setTitle(s);
	}
	
	public String getData() {
		return this.readModel.getData();
	}
	
	public boolean setData(String d) {
		this.readModel.setData(d);
		return true;
	}
	
	
	@Override
	public String getType() {
		return InteractionView.READ;
	}
	

}
