package test;

import control.PDC;
import model.User;
import model.projectdata.Project;
import model.projectdata.Purchase;
import model.projectdata.Task;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests methods not related to settings or importing/exporting Projects in the PDC class.
 * @author Paul Schmidt
 */
class PDCMainTest {

    static PDC control = new PDC();
    User testUser = new User("Test", "User", "test_user@junit.org","123");

    /**
     * Set up some fresh Projects/Tasks/Purchases to test.
     * @author Paul Schmidt
     * @throws IOException if something goes wrong.
     */
    @BeforeEach
    void setUp() throws IOException {
        control.setCurrentUser(testUser);
        control.addNewProject("TestProject");
        control.setCurrentProject("TestProject");
        control.addNewTask("TestTask");
        control.setCurrentTask("TestTask");
        control.addNewPurchase("TestPurchase1", "1.00");
        control.addNewPurchase("TestPurchase2", "0.50");
    }

    /**
     * Cleans up the Test User's local Persistent Data from the automated testing.
     * @author Paul Schmidt
     * @throws NullPointerException if we somehow tried to delete project that doesn't exist.
     */
    @AfterAll
    static void cleanUp() throws NullPointerException {
        for(String project : control.getProjectNames()) {
            System.out.println(project);
            control.setCurrentProject(project);
            control.deleteCurrentProject();
        }
    }

    @Test
    void setCurrentUser() {
        control.setCurrentUser(testUser);
        assertEquals(control.getCurrentUser(), testUser);
    }

    @Test
    void getProjectNames() {
        assertTrue(control.getProjectNames().contains("TestProject"));
    }

    @Test
    void setCurrentProject() {
        control.setCurrentProject("TestProject");
        assertEquals(control.getCurrProjectName(), "TestProject");
    }

    @Test
    void addNewProject() throws IOException {
        control.addNewProject("NewProject");
        assertTrue(control.getProjectNames().contains("NewProject"));
        control.setCurrentProject("NewProject");
        control.deleteCurrentProject();
    }

    @Test
    void deleteCurrentProject() throws IOException {
        boolean result = control.deleteCurrentProject();
        assertFalse(control.getProjectNames().contains("TestProject"));
        assertFalse(new ArrayList(List.of(new File(testUser.getUserPath().toString()).list())).contains("TestProject"));
        assertTrue(result);
    }

    @Test
    void addNewPurchase() {
        control.addNewPurchase("NewPurchase", "3.00");
        assertTrue(control.getPurchases().contains("NewPurchase"));
        double expected = 4.50;
        assertEquals(expected, control.getProjectCost().doubleValue());
    }

    @Test
    void editPurchaseCost() {
        control.editPurchaseCost("TestPurchase1", new BigDecimal("2.00"));
        assertEquals(new BigDecimal("2.00"), control.getPurchaseCost("TestPurchase1"));
        assertEquals(new BigDecimal("2.50"), control.getProjectCost());
    }

    @Test
    void deletePurchase() {
        control.deletePurchase("TestPurchase1");
        assertFalse(control.getPurchases().contains("TestPurchase1"));
        assertEquals(new BigDecimal("0.50"), control.getProjectCost());
    }

    @Test
    void addNewTask() {
        assertTrue(control.addNewTask("NewTask"));
        assertTrue(control.getTasks().contains("NewTask"));
    }

    @Test
    void setCurrentTask() {
        control.addNewTask("NewTask");
        control.setCurrentTask("NewTask");
        assertEquals("NewTask", control.getCurrTaskName());
    }

    @Test
    void deleteCurrentTask() {
        control.deleteCurrentTask();
        assertFalse(control.getTasks().contains("TestTask"));
        assertEquals(new BigDecimal("0.00"), control.getProjectCost());
    }

    @Test
    void getCurrProjectName() {
        assertEquals("TestProject", control.getCurrProjectName());
    }

    @Test
    void getCurrTaskName() {
        assertEquals("TestTask", control.getCurrTaskName());
    }

    @Test
    void getTasks() {
        String[] tasks = {"TestTask"};
        assertArrayEquals(tasks, control.getTasks().toArray());
    }

    @Test
    void getProjectBudget() {
        assertEquals(new BigDecimal("0.00"), control.getProjectBudget());
    }

    @Test
    void setProjectBudget() {
        control.setProjectBudget(new BigDecimal("30.49"));
        assertEquals(new BigDecimal("30.49"), control.getProjectBudget());
    }

    @Test
    void getProjectCost() {
        assertEquals(new BigDecimal("1.50"), control.getProjectCost());
    }

    @Test
    void setPurchaseStatus() {
        control.setPurchaseStatus("TestPurchase1", true);
        control.setPurchaseStatus("TestPurchase2", true);
        assertTrue(control.getProjectStatus());
    }

    @Test
    void getPurchases() {
        List<String> purchases = control.getPurchases();
        assertTrue(purchases.contains("TestPurchase1") && purchases.contains("TestPurchase2"));
    }

    @Test
    void getPurchaseCost() {
        assertEquals(new BigDecimal("1.00"), control.getPurchaseCost("TestPurchase1"));
    }

    @Test
    void logoutUser() {
        control.logoutUser();
        assertNull(control.getCurrentUser());
        assertNull(control.getCurrProjectName());
        assertNull(control.getCurrTaskName());
    }

    @Test
    void getCurrentUser() {
        assertSame(testUser, control.getCurrentUser());
    }
}