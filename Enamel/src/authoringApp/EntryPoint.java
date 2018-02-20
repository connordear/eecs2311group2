package authoringApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import enamel.ScenarioParser;

/**
 *
 * @author Rob
 */
public class EntryPoint extends javax.swing.JFrame {

    /**
     * Creates new form EntryPoint
     */
    public EntryPoint() {
        initComponents();
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")                          
    private void initComponents() {

        homePanel = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        newButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        isVisualCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Authoring App");

        header.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        header.setText("Welcome to the Authoring App");

        newButton.setText("New Scenerio");

        editButton.setText("Edit Scenerio");

        runButton.setText("Run Scenerio");

        isVisualCheckBox.setText("I am a visually capable user");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(header))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(newButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(runButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(isVisualCheckBox)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header)
                .addGap(56, 56, 56)
                .addComponent(newButton)
                .addGap(27, 27, 27)
                .addComponent(editButton)
                .addGap(28, 28, 28)
                .addComponent(runButton)
                .addGap(18, 18, 18)
                .addComponent(isVisualCheckBox)
                .addGap(0, 54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(homePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        newButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		newButtonPressed();
        	}
        } );
        
        editButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editButtonPressed();
        	}
        });
        
        runButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      	    runButtonPressed();
      	  } 
      	} );
        
        pack();
    }
    
    protected void newButtonPressed() {
    	// To be implemented
    }
    
    protected void editButtonPressed() {
    	// To be implemented
    }
    
    protected void runButtonPressed() {
    	
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
            	ScenarioParser s = new ScenarioParser(isVisualCheckBox.isSelected());
        	    
        	    //Create a file chooser and set dialog box name (screen reader seems to only read dialog box name)
        	    final JFileChooser fc = new JFileChooser();
        	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        	    	    "Text File", "txt");
        	    fc.setFileFilter(filter);
        	    fc.setDialogTitle("Open Scenario File");
        	    
        	    // Set accessible descriptions for screen reader (screen reader is not reading these)
        	    fc.getAccessibleContext().setAccessibleName("File chooser dialog box");
        	    fc.getAccessibleContext().setAccessibleDescription("Please choose a scenario file from this file dialog box.");
        	    
        	    int returnVal = fc.showOpenDialog(null);

        	    if (returnVal == JFileChooser.APPROVE_OPTION) {
        	    	String fileName = fc.getSelectedFile().getPath();
        	    	
        	    	// Call to begin drawing UI
        	        s.setScenarioFile(fileName);
        	    }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntryPoint().setVisible(true);
            }
        });
    }
                    
    private javax.swing.JButton editButton;
    private javax.swing.JLabel header;
    private javax.swing.JPanel homePanel;
    private javax.swing.JCheckBox isVisualCheckBox;
    private javax.swing.JButton newButton;
    private javax.swing.JButton runButton;
               
}