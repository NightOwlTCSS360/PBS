/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.components;

import control.PDC;
import java.io.File;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;



/**
 *
 * @author PaulS
 */
public class ExportDialog extends javax.swing.JDialog {

    private Set<String> projects;
    private PDC controller;
    private File directory;
    private String selectedProject;
    /**
     * Creates new form ExportDialog
     */
    public ExportDialog(java.awt.Frame parent, boolean modal, PDC controller) {
        super(parent, modal);
        initComponents();
        this.controller = controller;
        projects = controller.getProjectNames();
        for(String ProjectName : projects) {
            ProjectList.addItem(ProjectName);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DirectoryChooser = new javax.swing.JFileChooser();
        SelectLabel = new javax.swing.JLabel();
        DestinationLabel = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        ExportButton = new javax.swing.JButton();
        SelectedDestination = new javax.swing.JLabel();
        OpenExplorerButton = new javax.swing.JButton();
        ProjectList = new javax.swing.JComboBox<>();

        DirectoryChooser.setAcceptAllFileFilterUsed(false);
        DirectoryChooser.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        DirectoryChooser.setApproveButtonText("Select");
        DirectoryChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Export Project");
        setResizable(false);

        SelectLabel.setText("Select Project to Export");

        DestinationLabel.setText("Select Destination Folder");

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        ExportButton.setText("Export");
        ExportButton.setEnabled(false);
        ExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportButtonActionPerformed(evt);
            }
        });

        SelectedDestination.setText("<No Destination>");

        OpenExplorerButton.setText("...");
        OpenExplorerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectDirectoryAction(evt);
            }
        });

        ProjectList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProjectListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(ProjectList, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SelectLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DestinationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(SelectedDestination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OpenExplorerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DestinationLabel)
                    .addComponent(SelectLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SelectedDestination)
                        .addComponent(OpenExplorerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProjectList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExportButton)
                    .addComponent(CancelButton))
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void ExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportButtonActionPerformed
        if (directory != null && selectedProject != null) {
            try {
                File out = new File(directory.toString() + "\\" + selectedProject + ".ser");
                System.out.println(out.toString());
                controller.exportProject(selectedProject, out);
                JOptionPane successPane = new JOptionPane(selectedProject + " exported Successfully!", JOptionPane.INFORMATION_MESSAGE);
                JDialog successDialog = successPane.createDialog(this, selectedProject);
                successDialog.setAlwaysOnTop(true);
                successDialog.setVisible(true);
                
            } catch (Exception e) {
                JOptionPane error = new JOptionPane("Error exporting " + selectedProject, JOptionPane.ERROR_MESSAGE);
                JDialog errorDialog = error.createDialog(this, selectedProject);
                errorDialog.setAlwaysOnTop(true);
                errorDialog.setVisible(true);
                System.out.print(e);
            }
        }
    }//GEN-LAST:event_ExportButtonActionPerformed

    private void ProjectListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProjectListActionPerformed
        selectedProject = (String)ProjectList.getSelectedItem();
        if (directory != null && selectedProject != null) {
                ExportButton.setEnabled(true);
        }
    }//GEN-LAST:event_ProjectListActionPerformed

    private void SelectDirectoryAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectDirectoryAction
        int result = DirectoryChooser.showSaveDialog(this);
        if (result == 0) {
            directory = DirectoryChooser.getSelectedFile();
            String label = directory.toString();
            int index = Math.min(directory.toString().length(), 13);
            label = label.substring(label.length()-index);
            SelectedDestination.setText("..." + label);
            if (directory != null && selectedProject != null) {
                ExportButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_SelectDirectoryAction

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel DestinationLabel;
    private javax.swing.JFileChooser DirectoryChooser;
    private javax.swing.JButton ExportButton;
    private javax.swing.JButton OpenExplorerButton;
    private javax.swing.JComboBox<String> ProjectList;
    private javax.swing.JLabel SelectLabel;
    private javax.swing.JLabel SelectedDestination;
    // End of variables declaration//GEN-END:variables
}