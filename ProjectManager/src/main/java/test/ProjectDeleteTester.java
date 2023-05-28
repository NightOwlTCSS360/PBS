package test;

import control.PDC;
import model.User;

import javax.swing.*;
import java.io.IOException;

public class ProjectDeleteTester {

    public static void main(String[] args) throws IOException {
        JFrame myFrame = new JFrame();
        PDC myPDC = new PDC();
        User testerUser = new User("Ruiz", "Derek", "derek@gmail", "123456");

        JFileChooser fc = new JFileChooser();

        //Comment/uncomment this to test the settings file creator.
        myPDC.setCurrentUser(testerUser);
        myPDC.createSettingsFile();
        myPDC.addNewProject("Tester Project");
        myPDC.setCurrentProject("Tester Project");
        System.out.println(System.getProperty("user.dir"));
        boolean wasAdded = myPDC.addNewTask("The tester task");
        System.out.println("The tasks was added to the project? " + wasAdded);
        //what if we add it again?
        wasAdded = myPDC.addNewTask("The tester task");
        System.out.println("The tasks was added to the project the second time? " + wasAdded);

        myPDC.setCurrentTask("The tester task");
        System.out.println(myPDC.getTasks());

        myPDC.deleteCurrentTask();
        System.out.println(myPDC.getTasks());
//        boolean wasDeleted = myPDC.deletecurrentProject("Tester Project");
//        System.out.println(wasDeleted);
    }
}
