package authoringApp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import authoringApp.dndList.ListItemTransferHandler;

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
	
	private static JList list;
	
	/**
     * Create the GUI and show it.  For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */	
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
        //frame.pack(); it will automatically change the size of the frames according to the size of components in it
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
    	// Left pane (list of interactions)
    	String[] interactions = { "Read Out", "Wait", "Get Input"};
    	list = new JList(interactions);
    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	list.setSelectedIndex(0);
    	list.setTransferHandler(new ListItemTransferHandler());
    	list.setDropMode(DropMode.INSERT);
    	list.setDragEnabled(true);
    	list.setLayoutOrientation(JList.VERTICAL);
    	list.setVisibleRowCount(0);
    	list.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//    	list.setCellRenderer(new ListCellRenderer<Thumbnail>() {
//    		private final JPanel p = new JPanel(new BorderLayout());
//    		private final JLabel icon = new JLabel((Icon)null, JLabel.CENTER);
//    		private final JLabel label = new JLabel("", JLabel.CENTER);
//
//    		@Override
//    		public Component getListCellRendererComponent(
//    				JList<? extends Thumbnail> list, Thumbnail value, int index,
//    				boolean isSelected, boolean cellHasFocus) {
//    			icon.setIcon(value.icon);
//    	        label.setText(value.name);
//    	        label.setForeground(isSelected ? list.getSelectionForeground()
//    	                            : list.getForeground());
//    	        p.add(icon);
//    	        p.add(label, BorderLayout.SOUTH);
//    	        p.setBackground(isSelected ? list.getSelectionBackground()
//    	                        : list.getBackground());
//    	        return p;
//    		}
//    	    });
    	
    	interactionListPane = new JScrollPane(list);
    	// Provide minimum sizes for the two components in the split pane.
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
                createAndShowGUI();
            }
        });
    }
	
}
