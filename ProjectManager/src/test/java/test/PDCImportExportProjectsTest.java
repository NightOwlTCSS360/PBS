package test;

import control.PDC;
import model.User;
import model.projectdata.Project;
import model.projectdata.Purchase;
import model.projectdata.Task;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.NoSuchObjectException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PDCImportExportProjectsTest {
    PDC control = new PDC();
    User testUser;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testUser = new User("Test", "User",
                "test_user@junit.org","123");
        control.setCurrentUser(testUser);
    }

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
        File testdata = new File(PDC.myDir + "src/test/java/test/testdata/exportdata");
        for (File file : Objects.requireNonNull(testdata.listFiles())) {
            if(file.delete()) {
                System.out.println("Deleted: " + file);
            }
        }
        File exportedProject = new File(PDC.myDir + "src/test/java/test/testdata/exportdata/TestProject.ser");
        control.exportProject("TestProject", exportedProject);

        assertTrue(exportedProject.exists());
        try {
            control.setCurrentProject("TestProject");
            control.deleteCurrentProject();
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
    @org.junit.jupiter.api.Test
    void importProject() {
        boolean success = false;
        control.importProject(new File(PDC.myDir + "src/test/java/test/testdata/importdata/MyNewProject.ser"));
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
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }

}