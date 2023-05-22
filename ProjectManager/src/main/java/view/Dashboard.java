/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import control.PDC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.User;
import model.projectdata.Project;

/**
 * @author Jarvis Kampe
 */
public class Dashboard extends javax.swing.JFrame {
    
    /**
     * The controller used by the dashboard
     */
    private PDC myController = new PDC();
    
    /**
     * Creates new Dashboard
     * @param controller
     * @author Jarvis Kampe
     */
    public Dashboard(PDC controller) {
        myController = controller;
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
        createProjectDialog = new javax.swing.JDialog();
        projectNameTextField = new javax.swing.JTextField();
        projectNameLabel = new javax.swing.JLabel();
        createProjectButton = new javax.swing.JButton();
        cancelProjectButton = new javax.swing.JButton();
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

        usernameLabel.setText("First Name:");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsDialogLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(settingsDialogLayout.createSequentialGroup()
                        .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(settingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTextField)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cancelSettingsButton))
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

        projectNameLabel.setText("Project Name:");

        createProjectButton.setText("Create");
        createProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProjectAction(evt);
            }
        });



        cancelProjectButton.setText("Cancel");
        cancelProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelProjectAction(evt);
            }
        });

        javax.swing.GroupLayout createProjectDialogLayout = new javax.swing.GroupLayout(createProjectDialog.getContentPane());
        createProjectDialog.getContentPane().setLayout(createProjectDialogLayout);
        createProjectDialogLayout.setHorizontalGroup(
            createProjectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createProjectDialogLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(createProjectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(createProjectDialogLayout.createSequentialGroup()
                        .addComponent(projectNameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cancelProjectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createProjectButton)
                .addContainerGap())
        );
        createProjectDialogLayout.setVerticalGroup(
            createProjectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createProjectDialogLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(createProjectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projectNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addGroup(createProjectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createProjectButton)
                    .addComponent(cancelProjectButton))
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
        createProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProjectAction(evt);
                createProjectMenuAction(evt);
            }
        });
        fileMenu.add(createProjectMenuItem);

        importProjectsMenuItem.setText("Import Projects");
        importProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Create a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();

                // Set the file chooser to accept only files with the ".ser" extension
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files", "ser");
                fileChooser.setFileFilter(filter);

                // Show the dialog and wait for the user to select a file
                int result = fileChooser.showOpenDialog(Dashboard.this); // 'Dashboard.this' refers to the current instance of the Dashboard

                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    File selectedFile = fileChooser.getSelectedFile();

                    // Call the importProject method in the PDC controller and pass the selected file
                    myController.importProject(selectedFile);
                }
            }
        });

        fileMenu.add(importProjectsMenuItem);

        exportProjectsMenuItem.setText("Export Projects");
        exportProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Create a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();

                // Set the file chooser to accept only directories
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // Show the dialog and wait for the user to select a directory
                int result = fileChooser.showSaveDialog(Dashboard.this); // 'Dashboard.this' refers to the current instance of the Dashboard

                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected directory
                    File selectedDirectory = fileChooser.getSelectedFile();

                    // Get the names of the user's projects
                    Set<String> projectNames = myController.getProjectNames();

                    // Export each project to the selected directory
                    for (String projectName : projectNames) {
                        // Create a file for the project in the selected directory
                        File projectFile = new File(selectedDirectory, projectName + ".ser");
                        // Call the exportProject method in the PDC controller
                        myController.exportProject(projectName, projectFile);
                    }
                }
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
                importSettingsAction(evt);
            }
        });
        settingsMenu.add(importSettingsMenuItem);

        exportSettingsMenuItem.setText("Export Settings");
        exportSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //importSettingsAction(evt);
                exportSettingsAction(evt);
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
     * Edit settings behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void editSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSettingsAction
        settingsDialog.pack();
        settingsDialog.setLocationRelativeTo(null);
        usernameTextField.setText(myController.getCurrentUser().getMyUserFirstName());
        emailTextField.setText(myController.getCurrentUser().getUserEmail());
        settingsDialog.setVisible(true);
    }//GEN-LAST:event_editSettingsAction

    /**
     * Open file behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void importSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importSettingsAction
        int returnVal = jFileChooser1.showOpenDialog(Dashboard.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                myController.importSettings(jFileChooser1.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_importSettingsAction

    /**
     * Save settings behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void saveSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSettingsAction
        myController.setUserInfo(usernameTextField.getText(), emailTextField.getText());
        settingsDialog.dispose();
    }//GEN-LAST:event_saveSettingsAction

    /**
     * Cancel settings behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void cancelSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSettingsAction
        settingsDialog.dispose();
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
     * Cancel project behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void cancelProjectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelProjectAction
        createProjectDialog.dispose();
    }//GEN-LAST:event_cancelProjectAction

    /**
     * Open create project menu behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void createProjectMenuAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProjectMenuAction
        createProjectDialog.pack();
        createProjectDialog.setLocationRelativeTo(null);
        projectNameTextField.setText("");
        createProjectDialog.setVisible(true);
    }//GEN-LAST:event_createProjectMenuAction

    /**
     * Open file behavior
     * @param evt 
     * @author Jarvis Kampe
     */
    private void exportSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportSettingsAction
        int returnVal = jFileChooser1.showSaveDialog(Dashboard.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                myController.exportSettings(jFileChooser1.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportSettingsAction

    /**
     * Author: Hieu Nguyen
     * Event handler for the create project button action.
     * Displays input dialogs to get the project name and description from the user.
     * If a valid project name is provided, creates a new Project object and adds it to the current user's projects.
     * @param evt The action event triggered by the create project button
     */
    private void createProjectAction(java.awt.event.ActionEvent evt) {
        // Prompt the user to enter the project name
        String projectName = JOptionPane.showInputDialog(this, "Enter project name:");

        // Prompt the user to enter the project description
        String projectDescription = JOptionPane.showInputDialog(this, "Enter project description:");

        // Check if a valid project name is provided
        if (projectName != null && !projectName.isEmpty()) {
            // Get the current user from the PDC controller
            User user = myController.getCurrentUser();

            // Create a new Project object with the user and project name
            Project project = null;
            try {
                project = new Project(user, projectName);
            } catch (IOException e) {
                // If there is an error creating the project, throw a runtime exception
                throw new RuntimeException(e);
            }

            // Set the project description
            project.setDescription(projectDescription);

            // Add the project to the current user's projects
            myController.addProjectToCurrentUser(project);
        }
    }



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
                PDC controller = new PDC();
                User user = new User("LastName", "FirstName", "Email", "123");
                controller.setCurrentUser(user);
                new Dashboard(controller).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton cancelProjectButton;
    private javax.swing.JButton cancelSettingsButton;
    private javax.swing.JButton createProjectButton;
    private javax.swing.JDialog createProjectDialog;
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
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JButton saveSettingsButton;
    private javax.swing.JDialog settingsDialog;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
