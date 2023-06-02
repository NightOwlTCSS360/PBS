package control;

import com.formdev.flatlaf.FlatLightLaf;
import view.View;

/**
 * This class acts as the starting point for the program.
 * @author
 */
public class ProjectManagerMain {
    public static void main(String args[]) {
        //TODO Figure out why we get an error when try to make a JAR with the Flyleaf
        //IF YOU WANT TO CREATE A JAR YOU MUST COMMENT OUT THIS LINE
        FlatLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PDC controller = new PDC();
                View welcome = new View(controller);
                welcome.pack();
                welcome.setVisible(true);
                welcome.setLocationRelativeTo(null);
            }
        });
    }
}
