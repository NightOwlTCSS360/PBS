/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import control.PDC;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


/**
 * A project Panel that holds all the tasks, purchases and budget of the project.
 * @author David
 */
public class ProjectPanel extends javax.swing.JPanel implements PropertyChangeListener {

    /** The data controller */
    private final PDC myController;
    private DashboardPanel dbp;

    /** Helps with firing events. */
    private final PropertyChangeSupport myPropertycs = new PropertyChangeSupport(this);


    public void setBudgetInfo(JPanel budgetInfo) {
        BudgetInfo = budgetInfo;
    }

    public void repopulateBudgetInfo(){
        BudgetInfo.removeAll();
        // Add BudgetPanel to BudgetInfo
        BudgetInfo.setLayout(new BorderLayout()); // Set layout for BudgetInfo container
        BudgetInfo.add(new BudgetPanel(myController), BorderLayout.CENTER); // Add BudgetPanel to BudgetInfo
        revalidate();
        repaint();
    }

    /**
     * Creates new form ProjectPanel
     * @param theController The data controller.
     */
    public ProjectPanel(final PDC theController) {
        myController = theController;
        initComponents();
        try{
            currentTaskNameField.setText(myController.getCurrTaskName());
        } catch (Exception e){
            currentTaskNameField.setText("");
        }
        repopulateTasksList();
    }

    public void repopulatePurchasesList() {
        PurchasesList.removeAll();
        for (String purchaseName : myController.getPurchases()) {
            PurchasePanel pPanel = new PurchasePanel(purchaseName, myController.getPurchaseCost(purchaseName).toString(),
                    myController.getPurchaseStatus(purchaseName), myController);
            pPanel.addPropertyChangeLister(this);
            PurchasesList.add(pPanel);
        }
        currentTaskNameField.setText(myController.getCurrTaskName());
        revalidate();
        repaint();
    }

    public void repopulateTasksList(){
        List<String> arr = new ArrayList<>();
        TaskList.removeAll();
        if(myController.getCurrProjectName() != null) arr = myController.getTasks();

        for(String taskName: arr){
            TaskPanel t = new TaskPanel(taskName, myController.getTaskStatus(taskName), myController);
            addPropertyChangeLister(t);
            TaskList.add(t);
        }
        revalidate();
        repaint();
    }

