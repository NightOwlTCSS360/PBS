package test;

import control.PDC;
import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Import/Export/Edit Settings functionality in the PDC class.
 * @author Paul Schmidt
 */
class PDCSettingsTest {

    PDC control = new PDC();
    User testUser;

    /**
     * Make sure there is some test settings to work with in the testing Directory.
     * @author Paul Schmidt
     */
    @BeforeAll
    static void validateSettingsFiles() {
        try {
            Path testing = Paths.get(System.getProperty("user.home") + "\\ProjectBudgetingSystem\\testing\\");
            if(!Files.exists(testing)) {
                Files.createDirectory(testing);
            }
            Path importSettings = Paths.get(testing + "\\ImportedSettings.txt");
            if(!Files.exists(importSettings)) {
                FileWriter out = new FileWriter(importSettings.toFile());
                out.write("NewUserName\nNewUserEmail@junit.org");
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a fresh instance of the user and set them to the currentUser in the PDC class.
     * @author Paul Schmidt
     */
    @BeforeEach
    void setUp() {
         testUser = new User("Test", "User", "test_user@junit.org","123");
         control.setCurrentUser(testUser);
    }

    /**
     * Creates a settings file with the current user's information and validates that the file was created with the
     * correct information.
     * @author Paul Schmidt
     */
    @Test
    void createSettingsFile() {
        control.createSettingsFile();
        try {
            Scanner input = new Scanner(new File(PDC.myDir + "Settings.txt"));
            assertEquals(control.getCurrentUser().getMyUserFirstName(), input.nextLine());
            assertEquals(control.getCurrentUser().getUserEmail(), input.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Imports settings from a predefined Settings File and validates that the information was created correctly.
     * @author Paul Schmidt
     */
    @Test
    void importSettings() {
        try {
            control.importSettings(new File(PDC.myDir + "testing\\ImportedSettings.txt"));
            try {
                Scanner input = new Scanner(new File(PDC.myDir + "testing\\ImportedSettings.txt"));
                assertEquals("NewUserName", input.nextLine());
                assertEquals("NewUserEmail@junit.org", input.nextLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates and exports a settings file then reads the file to make sure that the file was created with the
     * correct information.
     * @author Paul Schmidt
     */
    @Test
    void exportSettings() {
        try {
            control.createSettingsFile();
            control.exportSettings(new File(PDC.myDir + "testing\\ExportedSettings.txt"));
            Scanner export = new Scanner(new File(PDC.myDir + "testing\\ExportedSettings.txt"));

            assertEquals(control.getCurrentUser().getMyUserFirstName(), export.nextLine());
            assertEquals(control.getCurrentUser().getUserEmail(), export.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}