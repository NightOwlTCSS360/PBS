package test;

import control.PDC;
import model.User;
import model.projectdata.Project;
import model.projectdata.Purchase;
import model.projectdata.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    Project testProject;

    PDC control = new PDC();

    User testUser = new User("myFirstName", "myLastName", "myEmail", "myPassword");

    @BeforeEach
    void setUp() throws IOException {
        testProject = new Project(testUser, "myProject");
        Task testTask = new Task("testTask");
        Purchase testPurchase = new Purchase("testPurchase", new BigDecimal("12.00"));
        testTask.addPurchase(testPurchase);
        testProject.addTask(testTask);
        testUser.addProject(testProject);
        control.setCurrentUser(testUser);
        control.setCurrentProject("myProject");
        control.setCurrentTask("testTask");
    }

    @Test
    void addTask() {
        Task testTask = new Task("testTaskAdded");
        testProject.addTask(testTask);
        assertEquals(testTask, testProject.getTask("testTaskAdded"));
    }

    @Test
    void deleteTask() {
        testProject.deleteTask("testTask");
        assertNull(testProject.getTask("myTask"));
    }

    @Test
    void serializeAndDeserialize() throws IOException {
        Project secondTestProject = new Project(testUser, "mySecondProject");
        Task testTask = new Task("testTask");
        Purchase testPurchase = new Purchase("testPurchase", new BigDecimal("36.00"));
        testTask.addPurchase(testPurchase);
        secondTestProject.addTask(testTask);
        testUser.addProject(secondTestProject);
        control.setCurrentProject("mySecondProject");
        control.setCurrentTask("testTask");

        secondTestProject.serialize(PDC.myDir);
        String filePath = PDC.myDir + "myEmail\\mySecondProject\\mySecondProject.ser";
        File projectFile = new File(filePath);
        if(projectFile.exists()){
            Project obtainedProject = new Project(testUser, Project.deserialize(filePath));
            if(!secondTestProject.getMyProjectName().equals(obtainedProject.getMyProjectName())
                    || !secondTestProject.getTask("testTask").getTaskName().equals(obtainedProject.getTask("testTask").getTaskName())
                    || !secondTestProject.getTask("testTask").getPurchase("testPurchase").getName().equals(obtainedProject.getTask("testTask").getPurchase("testPurchase").getName())){

                fail("They are not equal");
            }
        } else{
            fail("The project file doesn't exist");
        }
    }

    @Test
    void getInitialProjectEstimate() {
        assertEquals(new BigDecimal("0.00"), testProject.getProjectEstimate());
    }

    @Test
    void setProjectEstimate() {
        testProject.setProjectEstimate(new BigDecimal("35.00"));
        assertEquals(new BigDecimal("35.00"), testProject.getProjectEstimate());
    }

    @Test
    void recalculateTotalCost() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);
        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("20.00"), testProject.getProjectCost());
    }

    @Test
    void recalculateCompleted() {
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.recalculateCompleted();
        assertTrue(testProject.getCompletedStatus());
    }

    @Test
    void recalculateNotCompleted() {
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(false);
        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertFalse(testProject.getCompletedStatus());
    }

    @Test
    void recalculateNotCompletedOneCompleted() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.getTask("testTask").recalculateCompleted();
        testProject.getTask("testTask2").recalculateCompleted();
        testProject.recalculateCompleted();
        assertFalse(testProject.getCompletedStatus());
    }

    @Test
    void recalculateMultipleTasksCompleted() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.getTask("testTask2").getPurchase("testPurchase2").setCompletedStatus(true);
        testProject.getTask("testTask").recalculateCompleted();
        testProject.getTask("testTask2").recalculateCompleted();
        testProject.recalculateCompleted();
        assertTrue(testProject.getCompletedStatus());
    }

    @Test
    void recalculateMultiplePurchasesNotCompleted() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        testProject.getTask("testTask").addPurchase(secondTestPurchase);

        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.getTask("testTask").getPurchase("testPurchase2").setCompletedStatus(false);

        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertFalse(testProject.getCompletedStatus());
    }

    @Test
    void recalculateMultiplePurchasesCompleted() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        testProject.getTask("testTask").addPurchase(secondTestPurchase);

        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.getTask("testTask").getPurchase("testPurchase2").setCompletedStatus(true);

        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertTrue(testProject.getCompletedStatus());
    }

    @Test
    void getAllTaskNamesOneTask() {
        Set<String> mySet = new HashSet<>();
        mySet.add("testTask");
        assertEquals(mySet, testProject.getAllTaskNames());
    }

    @Test
    void getAllTaskNamesMultipleTasks() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);

        Set<String> mySet = new HashSet<>();
        mySet.add("testTask");
        mySet.add("testTask2");
        assertEquals(mySet, testProject.getAllTaskNames());
    }

    //This one is weird
    @Test
    void getTask() {
        Task testTask2 = new Task("testTask2");
        Purchase testPurchase = new Purchase("testPurchase", new BigDecimal("12.00"));
        testTask2.addPurchase(testPurchase);

        testProject.addTask(testTask2);
        assertEquals(testTask2, testProject.getTask("testTask2"));
    }

    @Test
    void getProjectCost() {
        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("12.00"), testProject.getProjectCost());
    }

    @Test
    void getProjectCostMultiplePurchases() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        testProject.getTask("testTask").addPurchase(secondTestPurchase);

        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("20.00"), testProject.getProjectCost());
    }

    @Test
    void getProjectCostMultipleTasks() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);

        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("20.00"), testProject.getProjectCost());
    }

    @Test
    void getDirectoryPath() {
        String path = PDC.myDir + "myEmail\\myProject";
        assertEquals(path, testProject.getDirectoryPath().toString());
    }

    @Test
    void getMyFilePath() {
        String path = PDC.myDir + "myEmail\\myProject\\myProject.ser";
        assertEquals(path, testProject.getMyFilePath().toString());
    }

    @Test
    void getMyProjectName() {
        assertEquals("myProject", testProject.getMyProjectName());
    }

    @Test
    void getCompletedStatus() {
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertTrue(testProject.getCompletedStatus());
    }

    @Test
    void getNotCompletedStatus() {
        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertFalse(testProject.getCompletedStatus());
    }

}