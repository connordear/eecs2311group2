package authoringApp;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicArrowButton;

public class ScenarioEditorView {
	// Frame
	private static JFrame frame;

	// Menus
	private static JMenuBar menuBar;
	private static JMenu fileMenu;
	private static JMenuItem newFile, openFile, saveFile, saveAsFile, exit;

	// Editor containers
	private static JSplitPane designerPane;
	private static JScrollPane interactionListPane;
	private static JPanel interactionEditorPanel;
	private static JPanel controlsPanel;

	// Buttons
	private static JButton addIntButton;
	private static JButton removeIntButton;
	private static JButton moveUpIntButton;
	private static JButton moveDownIntButton;

	// Layout managers
	private static GridBagConstraints c;

	private static JList list;

	public static Scenario test = new Scenario(new File("./FactoryScenarios/Scenario_1.txt"));

	private static void createAndShowGUI() {
		InitMenu();
		InitInteractionPanel();
		InitControlsPanel();

		// Create frame
		frame = new JFrame("Braille Author");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.ipadx = 10;
		c.ipady = 10;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;

		frame.getContentPane().add(designerPane, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.getContentPane().add(controlsPanel, c);

		// Display the window
		frame.setSize(600, 600);
		// frame.pack();
		frame.setVisible(true);
	}

	private static void InitMenu() {
		// Create menu
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		fileMenu.getAccessibleContext().setAccessibleDescription(
				"File menu which contains options to create new scenario file or open an existing one.");

		// File menu items
		newFile = new JMenuItem("New");
		openFile = new JMenuItem("Open");
		saveFile = new JMenuItem("Save");
		saveAsFile = new JMenuItem("Save As");
		exit = new JMenuItem("Exit");

		fileMenu.add(newFile);
		fileMenu.add(openFile);
		fileMenu.addSeparator();
		fileMenu.add(saveFile);
		fileMenu.add(saveAsFile);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		menuBar.add(fileMenu);
	}

	private static void InitInteractionList() {

		list = new JList(test.interactionList);
		list.setDragEnabled(true);
		list.setDropMode(DropMode.INSERT);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int itemIndex = e.getFirstIndex();
				Interaction currentInteraction = test.interactionList.get(itemIndex);
				String interactionId = Integer.toString(currentInteraction.getId());
				CardLayout cards = (CardLayout) interactionEditorPanel.getLayout();
				cards.show(interactionEditorPanel, interactionId);
			}
		});

		// list.setTransferHandler(new ListItemTransferHandler());
		// list.setTransferHandler(new TransferHandler() {
		// private int index;
		// private boolean beforeIndex = false; //Start with `false` therefore if it is
		// removed from or added to the list it still works
		//
		// @Override
		// public int getSourceActions(JComponent comp) {
		// return MOVE;
		// }
		//
		// @Override
		// public Transferable createTransferable(JComponent comp) {
		// index = list.getSelectedIndex();
		// return new StringSelection((String) list.getSelectedValue());
		// }
		//
		// @Override
		// public void exportDone(JComponent comp, Transferable trans, int action) {
		// if (action == MOVE) {
		// if (beforeIndex)
		// test.interactionList.remove(index + 1);
		// else
		// test.interactionList.remove(index);
		// }
		// }
		//
		// @Override
		// public boolean canImport(TransferHandler.TransferSupport support) {
		// return support.isDataFlavorSupported(DataFlavor.stringFlavor);
		// }
		//
		// @Override
		// public boolean importData(TransferHandler.TransferSupport support) {
		// try {
		// Interaction t = (Interaction)
		// support.getTransferable().getTransferData(DataFlavor.);
		// String s = (String)
		// support.getTransferable().getTransferData(DataFlavor.stringFlavor);
		// JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
		// test.interactionList.add(dl.getIndex(), );
		// beforeIndex = dl.getIndex() < index ? true : false;
		// return true;
		// } catch (UnsupportedFlavorException | IOException e) {
		// e.printStackTrace();
		// }
		//
		// return false;
		// }
		// });

		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(0);
		list.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		interactionListPane = new JScrollPane(list);
		Dimension minimumSize = new Dimension(100, 50);
		interactionListPane.setMinimumSize(minimumSize);
	}

	private static void InitInteractionEditor() {
		// Right pane (controls for editing)
		interactionEditorPanel = new JPanel(new CardLayout());
		// Provide minimum sizes for the two components in the split pane.
		Dimension minimumSize = new Dimension(100, 50);
		interactionEditorPanel.setMinimumSize(minimumSize);
		CreateInteractionCards(test.interactionList);
	}

	private static void InitInteractionPanel() {
		InitInteractionList();
		InitInteractionEditor();

		// Split pane
		designerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, interactionListPane, interactionEditorPanel);
		designerPane.setOneTouchExpandable(true);
		designerPane.setDividerLocation(150);

		// Provide a preferred size for the split pane.
		designerPane.setPreferredSize(new Dimension(400, 200));
	}

	private static void InitControlsPanel() {
		controlsPanel = new JPanel();
		addIntButton = new JButton("+");
		removeIntButton = new JButton("-");
		moveUpIntButton = new BasicArrowButton(BasicArrowButton.NORTH);
		moveDownIntButton = new BasicArrowButton(BasicArrowButton.SOUTH);

		controlsPanel.add(addIntButton);
		controlsPanel.add(removeIntButton);
		controlsPanel.add(moveUpIntButton);
		controlsPanel.add(moveDownIntButton);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException ex) {
				} catch (IllegalAccessException ex) {
				} catch (UnsupportedLookAndFeelException ex) {
				}

				createAndShowGUI();
			}
		});
	}

	protected class DesignerPane extends JPanel {
		public DesignerPane() {

		}
	}

	private static void CreateInteractionCards(CustomListModel<Interaction> list) {
		for (Interaction i : list) {
			switch (i.getType()) {
			case Interaction.READ:
				interactionEditorPanel.add(new ReadInteractionView((ReadInteraction) i).getInteractionView(),
						Integer.toString(i.getId()));
			default:
				System.out.println("Not there yet...");
			}
		}
	}

}
