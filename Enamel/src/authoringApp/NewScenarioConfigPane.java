package authoringApp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class NewScenarioConfigPane extends JPanel {
	private final int MIN_CELLS = 1;
	private final int MAX_CELLS = 8;
	private final int DEFAULT_CELLS = 1;
	
	private final int MIN_BUTTONS = 1;
	private final int MAX_BUTTONS = 10;
	private final int DEFAULT_BUTTONS = 4;
	
	private JLabel numCellsLbl, numBtnsLbl, statusLbl;
	private SpinnerModel cellSpinnerModel, btnSpinnerModel;
	private JSpinner cellSpinner, btnSpinner;
	private JButton createBtn, backBtn;
	private GridBagConstraints gbc;
	
	private ImageIcon iconCreate, iconBack;
	
	public NewScenarioConfigPane(MainViewController controller) {
		controller.getView().setTitle(MainFrame.APPLICATION_TITLE + ": New Scenario");
		setLayout(new GridBagLayout());
		cellSpinnerModel = new SpinnerNumberModel(DEFAULT_CELLS, MIN_CELLS, MAX_CELLS, 1);
		btnSpinnerModel = new SpinnerNumberModel(DEFAULT_BUTTONS, MIN_BUTTONS, MAX_BUTTONS, 1);
		numCellsLbl = new JLabel(String.format("Number of cells (%d - %d):", MIN_CELLS, MAX_CELLS));
		numBtnsLbl = new JLabel(String.format("Number of buttons (%d - %d):", MIN_BUTTONS, MAX_BUTTONS));
		statusLbl = new JLabel("");
		cellSpinner = new JSpinner(cellSpinnerModel);
		btnSpinner = new JSpinner(btnSpinnerModel);
		
		iconCreate = new ImageIcon(getClass().getResource("/assets/icon-create.png"));
		iconBack = new ImageIcon(getClass().getResource("/assets/icon-back.png"));
		
		createBtn = new JButton("Create");
		createBtn.setIcon(iconCreate);
		backBtn = new JButton("Back");
		backBtn.setIcon(iconBack);
		
		cellSpinner.getAccessibleContext().setAccessibleName("Number of braille cells");
		cellSpinner.getAccessibleContext().setAccessibleDescription("Set the number of braille cells");
		btnSpinner.getAccessibleContext().setAccessibleName("Number of buttons");
		btnSpinner.getAccessibleContext().setAccessibleDescription("Set the number of buttons");
		
		createBtn.getAccessibleContext().setAccessibleName("Create scenario");
		createBtn.getAccessibleContext().setAccessibleDescription("Create a new scenario with the set configurations");
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createScenario((int)cellSpinner.getValue(), (int)btnSpinner.getValue());
			}
		});
		backBtn.getAccessibleContext().setAccessibleName("Back");
		backBtn.getAccessibleContext().setAccessibleDescription("Return to the main page");
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.goBack();
			}
		});
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        add(numCellsLbl, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        add(cellSpinner, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        add(numBtnsLbl, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 0, 0, 10);
        add(btnSpinner, gbc);
		
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 0);
        gbc.weightx = 1;
        add(backBtn, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.weightx = 1;
        add(createBtn, gbc);
	}
}