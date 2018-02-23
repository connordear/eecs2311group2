package authoringApp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class NewScenarioConfigPane extends JPanel {
	private MainViewController controller;
	
	private final int MIN_CELLS = 1;
	private final int MAX_CELLS = 8;
	private final int DEFAULT_CELLS = 1;
	
	private final int MIN_BUTTONS = 1;
	private final int MAX_BUTTONS = 10;
	private final int DEFAULT_BUTTONS = 4;
	
	private JLabel titleLbl, numCellsLbl, numBtnsLbl, statusLbl;
	private JTextField titleFld;
	private SpinnerModel cellSpinnerModel, btnSpinnerModel;
	private JSpinner cellSpinner, btnSpinner;
	private JButton createBtn, backBtn;
	private GridBagConstraints gbc;
	
	public NewScenarioConfigPane(MainViewController controller) {
		setLayout(new GridBagLayout());
		cellSpinnerModel = new SpinnerNumberModel(DEFAULT_CELLS, MIN_CELLS, MAX_CELLS, 1);
		btnSpinnerModel = new SpinnerNumberModel(DEFAULT_BUTTONS, MIN_BUTTONS, MAX_BUTTONS, 1);
		titleLbl = new JLabel("Scenario title:");
		numCellsLbl = new JLabel(String.format("Number of cells (%d - %d):", MIN_CELLS, MAX_CELLS));
		numBtnsLbl = new JLabel(String.format("Number of buttons (%d - %d):", MIN_BUTTONS, MAX_BUTTONS));
		statusLbl = new JLabel("");
		titleFld = new JTextField(260);
		cellSpinner = new JSpinner(cellSpinnerModel);
		btnSpinner = new JSpinner(btnSpinnerModel);
		createBtn = new JButton("Create");
		backBtn = new JButton("Back");
		
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.createScenario(titleFld.getText(),(int)cellSpinner.getValue(), (int)btnSpinner.getValue());
			}
		});
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.goBack();
			}
		});
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        add(titleLbl, gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        add(titleFld, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        add(numCellsLbl, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        add(cellSpinner, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        add(numBtnsLbl, gbc);
        
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 0, 0, 10);
        add(btnSpinner, gbc);
		
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 0);
        gbc.weightx = 1;
        add(backBtn, gbc);

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 10, 10);
        gbc.weightx = 1;
        add(createBtn, gbc);
	}
}
