/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import control.PDC;
import model.CSVHandler;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author David
 */
public class LoginPanel extends javax.swing.JPanel {

    private CSVHandler csvHandler;

    /**
     * Creates new form LoginPanel
     */
    public LoginPanel() {
        initComponents();
        csvHandler = new CSVHandler("users.csv");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(250, 250, 250));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Welcome");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jButton1.setText("Sign Up");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sign In");
        CSVHandler csvHandler = new CSVHandler("users.csv");

        jButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                String email = jTextField1.getText();
                String password = new String(jPasswordField1.getPassword());
                Stack<Character> emailFormat = new Stack<>();
                emailFormat.push('.');
                emailFormat.push('@');
                boolean input = false;
                for (int i = 0; i < email.length(); i++) {
                    if (email.charAt(i) == '.' || email.charAt(i) == '@') {
                        if (emailFormat.isEmpty() || email.charAt(i) != emailFormat.peek()) {
                            input = true;
                            break;
                        } else {
                            emailFormat.pop();
                        }
                    }
                }
                if (input || !emailFormat.isEmpty()) {
                    // Display error message
                    JOptionPane.showMessageDialog(LoginPanel.this,
                            "Invalid Email Format, Example: User@email.com",
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                    return; //end function
                }
                try {
                    User user = csvHandler.loadUser(email);
                    if (user != null && user.getMyPassword().equals(password)) {
                        // Open the DashboardPanel
                        JOptionPane.showMessageDialog(LoginPanel.this, "Succeed", "Succeed", JOptionPane.ERROR_MESSAGE);
                        View v = (View) getParent().getParent().getParent().getParent().getParent();
                        // System.out.println("the view class: " + v.getClass().toString());
                        JPanel MainFrame = (JPanel) getParent();
                        // System.out.println("the mainframe class: " +MainFrame.getClass().toString());
                        PDC controller = v.getController();
                        user.loadInUserProjects();
                        controller.setCurrentUser(user);
                        // System.out.println("Current user set successfully!");
                        // Dashboard dash = new Dashboard(controller);
                        // dash.setLocationRelativeTo(null);
                        // dash.setVisible(true);
                        // v.dispose();
                        // return;

                        // ProjectPanel customProjectPanel = new ProjectPanel(controller);
                        DashboardPanel customDashPanel = new DashboardPanel(controller);
                        // customDashPanel.add(customProjectPanel, 0);
                        v.setDashBoardPanel(customDashPanel);
                        customDashPanel.setVisible(true);
                        v.setMenuBarVisibility(true);

                        // Remove twice
                        MainFrame.remove(0);
                        MainFrame.remove(0);

                        MainFrame.add(customDashPanel);
                        MainFrame.revalidate();
                        MainFrame.repaint();

                    } else {
                        // Display error message
                        JOptionPane.showMessageDialog(LoginPanel.this,
                                "Incorrect username or password",
                                "Login Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jLabel2.setText("Email");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Password");

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(10, 10, 10)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPasswordField1))
                                .addGap(62, 62, 62))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(jLabel1)
                                .addGap(122, 122, 122)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jLabel1)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(6, 6, 6)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(126, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        SignUpPanel signupPanel = new SignUpPanel();
        javax.swing.JPanel MainFrame = (javax.swing.JPanel) this.getParent();
        MainFrame.remove(MainFrame.getComponent(0));
        MainFrame.add(signupPanel, 0);
        MainFrame.revalidate();
        MainFrame.repaint();

    }// GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jTextField1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jPasswordField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
