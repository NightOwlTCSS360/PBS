package test;

import control.PDC;

import javax.swing.*;
import java.io.IOException;

public class ImportExportSettingsTester {

    public static void main(String[] args) throws IOException {
        JFrame myFrame = new JFrame();
        PDC myPDC = new PDC();

        // Comment/uncomment this to test for the import.
//        myPDC.importSettings(myFrame);
//        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Comment/uncomment this to test for the export
        myPDC.exportSettings(myFrame);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
