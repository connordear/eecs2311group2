package authoringApp;

import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
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
	
	private static JList<String> list;
	
    private static void createAndShowGUI() {
    	InitMenu();
    	InitInteractionPanel();
    	
    	// Create frame
        frame = new JFrame("Braille Author");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(designerPane);
        
        // Display the window
        frame.setSize(600, 600);
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
    	Scenario test = new Scenario(new File("./FactoryScenarios/Scenario_1.txt"));

    	list = new JList(test.interactionList);
    	list.setDragEnabled(true);
    	list.setDropMode(DropMode.INSERT);
    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	list.setSelectedIndex(0);
    	list.setTransferHandler(new ListItemTransferHandler());
//    	list.setTransferHandler(new TransferHandler() {
//            private int index;
//            private boolean beforeIndex = false; //Start with `false` therefore if it is removed from or added to the list it still works
//
//            @Override
//            public int getSourceActions(JComponent comp) {
//                return MOVE;
//            }
//
//            @Override
//            public Transferable createTransferable(JComponent comp) {
//                index = list.getSelectedIndex(); 
//                return new StringSelection((String) list.getSelectedValue());
//            }
//
//            @Override
//            public void exportDone(JComponent comp, Transferable trans, int action) {
//                if (action == MOVE) {
//                    if (beforeIndex)
//                    	test.interactionList.remove(index + 1);
//                    else
//                    	test.interactionList.remove(index);
//                }
//            }
//
//            @Override
//            public boolean canImport(TransferHandler.TransferSupport support) {
//                return support.isDataFlavorSupported(DataFlavor.stringFlavor);
//            }
//
//            @Override
//            public boolean importData(TransferHandler.TransferSupport support) {
//                try {
//                	Interaction t = (Interaction) support.getTransferable().getTransferData(DataFlavor.);
//                    String s = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
//                    JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();
//                    test.interactionList.add(dl.getIndex(), );
//                    beforeIndex = dl.getIndex() < index ? true : false;
//                    return true;
//                } catch (UnsupportedFlavorException | IOException e) {
//                    e.printStackTrace();
//                }
//
//                return false;
//            }
//        });
    	
    	list.setLayoutOrientation(JList.VERTICAL);
    	list.setVisibleRowCount(0);
    	list.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    	interactionListPane = new JScrollPane(list);
        Dimension minimumSize = new Dimension(100, 50);
        interactionListPane.setMinimumSize(minimumSize);
    }
    
    private static void InitInteractionEditor() {
    	// Right pane (controls for editing)
    	interactionEditorPanel = new JPanel();
    	// Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        interactionEditorPanel.setMinimumSize(minimumSize);
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
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
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
	
}
