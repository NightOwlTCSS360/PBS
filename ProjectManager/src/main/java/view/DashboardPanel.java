/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import control.PDC;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import model.AppInfo;

/**
 *
 * @author David
 */
public class DashboardPanel extends javax.swing.JPanel {
   
    /**
     * Generic object used by the About object
     */
    private javax.swing.JPanel myLast;
    private View parentJFrame;
    
    private PDC controller = new PDC();
    private ProjectList pl;
    
    /**
     * The AppInfo object used by the GUI
     */
    private static final AppInfo myAppInfo = new AppInfo();
    
    /**
     * Creates new form AboutPanel
     * @author Jarvis Kampe
     * @param controller
     */
    public DashboardPanel(PDC controller) {
        this.controller = controller;
        initComponents();
        ProjectPanel pp = new ProjectPanel(controller);
        pl = new ProjectList(controller, pp);
        this.add(pl, 0);
        this.add(pp, 1);
        this.revalidate();
        this.repaint();
    }

    /**
     * Repopulates the ProjectPanel based on the currently selected Project.
     * @author Paul Schmidt
     */
    public void repopulateProjectPanel() {
        this.remove(1);

        ProjectPanel projectPanel = new ProjectPanel(controller);

        this.add(projectPanel, 1);

        projectPanel.repopulateBudgetInfo(); // Call the method on the ProjectPanel object
        this.revalidate();
        if (parentJFrame == null) {
            parentJFrame = (View)getParent().getParent().getParent().getParent().getParent();
        }
        parentJFrame.pack();
        this.repaint();
    }

    /**
     * Repopulates the List of Projects based on the User's Projects.
     * @author Paul Schmidt
     */
    public void repopulateProjectList() {
        pl.repopulateProjectsList();
        revalidate();
        if (parentJFrame == null) {
            parentJFrame = (View)getParent().getParent().getParent().getParent().getParent();
        }        parentJFrame.pack();
        repaint();
    }
    
    /**
     * Creates new About with type obj
     * @param obj 
     */
    public DashboardPanel(javax.swing.JPanel obj) {
        myLast = obj;
        initComponents();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(250, 250, 250));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handle hyperlink events
     * @param evt 
     */
    private void addHyperlinkListener(javax.swing.event.HyperlinkEvent evt) {                                      
        if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(evt.getURL().toURI());
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(About.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }   
    

    /**
     * Generate developer information
     * @param id
     * @return developer info
     */
    private String genDevInfo(int id) {
        return "<a href=\"" + myAppInfo.getDevs().get(id).getGit() + "\">"
            + myAppInfo.getDevs().get(id).getName() + "</a>";
    }
    
    /**
     * Gets the object type if you need it for some reason lol
     * @return the object type what else would it return
     */
    public javax.swing.JPanel getObject() {
        return myLast;
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
