/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.text.NumberFormat;
import control.PDC;

import javax.swing.*;

/**
 * A Panel used to represent a purchase.
 * @author Derek J. Ruiz Garcia
 * @version 6/4/2023
 */
public class PurchasePanel extends javax.swing.JPanel {

    /** The name of the purchase represented by this panel */
    private final String myPurchaseName;

    /** The cot of the purchase represented by this panel */
    private final String myCost;

    /** The controller used to change and retrieve data. */
    private final PDC myController;

    /** Helps with firing events. */
    private final PropertyChangeSupport myPropertycs = new PropertyChangeSupport(this);

    /**
     * A constructor for the purchase panel.
     * @param thePurchaseName the name of the purchase this panel will represent as a string.
     * @param theCost the cost of the purchase as a string linked to this panel.
     * @param theStatus the completed status of the purchase as a boolean.
     * @param theController the PDC controller that this panel will use to update the data.
     * @author Derek J. Ruiz Garcia
     */
    public PurchasePanel(String thePurchaseName, String theCost, boolean theStatus, PDC theController) {
        myPurchaseName = thePurchaseName;
        myCost = theCost;
        myController = theController;
        initComponents();
        purchaseLabel.setText(thePurchaseName);
//        costLabel.setText(theCost);
        costLabel.setText(NumberFormat.getCurrencyInstance().format(new BigDecimal(theCost)));
        statusCheckBox.setSelected(theStatus);
    }

    /**
     * Adds a listener to this property change.
     * @param theListener the listener of the property changed.
     * @author Derek J. Ruiz Garcia
     */
    public void addPropertyChangeLister(PropertyChangeListener theListener){
        myPropertycs.addPropertyChangeListener(theListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        costLabel = new javax.swing.JLabel();
        statusCheckBox = new javax.swing.JCheckBox();
        purchaseLabel = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 250, 250));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(550, 37));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(550, 37));

        costLabel.setText("<Purchase Cost>");

        statusCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxStatusActionPerformed(evt);
            }
        });

        purchaseLabel.setText("<PurchaseName>");
        purchaseLabel.setMinimumSize(new java.awt.Dimension(125, 16));

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(purchaseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(statusCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(statusCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editButton)
                        .addComponent(deleteButton))
                    .addComponent(costLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purchaseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Action performed listener for the checkbox.
     * @param evt the event linked to the checkbox
     * @author Derek J. Ruiz Garcia
     */
    private void checkBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
        AbstractButton abs = (AbstractButton) evt.getSource();
        boolean oldTaskStatus = myController.getTaskStatus(myController.getCurrTaskName());
        myController.setPurchaseStatus(myPurchaseName, abs.getModel().isSelected());
        boolean newTaskStatus = myController.getTaskStatus(myController.getCurrTaskName());
        myPropertycs.firePropertyChange("The purchase checkBox was selected", oldTaskStatus, newTaskStatus);
        revalidate();
        repaint();
    }//GEN-LAST:event_statusActionPerformed

    /**
     * A listener for the edit button that allows the user to change the cost of a purchase
     * @param evt the event connected to the edit button.
     * @author Derek J. Ruiz Garcia
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        String userInput = JOptionPane.showInputDialog(this,
                "Please enter the new cost for " + myPurchaseName, "Edit purchase", JOptionPane.OK_CANCEL_OPTION);

        if(userInput != null){
            try{
                boolean confirm = false;
                int secondResponse = JOptionPane.showConfirmDialog(this,
                        "Do you want to change the cost of " + myPurchaseName + " to $" + userInput + "?",
                        "Confirm changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (secondResponse == 0) confirm = true;
                if (confirm){
                    boolean wasAdded = myController.setPurchaseCost(myPurchaseName, userInput);
                    if (wasAdded){
                        costLabel.setText(NumberFormat.getCurrencyInstance().format(new BigDecimal(userInput)));
                    } else {
                        JOptionPane.showMessageDialog(this, "The quantity entered was invalid", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NullPointerException e){
                System.out.println("Error!");
            }
        }
    }//GEN-LAST:event_editButtonActionPerformed

    /**
     * A listener for the delete button that allows the user to change the cost of a purchase.
     * @param evt the event linked to the delete button.
     * @author Derek J.Ruiz Garcia
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the purchase: " + myPurchaseName + "?",
                "Confirm changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == 0){
            myPropertycs.firePropertyChange("The purchase was deleted", myPurchaseName, "");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel costLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel purchaseLabel;
    private javax.swing.JCheckBox statusCheckBox;
    // End of variables declaration//GEN-END:variables
}
