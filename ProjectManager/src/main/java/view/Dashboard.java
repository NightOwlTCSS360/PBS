/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import javax.swing.JFileChooser;
/**
 * @author Jarvis Kampe
 */
public class Dashboard extends javax.swing.JFrame {
    
    /**
     * The file used by the dashboard
     */
    private File myFile;
    
    /**
     * The username used by the dashboard
     */
    private String myUsername;
    
    /**
     * The email used by the dashboard
     */
    private String myEmail;
    
    //PDC controller = new PDC();
    
    /**
     * Creates new Dashboard
     * @author Jarvis Kampe
     */
    public Dashboard(/*PDC controller*/) {
        //this.controller = controller;
        //Set myUsername
        //Set myEmail
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        settingsDialog = new javax.swing.JDialog();
        usernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        saveSettingsButton = new javax.swing.JButton();
        cancelSettingsButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        createProjectMenuItem = new javax.swing.JMenuItem();
        importProjectsMenuItem = new javax.swing.JMenuItem();
        exportProjectsMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        editSettingsMenuItem = new javax.swing.JMenuItem();
        importSettingsMenuItem = new javax.swing.JMenuItem();
        exportSettingsMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        usernameTextField.setText(myUsername);

        usernameLabel.setText("Username:");

        emailTextField.setText(myEmail);

        emailLabel.setText("Email:");

        saveSettingsButton.setText("Apply");
        saveSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSettingsAction(evt);
            }
        });

        cancelSettingsButton.setText("Cancel");
        cancelSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSettingsAction(evt);
            }
        });

        javax.swing.GroupLayout settingsDialogLayout = new javax.swing.GroupLayout(settingsDialog.getContentPane());
        settingsDialog.getContentPane().setLayout(settingsDialogLayout);
        settingsDialogLayout.setHorizontalGroup(
            settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsDialogLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(emailTextField))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelSettingsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveSettingsButton)
                .addContainerGap())
        );
        settingsDialogLayout.setVerticalGroup(
            settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsDialogLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveSettingsButton)
                    .addComponent(cancelSettingsButton))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        createProjectMenuItem.setText("Create Project");
        fileMenu.add(createProjectMenuItem);

        importProjectsMenuItem.setText("Import Projects");
        importProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileAction(evt);
            }
        });
        fileMenu.add(importProjectsMenuItem);

        exportProjectsMenuItem.setText("Export Projects");
        exportProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileAction(evt);
            }
        });
        fileMenu.add(exportProjectsMenuItem);

        jMenuBar1.add(fileMenu);

        settingsMenu.setText("Settings");

        editSettingsMenuItem.setText("Edit Settings");
        editSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSettingsAction(evt);
            }
        });
        settingsMenu.add(editSettingsMenuItem);

        importSettingsMenuItem.setText("Import Settings");
        importSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileAction(evt);
            }
        });
        settingsMenu.add(importSettingsMenuItem);

        exportSettingsMenuItem.setText("Export Settings");
        exportSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileAction(evt);
            }
        });
        settingsMenu.add(exportSettingsMenuItem);

        jMenuBar1.add(settingsMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutAction(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Return the file retrieved by the dashboard
     * @return the file held in the dashboard
     * @author Jarvis Kampe
     */
    public File getFile() {
        return myFile;
    }
    
    /**
     * Return the username retrieved by the dashboard
     * @return the username held in the dashboard
     * @author Jarvis Kampe
     */
    public String getUsername() {
        return myUsername;
    }
    
    /**
     * Return the email retrieved by the dashboard
     * @return the email held in the dashboard
     * @author Jarvis Kampe
     */
    public String getEmail() {
        return myEmail;
    }
    
    /**
     * Edit settings behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void editSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSettingsAction
        settingsDialog.pack();
        settingsDialog.setLocationRelativeTo(null);
        settingsDialog.setVisible(true);
    }//GEN-LAST:event_editSettingsAction

    /**
     * Open file behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void openFileAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileAction
        int returnVal = jFileChooser1.showOpenDialog(Dashboard.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            myFile = jFileChooser1.getSelectedFile();
        }
    }//GEN-LAST:event_openFileAction

    /**
     * Save settings behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void saveSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSettingsAction
        myUsername = usernameTextField.getText();
        myEmail = emailTextField.getText();
        settingsDialog.setVisible(false);
    }//GEN-LAST:event_saveSettingsAction

    /**
     * Cancel settings behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void cancelSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSettingsAction
        settingsDialog.setVisible(false);
    }//GEN-LAST:event_cancelSettingsAction

    /**
     * Open about behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void aboutAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutAction
        //Open about panel
        //I can't do it because this thing is a frame sorry
    }//GEN-LAST:event_aboutAction
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // /* Set the Nimbus look and feel */
        // //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        // /* If Nimbus (introduced in Java SE 6) is not available, stay with the
        // default look and feel.
        // * For details see
        // http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        // */
        // try {
        // for (javax.swing.UIManager.LookAndFeelInfo info :
        // javax.swing.UIManager.getInstalledLookAndFeels()) {
        // if ("Nimbus".equals(info.getName())) {
        // javax.swing.UIManager.setLookAndFeel(info.getClassName());
        // break;
        // }
        // }
        // } catch (ClassNotFoundException ex) {
        // java.util.logging.Logger.getLogger(StartGUI.class.getName()).log(java.util.logging.Level.SEVERE,
        // null, ex);
        // } catch (InstantiationException ex) {
        // java.util.logging.Logger.getLogger(StartGUI.class.getName()).log(java.util.logging.Level.SEVERE,
        // null, ex);
        // } catch (IllegalAccessException ex) {
        // java.util.logging.Logger.getLogger(StartGUI.class.getName()).log(java.util.logging.Level.SEVERE,
        // null, ex);
        // } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        // java.util.logging.Logger.getLogger(StartGUI.class.getName()).log(java.util.logging.Level.SEVERE,
        // null, ex);
        // }
        // //</editor-fold>
        FlatLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton cancelSettingsButton;
    private javax.swing.JMenuItem createProjectMenuItem;
    private javax.swing.JMenuItem editSettingsMenuItem;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JMenuItem exportProjectsMenuItem;
    private javax.swing.JMenuItem exportSettingsMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importProjectsMenuItem;
    private javax.swing.JMenuItem importSettingsMenuItem;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton saveSettingsButton;
    private javax.swing.JDialog settingsDialog;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
