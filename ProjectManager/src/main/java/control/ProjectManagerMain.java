package control;

import com.formdev.flatlaf.FlatLightLaf;
import view.View;

public class ProjectManagerMain {
    public static void main(String args[]) {
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
