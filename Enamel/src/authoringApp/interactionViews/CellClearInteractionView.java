package authoringApp.interactionViews;

import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import authoringApp.interactionModels.CellClearInteraction;

public class CellClearInteractionView extends InteractionView {

	private JComboBox<Integer> comboBoxNumbers;
	private CellClearInteraction cellClearModel;
	JLabel cellNumberLabel = new JLabel("Cell Number: ");
	
	public CellClearInteractionView(CellClearInteraction cellClearInteraction) {
		super(cellClearInteraction.getInteraction());
		GridBagConstraints c = super.c;
		this.cellClearModel = cellClearInteraction;
		
		this.comboBoxNumbers = new JComboBox<Integer>();
		this.comboBoxNumbers.getAccessibleContext().setAccessibleDescription("Specify which Cell number you wish to clear.");
		for (int i = 0; i < cellClearModel.getNumCells(); i++) {
			this.comboBoxNumbers.addItem(i);
		}
		this.comboBoxNumbers.setSelectedIndex(this.cellClearModel.getCellNumber());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		super.addRow(this.comboBoxNumbers, c);
		this.comboBoxNumbers.addItemListener(new ItemListener() {
			@Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
		          Object item = event.getItem();
		          cellClearModel.setCellNumber((Integer) item);
		       }
		    }    
		});
		c.gridx = 0;
		c.gridy = 1;
		this.cellNumberLabel.setLabelFor(comboBoxNumbers);
		super.addRow(this.cellNumberLabel, c);
	}

}