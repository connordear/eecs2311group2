package authoringApp;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.basic.BasicArrowButton;

import authoringApp.interactionModels.CellClearInteraction;
import authoringApp.interactionModels.DisplayBrailleInteraction;
import authoringApp.interactionModels.Interaction;
import authoringApp.interactionModels.KeywordInteraction;
import authoringApp.interactionModels.PauseInteraction;
import authoringApp.interactionModels.ReadInteraction;
import authoringApp.interactionModels.ResetButtonInteraction;
import authoringApp.interactionModels.SkipButtonInteraction;
import authoringApp.interactionModels.SkipInteraction;
import authoringApp.interactionModels.UserInputInteraction;
import authoringApp.interactionModels.VoiceInteraction;
import authoringApp.interactionViews.CellClearInteractionView;
import authoringApp.interactionViews.DisplayBrailleInteractionView;
import authoringApp.interactionViews.InteractionView;
import authoringApp.interactionViews.KeywordInteractionView;
import authoringApp.interactionViews.PauseInteractionView;
import authoringApp.interactionViews.ReadInteractionView;
import authoringApp.interactionViews.ResetButtonInteractionView;
import authoringApp.interactionViews.SkipButtonInteractionView;
import authoringApp.interactionViews.SkipInteractionView;
import authoringApp.interactionViews.UserInputInteractionView;
import authoringApp.interactionViews.VoiceInteractionView;
import enamel.ScenarioParser;

public class EditorPane extends JPanel {
	
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
		// Create dummy config pane to solve issue of not displaying the thing
		JPanel test = new JPanel();
		test.setName("dummy");
		configPane.add(test);
		cards.show(configPane, "dummy");
		
		this.controller.createInteractionList();
		listPane = new JScrollPane(list);
		listPane.setMinimumSize(new Dimension(100, 50));
		
		containerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPane, configPane);
		containerPane.setOneTouchExpandable(true);
		containerPane.setDividerLocation(350);
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
		addBtn.getAccessibleContext().setAccessibleName("Add New Interaction");
		addBtn.getAccessibleContext().setAccessibleDescription("Click here to open a dropdown menu listing all possible interactions to add to the scenario.");
		delBtn = new JButton("Delete");
		delBtn.getAccessibleContext().setAccessibleName("Delete Interaction");
		delBtn.getAccessibleContext().setAccessibleDescription("Click here to delete the currently selected interaction.");
		upBtn = new BasicArrowButton(BasicArrowButton.NORTH);
		upBtn.getAccessibleContext().setAccessibleName("Move Interaction Up");
		upBtn.getAccessibleContext().setAccessibleDescription("Click here to move the currently selected interaction up");
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
		
		saveBtn.getAccessibleContext().setAccessibleDescription("Save Scenario");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		runBtn.getAccessibleContext().setAccessibleDescription("Run Scenario in simulator");
		runBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread starterCodeThread = new Thread("Starter Code Thread") {
				    public void run() {
				        ScenarioParser s = new ScenarioParser(true);
				        s.setScenarioFile(controller.getModel().getPath());
				    }
				};
				starterCodeThread.start();
			}
		});
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
		
		if (list.getModel().getSize() != 0) {
			//List is not empty: enable delete, up, and down buttons.
            delBtn.setEnabled(true);
            upBtn.setEnabled(true);
            downBtn.setEnabled(true);
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
			intView = new PauseInteractionView((PauseInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.RESET_BUTTONS.getDescription())) {
			intView = new ResetButtonInteractionView((ResetButtonInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.SKIP_BUTTON.getDescription())) {
			intView = new SkipButtonInteractionView((SkipButtonInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.SKIP.getDescription())) {
			intView = new SkipInteractionView((SkipInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.USER_INPUT.getDescription())) {
			intView = new UserInputInteractionView((UserInputInteraction) i);
		} else if (interactionType.equals(Interaction.InteractionType.VOICE.getDescription())) {
			intView = new VoiceInteractionView((VoiceInteraction) i);
		} else {
		}
		if (intView != null) {
			configPane.add(intView.getInteractionView(), Integer.toString(i.getId()));
			showCard(Integer.toString(i.getId()));
		}
	}
	
	public void deleteInteractionCard(Interaction i) {
		for (Component c : configPane.getComponents()) {
			if (c.getName() != null) {
				System.out.println(c.getName());
				if (c.getName().equals(Integer.toString(i.getId()))) {
					System.out.println(c.getName());
					cards.removeLayoutComponent(c);
				}
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
	
	public void saveFile() {
		File sf = new File(controller.getModel().getPath());
		if (sf.isFile() && !sf.isDirectory()) {
			try {
				controller.getModel().generateScenarioText();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error",
						"Error saving scenario file!",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		} else {
			JFileChooser fileChooser = new JFileChooser();
			FileFilter txtFilter = new FileFilter() {
				@Override
				public String getDescription() {
					return "Text File (*.TXT)";
				}

				@Override
				public boolean accept(File file) {
					if (file.isDirectory()) {
						return true;
					} else {
						return file.getName().toLowerCase().endsWith(".txt");
					}
				}
			};

			fileChooser.setFileFilter(txtFilter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			
			int userChoice = fileChooser.showSaveDialog(null);
			if (userChoice == JFileChooser.APPROVE_OPTION) {
				String saveFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				File f = new File(saveFilePath);
				if (f.isFile() && !f.isDirectory()) {
					int overwriteExistingFile = 0;
					overwriteExistingFile = JOptionPane.showConfirmDialog(null, "The file already exists. Replace existing file?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (overwriteExistingFile != JOptionPane.YES_OPTION) {
						return;
					}
				}
				
				if (!saveFilePath.toLowerCase().endsWith(".txt")) {
					saveFilePath += ".txt";
				}
				controller.getModel().setPath(saveFilePath);
				
				try {
					controller.getModel().generateScenarioText();
					JOptionPane.showMessageDialog(null, "Save",
							"Scenario file saved.",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error",
							"Error saving scenario file!",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
	}
	
	
}
