package test;

import control.PDC;
import model.User;
import model.projectdata.Project;
import model.projectdata.Purchase;
import model.projectdata.Task;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NoSuchObjectException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the Importing and Exporting Features for Projects Implemented in the PDC class
 * @author Paul Schmidt
 */
class PDCImportExportProjectsTest {
    PDC control = new PDC();
    User testUser;

    static Path testDir;

    /**
     * Set up the Test User's local files so that we can run the automated testing.
     * @author Paul Schmidt
     */
    @BeforeAll
    static void validateTestDirectory() {
        try {
            testDir = Paths.get(System.getProperty("user.home") + "\\ProjectBudgetingSystem\\testing\\");
            if(!Files.exists(testDir)) {
                Files.createDirectory(testDir);
                Files.createDirectory(new File(testDir + "\\exportdata\\").toPath());
                Files.createDirectory(new File(testDir + "\\importdata\\").toPath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Get a fresh instance of the User and set it to currentUser.
     * @author Paul Schmidt
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testUser = new User("Test", "User",
                "test_user@junit.org","123");
        control.setCurrentUser(testUser);
    }

    /**
     * Creates a new Project with Tasks and Purchases and Exports it to a file in the Testing Directory.
     * @author Paul Schmidt
     * @throws IOException If something goes wrong.
     */
    @org.junit.jupiter.api.Test
    void exportProject() throws IOException {
        Project testProject = new Project(testUser, "TestProject");
        Task testTask = new Task("TestTask");
        Purchase testPurchase1 = new Purchase("TestPurchase1", new BigDecimal("1.00"));
        Purchase testPurchase2 = new Purchase("TestPurchase2", new BigDecimal("0.50"));
        testTask.addPurchase(testPurchase1);
        testTask.addPurchase(testPurchase2);
        testUser.addProject(testProject);
        control.setCurrentProject("TestProject");
        File testdata = new File(PDC.myDir + "testing/exportdata");
        for (File file : Objects.requireNonNull(testdata.listFiles())) {
            if(file.delete()) {
                System.out.println("Deleted: " + file);
            }
        }
        File exportedProject = new File(PDC.myDir + "testing/exportdata/TestProject.ser");
        control.exportProject("TestProject", exportedProject);

        assertTrue(exportedProject.exists());
        try {
            control.setCurrentProject("TestProject");
            control.deleteCurrentProject();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imports an already existing serialized Project from the Testing Directory
     * @author Paul Schmidt
     */
    @org.junit.jupiter.api.Test
    void importProject() {
        boolean success = false;
        control.importProject(new File(PDC.myDir + "testing/importdata/MyNewProject.ser"));
        if (testUser.getProjects().containsKey("MyNewProject")) {
            try {
                Project imported = testUser.getProject("MyNewProject");
                success = new File(imported.getMyFilePath().toString()).exists();
            } catch (NoSuchObjectException e) {
                throw new RuntimeException(e);
            }
        }
        assertTrue(success);
        try {
            control.setCurrentProject("MyNewProject");
            control.deleteCurrentProject();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}