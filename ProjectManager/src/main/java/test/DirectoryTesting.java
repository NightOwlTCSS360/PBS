package test;

import control.PDC;
import model.User;
import model.UserController;
import model.projectdata.Project;
import model.projectdata.Purchase;
import model.projectdata.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;


public class DirectoryTesting {
    public static void main(String[] args) {
        PDC controller = new PDC();
        try {
            User testUser1 = new User("Paul", "Schmidt", "email@email.com", "pw");
            DirectoryTesting.addTestProjects(testUser1, 2, 4); //Uncomment to generate some project files, comment out if these projects already generated
            System.out.println("Printing Projects in " + testUser1.getMyUserFirstName() + "'s Project array");
            UserController.addUser(testUser1.getMyUserFirstName(), testUser1.getMyUserLastName(),
                    testUser1.getUserEmail(), testUser1.getMyPassword());
            for (Project p : testUser1.getProjects().values()) {
                System.out.println(p);
            }
            exportImportTest(testUser1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            User testUser2 = new User("First", "Last", "user@email.com", "pw");
            DirectoryTesting.addTestProjects(testUser2, 4, 7); //Uncomment to generate some project files, comment out if these projects already generated
            System.out.println("Printing Projects in " + testUser2.getMyUserFirstName() + "'s Project array");
            UserController.addUser(testUser2.getMyUserFirstName(), testUser2.getMyUserLastName(),
                    testUser2.getUserEmail(), testUser2.getMyPassword());
            for (Project p : testUser2.getProjects().values()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addTestProjects(final User theUser, final int start, final int end) throws IOException {
        for (int i = start; i < end; i++) {
            Project p = new Project(theUser, "TestProject" + i);
            Task t = new Task("TestTask" + i);
            Purchase pu = new Purchase("TestPurchase" + i, new BigDecimal("1.00"));
            t.addPurchase(pu);
            p.addTask(t);
            theUser.addProject(p);
            try {
                p.serialize(PDC.myDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static void exportImportTest(final User theUser) {
        PDC control = new PDC();
        control.setCurrentUser(theUser);
        JFrame main = new JFrame();
        main.setLocationRelativeTo(null);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setPreferredSize(new java.awt.Dimension(400, 500));
        main.setSize(new java.awt.Dimension(400, 500));
        main.setLayout(new GridLayout(2,1));
        JButton export = new JButton("Export");
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int result = ch.showSaveDialog(main);
                if (result == JFileChooser.APPROVE_OPTION) {
                    control.exportProject("TestProject2", ch.getSelectedFile());
                }
            }
        });
        JButton importB = new JButton("Import");
        importB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int result = ch.showOpenDialog(main);
                if (result == JFileChooser.APPROVE_OPTION) {
                    control.importProject(ch.getSelectedFile());
                    System.out.println("File imported Successfully");
                }
            }
        });
        main.add(export);
        main.add(importB);
        main.setVisible(true);
    }
}
