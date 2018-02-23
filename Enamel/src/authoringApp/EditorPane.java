package authoringApp;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicArrowButton;

import authoringApp.Interaction.InteractionType;

public class EditorPane extends JPanel implements EditorView {
	private EditorController controller;
	
	private JSplitPane containerPane;
	private JScrollPane listPane;
	private JPanel configPane, controlsPane;
	private JList list;
	
	private CardLayout cards;
	
	private JComboBox newOptions;
	private JButton addBtn;
	private JButton delBtn;
	private JButton upBtn;
	private JButton downBtn;
	private JButton saveBtn;
	private JButton runBtn;
	
	private GridBagConstraints gbc;
	
	public EditorPane(EditorController controller) {
		setEditorViewController(controller);
		setLayout(new GridBagLayout());
		this.controller.setView(this);
		
		configPane = new JPanel(new CardLayout());
		cards = (CardLayout) configPane.getLayout();
		
		this.controller.createInteractionList();
		listPane = new JScrollPane(list);
		listPane.setMinimumSize(new Dimension(100, 50));
		
		containerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPane, configPane);
		containerPane.setOneTouchExpandable(true);
		containerPane.setDividerLocation(150);
		containerPane.setPreferredSize(new Dimension(400, 200));
		
		controlsPane = createControlsPane();
		toggleControls();
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(containerPane, gbc);
		
		gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(controlsPane, gbc);
	}
	
	void createInteractionList(CustomListModel<Interaction> model) {
		list = new JList(model);
		list.setDragEnabled(true);
		list.setDropMode(DropMode.INSERT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(0);
		list.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		list.addListSelectionListener(new ListSelectionListener() {
    		@Override
			public void valueChanged(ListSelectionEvent e) {
    			int selectedItemIndex = list.getSelectedIndex();
    			if (e.getValueIsAdjusting() == false) {
		            toggleControls();
		        }
    			controller.listItemSelected(selectedItemIndex);
			}
    	});
		
		createInteractionCards(model);
	}
	
	private JPanel createControlsPane() {
		JPanel c = new JPanel();
		c.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = GridBagConstraints.RELATIVE;
		gbc.gridy = GridBagConstraints.RELATIVE;
		
		newOptions = new JComboBox();
		for (Interaction.InteractionType iType : Interaction.InteractionType.values()) {
			newOptions.addItem(iType.getDescription());
		}
		
		addBtn = new JButton("Add");
		delBtn = new JButton("Delete");
		upBtn = new BasicArrowButton(BasicArrowButton.NORTH);
		downBtn = new BasicArrowButton(BasicArrowButton.SOUTH);
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInteraction(list.getSelectedIndex());
			}
		});
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteInteraction(list.getSelectedIndex());
			}
		});
		upBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveInteractionUp(list.getSelectedIndex());
			}
		});
		downBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveInteractionDown(list.getSelectedIndex());
			}
		});
		
		c.add(newOptions, gbc);
		c.add(addBtn, gbc);
		c.add(delBtn, gbc);
		c.add(upBtn, gbc);
		c.add(downBtn, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		saveBtn = new JButton("Save");
		runBtn = new JButton("Run");
		
		c.add(saveBtn, gbc);
		c.add(runBtn, gbc);
		
		return c;
	}
	
	public void setEditorViewController(EditorController controller) {
		this.controller = controller;
	}
	
	public EditorController getEditorViewController() {
		return this.controller;
	}

	public void addInteraction(int selectedIndex) {
		int size = list.getModel().getSize();
		
		if (list.getModel().getSize() != 0) {
			//List is not empty: enable delete, up, and down buttons.
            delBtn.setEnabled(true);
            upBtn.setEnabled(true);
            downBtn.setEnabled(true);
		}
		
		// If no selection or if item in last position is selected,
        // add the new one to end of list, and select new one.
		if (list.getModel().getSize() == 0 || selectedIndex == -1 || (selectedIndex + 1 == size)) {
			controller.addInteraction(newOptions.getSelectedIndex());
            list.setSelectedIndex(size);
		} else {
			// Otherwise insert the new one after the current selection,
	        // and select new one.
			controller.addInteraction(newOptions.getSelectedIndex(), selectedIndex + 1);
            list.setSelectedIndex(selectedIndex + 1);
		}
	}

	public void deleteInteraction(int selectedIndex) {
		if (selectedIndex != -1) {
			controller.deleteInteraction(selectedIndex);
		}

        int size = list.getModel().getSize();

        if (size == 0) {
            //List is empty: disable delete, up, and down buttons.
            delBtn.setEnabled(false);
            upBtn.setEnabled(false);
            downBtn.setEnabled(false);

        } else {
            // Adjust the selection.
            if (selectedIndex == list.getModel().getSize()) {
                // Removed item in last position.
            	selectedIndex--;
            }
            list.setSelectedIndex(selectedIndex);
        }
	}
	
	public void moveInteractionUp(int selectedIndex) {
		if (selectedIndex != 0) {
			controller.moveUp(selectedIndex);
			list.setSelectedIndex(selectedIndex - 1);
			list.ensureIndexIsVisible(selectedIndex - 1);
		}
	}
	
	public void moveInteractionDown(int selectedIndex) {
		if (selectedIndex != (list.getModel().getSize() - 1)) {
			controller.moveDown(selectedIndex);
			list.setSelectedIndex(selectedIndex + 1);
			list.ensureIndexIsVisible(selectedIndex + 1);
		}
	}

	public void showCard(String cardName) {
		cards.show(configPane, cardName);
	}
	
	public void addInteractionCard(Interaction i) {
		InteractionView intView = null;
		String interactionType = i.getType();
		
		if (interactionType.equals(Interaction.InteractionType.READ.getDescription())) {
			intView = new ReadInteractionView((ReadInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.CLEAR_BRAILLE.getDescription())) {
			intView = new CellClearInteractionView((CellClearInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.DISPLAY_BRAILLE.getDescription())) {
			intView = new DisplayBrailleInteractionView((DisplayBrailleInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.KEYWORD.getDescription())) {
			intView = new KeywordInteractionView((KeywordInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.PAUSE.getDescription())) {
			//JOptionPane.showMessageDialog(null, "Feature not implemented yet!", "Sorry", JOptionPane.WARNING_MESSAGE);
		} else if (interactionType.equals(Interaction.InteractionType.RESET_BUTTONS.getDescription())) {
			intView = new ResetButtonInteractionView((ResetButtonInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.SKIP_BUTTON.getDescription())) {
		} else if (interactionType.equals(Interaction.InteractionType.USER_INPUT.getDescription())) {
		} else if (interactionType.equals(Interaction.InteractionType.VOICE.getDescription())) {
		} else {
		}
		if (intView != null) {
			configPane.add(intView.getInteractionView(), Integer.toString(i.getId()));
			//configPane.add(intView.getInteractionView(), i.getId());
		}
	}
	
	public void deleteInteractionCard(Interaction i) {
		Component[] components = configPane.getComponents();
		
		for (Component c : configPane.getComponents()) {
			if (c.getName().equals(i.getId())) {
				cards.removeLayoutComponent(c);
			}
		}
	}
	
	public void createInteractionCards(CustomListModel<Interaction> model) {
		for (Interaction i : model) {
			addInteractionCard(i);
		}
	}
	
	public void toggleControls() {
		if (list.getSelectedIndex() == -1) {
            // No selection: disable delete, up, and down buttons.
            delBtn.setEnabled(false);
            upBtn.setEnabled(false);
            downBtn.setEnabled(false);
        } else if (list.getSelectedIndices().length > 1) {
            // Multiple selection: disable up and down buttons.
            delBtn.setEnabled(true);
            upBtn.setEnabled(false);
            downBtn.setEnabled(false);
        } else {
            // Single selection: permit all operations.
            delBtn.setEnabled(true);
            upBtn.setEnabled(true);
            downBtn.setEnabled(true);
        }
	}
	
	
}