    /**
     * A property change listener that will catch a signal send when a specific property has changed.
     * @param thePropertyEvent A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent thePropertyEvent) {
        if (thePropertyEvent.getPropertyName().equals("The purchase was deleted")){

            boolean oldTaskStatus = myController.getTaskStatus(myController.getCurrTaskName());
            myController.deletePurchase(thePropertyEvent.getOldValue().toString());
            boolean newTaskStatus = myController.getTaskStatus(myController.getCurrTaskName());
            myPropertycs.firePropertyChange("the task " + myController.getCurrTaskName() + " status has changed", oldTaskStatus, newTaskStatus);
            repopulatePurchasesList();

        } else if (thePropertyEvent.getPropertyName().equals("The purchase checkBox was selected")){
            myPropertycs.firePropertyChange("the task " + myController.getCurrTaskName() + " status has changed", thePropertyEvent.getOldValue(), thePropertyEvent.getNewValue());
        }
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

        ProjectTitle = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ProjectTrashButton = new javax.swing.JButton();
        TaskTitle = new javax.swing.JPanel();
        TrashButton = new javax.swing.JButton();
        currentTaskNameField = new javax.swing.JLabel();
        PurchasesTitle = new javax.swing.JPanel();
        PurchaseAdd = new javax.swing.JButton();
        PurchasesLabel = new javax.swing.JLabel();
        Description = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        nameLabel = new javax.swing.JLabel();
        CostLabel = new javax.swing.JLabel();
        StatusLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        BudgetInfo = new javax.swing.JPanel();
        BudgetLabel = new javax.swing.JLabel();
        TaskListTitle = new javax.swing.JPanel();
        TaskAdd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        TaskList = new javax.swing.JPanel();
        PurchasesList = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 250, 250));
        setPreferredSize(new java.awt.Dimension(750, 500));

        ProjectTitle.setBackground(new java.awt.Color(250, 250, 250));
        ProjectTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ProjectTitle.setPreferredSize(new java.awt.Dimension(400, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel7.setText(myController.getCurrProjectName());

        ProjectTrashButton.setBackground(new java.awt.Color(250, 250, 250));
        ProjectTrashButton.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        ProjectTrashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trash-bin.png"))); // NOI18N
        ProjectTrashButton.setAlignmentY(0.0F);
        ProjectTrashButton.setBorder(null);
        ProjectTrashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProjectTrashButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProjectTitleLayout = new javax.swing.GroupLayout(ProjectTitle);
        ProjectTitle.setLayout(ProjectTitleLayout);
        ProjectTitleLayout.setHorizontalGroup(
            ProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProjectTitleLayout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(ProjectTrashButton)
                .addContainerGap())
        );
        ProjectTitleLayout.setVerticalGroup(
            ProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProjectTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProjectTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ProjectTrashButton)
                    .addComponent(jLabel7))
                .addGap(12, 12, 12))
        );

        TaskTitle.setBackground(new java.awt.Color(250, 250, 250));
        TaskTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TaskTitle.setPreferredSize(new java.awt.Dimension(400, 40));

        TrashButton.setBackground(new java.awt.Color(250, 250, 250));
        TrashButton.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        TrashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trash-bin.png"))); // NOI18N
        TrashButton.setAlignmentY(0.0F);
        TrashButton.setBorder(null);
        TrashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrashButtonActionPerformed(evt);
            }
        });

        currentTaskNameField.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        currentTaskNameField.setText(myController.getCurrTaskName());

        javax.swing.GroupLayout TaskTitleLayout = new javax.swing.GroupLayout(TaskTitle);
        TaskTitle.setLayout(TaskTitleLayout);
        TaskTitleLayout.setHorizontalGroup(
            TaskTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskTitleLayout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(currentTaskNameField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(TrashButton)
                .addGap(7, 7, 7))
        );
        TaskTitleLayout.setVerticalGroup(
            TaskTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskTitleLayout.createSequentialGroup()
                .addGroup(TaskTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TaskTitleLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(currentTaskNameField))
                    .addGroup(TaskTitleLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(TrashButton)))
                .addGap(48, 48, 48))
        );

        PurchasesTitle.setBackground(new java.awt.Color(250, 250, 250));
        PurchasesTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PurchasesTitle.setPreferredSize(new java.awt.Dimension(400, 40));

        PurchaseAdd.setBackground(new java.awt.Color(250, 250, 250));
        PurchaseAdd.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        PurchaseAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add-button.png"))); // NOI18N
        PurchaseAdd.setAlignmentY(0.0F);
        PurchaseAdd.setBorder(null);
        PurchaseAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseAddActionPerformed(evt);
            }
        });

        PurchasesLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        PurchasesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PurchasesLabel.setText("Purchases");
        PurchasesLabel.setAlignmentY(0.0F);
        PurchasesLabel.setPreferredSize(new java.awt.Dimension(67, 16));

        javax.swing.GroupLayout PurchasesTitleLayout = new javax.swing.GroupLayout(PurchasesTitle);
        PurchasesTitle.setLayout(PurchasesTitleLayout);
        PurchasesTitleLayout.setHorizontalGroup(
            PurchasesTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PurchasesTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PurchasesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PurchaseAdd)
                .addGap(10, 10, 10))
        );
        PurchasesTitleLayout.setVerticalGroup(
            PurchasesTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PurchasesTitleLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(PurchasesTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PurchaseAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PurchasesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        Description.setBackground(new java.awt.Color(250, 250, 250));
        Description.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Description.setPreferredSize(new java.awt.Dimension(400, 50));

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        nameLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setText("Name");

        CostLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        CostLabel.setText("Cost");

        StatusLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        StatusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StatusLabel.setText("Status");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Actions");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout DescriptionLayout = new javax.swing.GroupLayout(Description);
        Description.setLayout(DescriptionLayout);
        DescriptionLayout.setHorizontalGroup(
            DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DescriptionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CostLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(StatusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(55, 55, 55))
        );
        DescriptionLayout.setVerticalGroup(
            DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DescriptionLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator3)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DescriptionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel)
                    .addComponent(CostLabel))
                .addGap(14, 14, 14))
            .addGroup(DescriptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DescriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StatusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(DescriptionLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        BudgetInfo.setBackground(new java.awt.Color(250, 250, 250));
        BudgetInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        BudgetInfo.setPreferredSize(new java.awt.Dimension(200, 120));

        BudgetLabel.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        BudgetLabel.setText("Budget");

        javax.swing.GroupLayout BudgetInfoLayout = new javax.swing.GroupLayout(BudgetInfo);
        BudgetInfo.setLayout(BudgetInfoLayout);
        BudgetInfoLayout.setHorizontalGroup(
            BudgetInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BudgetInfoLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(BudgetLabel)
                .addGap(69, 69, 69))
        );
        BudgetInfoLayout.setVerticalGroup(
            BudgetInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BudgetInfoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(BudgetLabel)
                .addGap(162, 162, 162))
        );

        TaskListTitle.setBackground(new java.awt.Color(250, 250, 250));
        TaskListTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TaskListTitle.setPreferredSize(new java.awt.Dimension(200, 120));

        TaskAdd.setBackground(new java.awt.Color(250, 250, 250));
        TaskAdd.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        TaskAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add-button.png"))); // NOI18N
        TaskAdd.setAlignmentY(0.0F);
        TaskAdd.setBorder(null);
        TaskAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaskAddActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tasks List");

        javax.swing.GroupLayout TaskListTitleLayout = new javax.swing.GroupLayout(TaskListTitle);
        TaskListTitle.setLayout(TaskListTitleLayout);
        TaskListTitleLayout.setHorizontalGroup(
            TaskListTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskListTitleLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addGap(42, 42, 42)
                .addComponent(TaskAdd)
                .addGap(417, 417, 417))
        );
        TaskListTitleLayout.setVerticalGroup(
            TaskListTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskListTitleLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(TaskListTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(TaskAdd))
                .addGap(264, 264, 264))
        );

        TaskList.setBackground(new java.awt.Color(250, 250, 250));
        TaskList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        TaskList.setPreferredSize(new java.awt.Dimension(200, 120));
        TaskList.setLayout(new javax.swing.BoxLayout(TaskList, javax.swing.BoxLayout.Y_AXIS));

        PurchasesList.setBackground(new java.awt.Color(250, 250, 250));
        PurchasesList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        PurchasesList.setPreferredSize(new java.awt.Dimension(200, 120));
        PurchasesList.setLayout(new javax.swing.BoxLayout(PurchasesList, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BudgetInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ProjectTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(TaskTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(PurchasesTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(Description, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TaskListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TaskList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(PurchasesList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BudgetInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ProjectTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(TaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(PurchasesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TaskListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(TaskList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PurchasesList, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * A task action performed that is connected to the taskAdd Button.
     * @param evt the event connected to the taskAdd button.
     */
    private void TaskAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaskAddActionPerformed
        // TODO add your handling code here:
        if (myController.getCurrProjectName() != null){             // If we have selected a project
            final String taskName = JOptionPane.showInputDialog(null, "Enter the task name",
                    "Task name");
            try{
                if(taskName != null){
                    if (!myController.getTasks().contains(taskName)){
                        myController.addNewTask(taskName);
                        TaskPanel t = new TaskPanel(taskName, true,  myController);
                        addPropertyChangeLister(t);
                        TaskList.add(t);
                        this.repopulatePurchasesList();
                        revalidate();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "That task already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }// If the user has entered anything
                }
            } catch (NullPointerException e){
                System.out.println("You canceled");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a project before adding a task!", "No project selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_TaskAddActionPerformed

    /**
     * A purchase action performed that is connected to the purhaseAdd button.
     * @param evt the event generated by the purchaseAdd button.
     */
    private void PurchaseAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseAddActionPerformed
        // TODO add your handling code here:

        if (myController.getCurrTaskName() != null){                                            // If we have a task selected
            PurchasePopUpPanel myPopUpPurchase = new PurchasePopUpPanel();
            int confirm = JOptionPane.showOptionDialog(null, myPopUpPurchase, "Purchase",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            try{
                if(confirm == 0){
                    boolean purchaseWasAdded = myController.addNewPurchase(myPopUpPurchase.getPurchaseName(), myPopUpPurchase.getPurchaseCost());
                    if (purchaseWasAdded){
                        PurchasePanel pur = new PurchasePanel(myPopUpPurchase.getPurchaseName(),
                                new BigDecimal(myPopUpPurchase.getPurchaseCost()).toString(), false, myController);
                        pur.addPropertyChangeLister(this);                                                    // This project panel will be listening to the property changed of this purchasePanel
                        PurchasesList.add(pur);
                        myPropertycs.firePropertyChange("the task " + myController.getCurrTaskName() + " status has changed", true, false); // Since we added a new purchase, the task is no longer completed
                        revalidate();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid name or cost", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NullPointerException e){
                System.out.println("Error!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a project and a task first before adding a purchase!", "No project or task selected", JOptionPane.WARNING_MESSAGE);
        }
//        }
    }//GEN-LAST:event_PurchaseAddActionPerformed

    private void TrashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrashButtonActionPerformed
        // TODO add your handling code here:
        if (myController.getCurrTaskName() != null){
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the task: " + myController.getCurrTaskName() + "?",
                    "Confirm changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == 0){
                myController.deleteCurrentTask();
                repopulateTasksList();
                PurchasesList.removeAll();
                currentTaskNameField.setText("");
                revalidate();
                repaint();
            }
        }
    }//GEN-LAST:event_TrashButtonActionPerformed

    private void ProjectTrashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProjectTrashButtonActionPerformed
        if (myController.getCurrProjectName() != null){
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the Project: " + myController.getCurrProjectName() + "?",
                    "Delete Current Project", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == 0){
                dbp = (DashboardPanel)getParent();
                myController.deleteCurrentProject();
                dbp.repopulateProjectList();
                dbp.repopulateProjectPanel();
            }
        }
    }//GEN-LAST:event_ProjectTrashButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BudgetInfo;
    private javax.swing.JLabel BudgetLabel;
    private javax.swing.JLabel CostLabel;
    private javax.swing.JPanel Description;
    private javax.swing.JPanel ProjectTitle;
    private javax.swing.JButton ProjectTrashButton;
    private javax.swing.JButton PurchaseAdd;
    private javax.swing.JLabel PurchasesLabel;
    private javax.swing.JPanel PurchasesList;
    private javax.swing.JPanel PurchasesTitle;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JButton TaskAdd;
    private javax.swing.JPanel TaskList;
    private javax.swing.JPanel TaskListTitle;
    private javax.swing.JPanel TaskTitle;
    private javax.swing.JButton TrashButton;
    private javax.swing.JLabel currentTaskNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
