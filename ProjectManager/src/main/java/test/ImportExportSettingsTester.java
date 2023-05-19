package test;

import control.PDC;
import model.User;

import javax.swing.*;
import java.io.IOException;

public class ImportExportSettingsTester {

    public static void main(String[] args) throws IOException {
        JFrame myFrame = new JFrame();
        PDC myPDC = new PDC();
        User testerUser = new User("Ruiz", "Derek", "derek@gmail", "123456");

        JFileChooser fc = new JFileChooser();

        //Comment/uncomment this to test the settings file creator.
        myPDC.setCurrentUser(testerUser);
        myPDC.createSettingsFile();
        System.out.println(System.getProperty("user.dir"));
 //         Comment/uncomment this to test for the import.
//        int result = fc.showDialog(myFrame, "choose a file to import");
//        myPDC.importSettings(fc.getSelectedFile());
//        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Comment/uncomment this to test for the export
//        int result = fc.showDialog(myFrame, "Choose a location to export settings");
//        myPDC.exportSettings(fc.getSelectedFile());
//        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
