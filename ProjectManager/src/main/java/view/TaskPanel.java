/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import control.PDC;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A panel used to represent a single Task.
 * @author David
 */
public class TaskPanel extends javax.swing.JPanel implements PropertyChangeListener{

    /** The name of the task this panel represents. */
    private final String myTask;
    
    /** The data controller. */
    private final PDC myController;
    
    /**
     * A constructor for the TaskPanel
     * @param theTask the name of the task this panel will represent as a string.
     * @param theController the data controller used by this panel.
     * @param theStatus the state of the checkBox this task panel will have.
     */
    public TaskPanel(final String theTask, boolean theStatus, final PDC theController) {
        myTask = theTask;
        myController = theController;
        initComponents();
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        TaskName.setText(myTask);
        status.setSelected(theStatus);
        status.setEnabled(false);
    }

    /** A property listener that catches a signal indicating that a property has changed.
     *  @param theEvenOfThePropertyChanged A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent theEvenOfThePropertyChanged) {
        if (theEvenOfThePropertyChanged.getPropertyName().equals("the task " + myTask + " status has changed")){        // if the one that send the message gave the signal this name,
            status.setSelected(myController.getTaskStatus(myTask));                                                     // we update the status of the checkBox.
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

        status = new javax.swing.JCheckBox();
        TaskName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 250, 250));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(200, 30));

        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        TaskName.setText("<Task>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(status)
                .addGap(35, 35, 35)
                .addComponent(TaskName)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TaskName)
                    .addComponent(status))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * A listener for the clicks on the taskPanel
     * @param evt the event connected to the listener.
     */
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        myController.setCurrentTask(this.myTask);
        ProjectPanel pp = (ProjectPanel)getParent().getParent();
        myController.setCurrentTask(myTask);
        pp.repopulatePurchasesList();
        revalidate();
        repaint();
    }//GEN-LAST:event_formMouseClicked

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TaskName;
    private javax.swing.JCheckBox status;
    // End of variables declaration//GEN-END:variables
}
