/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import control.PDC;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


/**
 *
 * @author pihol
 */
public class View extends javax.swing.JFrame {

    private PDC controller;

    private DashboardPanel dbp;
    /**
     * Creates new form StartGUI
     */
    public View(final PDC theController) {
        controller = theController;
        initComponents();
        LoginPanel login = new LoginPanel();
        LogoPanel logo = new LogoPanel();
        MainFrame.remove(jPanel2);
        MainFrame.remove(jPanel3);
        MainFrame.add(login, 0);
        MainFrame.add(logo, 1);
        MainFrame.revalidate();
        MainFrame.repaint();
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
        MainFrame = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        createProjectMenuItem = new javax.swing.JMenuItem();
        importProjectsMenuItem = new javax.swing.JMenuItem();
        exportProjectsMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        editSettingsMenuItem = new javax.swing.JMenuItem();
        importSettingsMenuItem = new javax.swing.JMenuItem();
        exportSettingsMenuItem = new javax.swing.JMenuItem();
        signOutMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        usernameLabel.setText("First Name:");

        emailLabel.setText("Email:");

        saveSettingsButton.setText("Apply");
        saveSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSettingsButtonsaveSettingsAction(evt);
            }
        });

        cancelSettingsButton.setText("Cancel");
        cancelSettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSettingsButtoncancelSettingsAction(evt);
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
                createProjectButtoncreateProjectAction(evt);
            }
        });

        cancelProjectButton.setText("Cancel");
        cancelProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelProjectButtoncancelProjectAction(evt);
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

        MainFrame.setBackground(new java.awt.Color(255, 255, 255));
        MainFrame.setToolTipText("");
        MainFrame.setLayout(new javax.swing.BoxLayout(MainFrame, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setMaximumSize(new java.awt.Dimension(400, 32767));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 500));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        MainFrame.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 500));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        MainFrame.add(jPanel3);

        fileMenu.setText("File");

        createProjectMenuItem.setText("Create Project");
        createProjectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createProjectMenuItemcreateProjectAction(evt);
                createProjectMenuItemcreateProjectMenuAction(evt);
            }
        });
        fileMenu.add(createProjectMenuItem);

        importProjectsMenuItem.setText("Import Projects");
        importProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importProjectAction(evt);
            }
        });
        fileMenu.add(importProjectsMenuItem);

        exportProjectsMenuItem.setText("Export Projects");
        exportProjectsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportProjectAction(evt);
            }
        });
        fileMenu.add(exportProjectsMenuItem);

        jMenuBar1.add(fileMenu);

        settingsMenu.setText("Settings");

        editSettingsMenuItem.setText("Edit Settings");
        editSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSettingsMenuItemeditSettingsAction(evt);
            }
        });
        settingsMenu.add(editSettingsMenuItem);

        importSettingsMenuItem.setText("Import Settings");
        importSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importSettingsMenuItemimportSettingsAction(evt);
            }
        });
        settingsMenu.add(importSettingsMenuItem);

        exportSettingsMenuItem.setText("Export Settings");
        exportSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportSettingsMenuItemimportSettingsAction(evt);
                exportSettingsMenuItemexportSettingsAction(evt);
            }
        });
        settingsMenu.add(exportSettingsMenuItem);

        signOutMenuItem.setText("Sign Out");
        signOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutAction(evt);
            }
        });
        settingsMenu.add(signOutMenuItem);

        jMenuBar1.add(settingsMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemaboutAction(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createProjectMenuItemcreateProjectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProjectMenuItemcreateProjectAction
        // TODO add your handling code here:
    }//GEN-LAST:event_createProjectMenuItemcreateProjectAction

    private void createProjectMenuItemcreateProjectMenuAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProjectMenuItemcreateProjectMenuAction
        createProjectDialog.pack();
        createProjectDialog.setLocationRelativeTo(null);
        projectNameTextField.setText("");
        createProjectDialog.setVisible(true);
    }//GEN-LAST:event_createProjectMenuItemcreateProjectMenuAction

    private void importProjectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importProjectsMenuItemimportSettingsAction
        int returnVal = jFileChooser1.showOpenDialog(View.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            controller.importProject(jFileChooser1.getSelectedFile());
            dbp.repopulateProjectList();
        }
    }//GEN-LAST:event_importProjectsMenuItemimportSettingsAction

    private void exportProjectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportProjectsMenuItemimportSettingsAction
        int returnVal = jFileChooser1.showOpenDialog(View.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            if (controller.getCurrProjectName() != null) {
                controller.exportProject(controller.getCurrProjectName(),jFileChooser1.getSelectedFile());
            }
        }
    }//GEN-LAST:event_exportProjectsMenuItemimportSettingsAction

    private void editSettingsMenuItemeditSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSettingsMenuItemeditSettingsAction
        settingsDialog.pack();
        settingsDialog.setLocationRelativeTo(null);
        usernameTextField.setText(controller.getCurrentUser().getMyUserFirstName());
        emailTextField.setText(controller.getCurrentUser().getUserEmail());
        settingsDialog.setVisible(true);
    }//GEN-LAST:event_editSettingsMenuItemeditSettingsAction

    private void importSettingsMenuItemimportSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importSettingsMenuItemimportSettingsAction
        int returnVal = jFileChooser1.showOpenDialog(View.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                controller.importSettings(jFileChooser1.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_importSettingsMenuItemimportSettingsAction

    private void exportSettingsMenuItemimportSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportSettingsMenuItemimportSettingsAction
        int returnVal = jFileChooser1.showOpenDialog(View.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                controller.importSettings(jFileChooser1.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportSettingsMenuItemimportSettingsAction

    private void exportSettingsMenuItemexportSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportSettingsMenuItemexportSettingsAction
        int returnVal = jFileChooser1.showSaveDialog(View.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                controller.exportSettings(jFileChooser1.getSelectedFile());
            } catch (IOException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_exportSettingsMenuItemexportSettingsAction

    private void aboutMenuItemaboutAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemaboutAction
        javax.swing.JPanel MainFrame = (javax.swing.JPanel)this.MainFrame;
        AboutPanel about = new AboutPanel((javax.swing.JPanel)this.MainFrame.getComponent(0));
        MainFrame.remove(0);
        MainFrame.add(about);
        MainFrame.revalidate();
        MainFrame.repaint();
    }//GEN-LAST:event_aboutMenuItemaboutAction

    private void saveSettingsButtonsaveSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSettingsButtonsaveSettingsAction
        controller.setUserInfo(usernameTextField.getText(), emailTextField.getText());
        settingsDialog.dispose();
    }//GEN-LAST:event_saveSettingsButtonsaveSettingsAction

    private void cancelSettingsButtoncancelSettingsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSettingsButtoncancelSettingsAction
        settingsDialog.dispose();
    }//GEN-LAST:event_cancelSettingsButtoncancelSettingsAction

    private void createProjectButtoncreateProjectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createProjectButtoncreateProjectAction
        try {
            controller.addNewProject(projectNameTextField.getText());
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        createProjectDialog.dispose();
    }//GEN-LAST:event_createProjectButtoncreateProjectAction

    private void cancelProjectButtoncancelProjectAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelProjectButtoncancelProjectAction
        createProjectDialog.dispose();
    }//GEN-LAST:event_cancelProjectButtoncancelProjectAction

    private void signOutAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutAction
        if (controller.getCurrentUser() != null) {
            javax.swing.JPanel MainFrame = (javax.swing.JPanel)this.MainFrame;
            LoginPanel login = new LoginPanel();
            LogoPanel logo = new LogoPanel();
            MainFrame.remove(0);
            MainFrame.add(login, 0);
            MainFrame.add(logo, 1);
            MainFrame.revalidate();
            MainFrame.repaint();
            controller.logoutUser();
        }
    }//GEN-LAST:event_signOutAction

    public PDC getController() {
        return controller;
    }
    private void disableWelcome() {
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainFrame;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JTextField projectNameTextField;
    private javax.swing.JButton saveSettingsButton;
    private javax.swing.JDialog settingsDialog;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JMenuItem signOutMenuItem;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;

    public void setDashBoardPanel(DashboardPanel customDashPanel) {
        dbp = customDashPanel;
    }
    // End of variables declaration//GEN-END:variables
}
