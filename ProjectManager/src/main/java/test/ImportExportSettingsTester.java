package test;

import control.PDC;

import javax.swing.*;
import java.io.IOException;

public class ImportExportSettingsTester {

    public static void main(String[] args) throws IOException {
        JFrame myFrame = new JFrame();
        PDC myPDC = new PDC();

        JFileChooser fc = new JFileChooser();
//        int result = fc.showDialog(myFrame, "choose a file to import");

//        //         Comment/uncomment this to test for the import.
//        myPDC.importSettings(fc.getSelectedFile());
//        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Comment/uncomment this to test for the export
        int result = fc.showDialog(myFrame, "Choose a location to export settings");
        myPDC.exportSettings(fc.getSelectedFile());
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
