package test;

import control.PDC;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PDCSettingsTest {

    PDC control = new PDC();
    User testUser;
    @BeforeEach
    void setUp() {
         testUser = new User("Test", "User", "test_user@junit.org","123");
         control.setCurrentUser(testUser);
    }

    @Test
    void createSettingsFile() {
        control.createSettingsFile();
        try {
            Scanner input = new Scanner(new File(PDC.myDir + "src\\main\\resources\\Settings\\Settings.txt"));
            assertEquals(control.getCurrentUser().getMyUserFirstName(), input.nextLine());
            assertEquals(control.getCurrentUser().getUserEmail(), input.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void importSettings() {
        try {
            control.importSettings(new File(PDC.myDir + "src\\test\\java\\test\\testdata\\Settings.txt"));
            try {
                Scanner input = new Scanner(new File(PDC.myDir + "src\\main\\resources\\Settings\\Settings.txt"));
                assertEquals("NewUserName", input.nextLine());
                assertEquals("NewUserEmail@junit.org", input.nextLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void exportSettings() {
        try {
            control.createSettingsFile();
            control.exportSettings(new File(PDC.myDir + "src\\test\\java\\test\\testdata\\ExportedSettings.txt"));
            Scanner export = new Scanner(new File(PDC.myDir + "src\\test\\java\\test\\testdata\\ExportedSettings.txt"));

            assertEquals(control.getCurrentUser().getMyUserFirstName(), export.nextLine());
            assertEquals(control.getCurrentUser().getUserEmail(), export.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}