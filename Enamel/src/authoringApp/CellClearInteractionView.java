package authoringApp;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class CellClearInteractionView extends InteractionView {

	private JComboBox<Integer> comboBoxNumbers;
	private CellClearInteraction cellClearModel;
	
	
	public CellClearInteractionView(CellClearInteraction cellClearInteraction) {
		super(cellClearInteraction.getInteraction());
		GridBagConstraints c = super.c;
		this.cellClearModel = cellClearInteraction;
		this.comboBoxNumbers = new JComboBox<Integer>();
		for (int i = 0; i < 20; i++) {
			this.comboBoxNumbers.addItem(i);
		}
		this.comboBoxNumbers.setSelectedIndex(this.cellClearModel.getCellNumber());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(this.comboBoxNumbers, c);
//		this.comboBoxNumbers.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				
//			}
//		});
	}

	@Override
	public String getTitle() {
		return this.cellClearModel.getTitle();
	}

	@Override
	public boolean setTitle(String s) {
		return this.cellClearModel.setTitle(s);
	
	}

	@Override
	public String getType() {
		return this.cellClearModel.getType();
	}

}
