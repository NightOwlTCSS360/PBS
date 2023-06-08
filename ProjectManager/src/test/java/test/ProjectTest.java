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

/**
 * A tester for the Project class.
 * @author Derek J. Ruiz Garcia
 */
class ProjectTest {

    /** The project we are going to test */
    Project testProject;

    /**  The controller used to interact with the internal data */
    PDC control = new PDC();

    /** The user used to store the project during the tests */
    User testUser = new User("myFirstName", "myLastName", "myEmail", "myPassword");

    /**
     * The setup method for the tests.
     * @author Derek J. Ruiz Garcia
     * @throws IOException
     */
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

    /**
     * Tests the add method in the project class.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void addTask() {
        Task testTask = new Task("testTaskAdded");
        testProject.addTask(testTask);
        assertEquals(testTask, testProject.getTask("testTaskAdded"));
    }

    /**
     * Tests the deleted task method in the project class.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void deleteTask() {
        testProject.deleteTask("testTask");
        assertNull(testProject.getTask("myTask"));
    }

    /**
     * Tests the serialize and the deserialize method in the project class.
     * @author Derek J. Ruiz Garcia
     * @throws IOException
     */
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

    /**
     * Tests the getProjectEstimate method in the project class at the start after a project was created.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getInitialProjectEstimate() {
        assertEquals(new BigDecimal("0.00"), testProject.getProjectEstimate());
    }

    /**
     * Tests the setProjectEstimate method in the project class by using a BigDecimal as a parameter.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void setProjectEstimate() {
        testProject.setProjectEstimate(new BigDecimal("35.00"));
        assertEquals(new BigDecimal("35.00"), testProject.getProjectEstimate());
    }

    /**
     * Tests the recalculateTotalCost after a task with a purchase is added to a project
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateTotalCost() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);
        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("20.00"), testProject.getProjectCost());
    }

    /**
     * Tests the recalculateCompleted method of the project class after a task with a completed purchase
     * is added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateCompleted() {
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.recalculateCompleted();
        assertTrue(testProject.getCompletedStatus());
    }

    /**
     * Tests the recalculateCompleted method of the project class after a task with an uncompleted purchase
     * is added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateNotCompleted() {
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(false);
        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertFalse(testProject.getCompletedStatus());
    }

    /**
     * Tests the recalculateCompleted method of the project class after a task with an uncompleted purchase
     * is added and another task with a completed purchase is added too.
     * @author Derek J. Ruiz Garcia
     */
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

    /**
     * Tests the recalculateCompleted method of the project class after two tasks with completed purchases
     * are added to the project.
     * @author Derek J. Ruiz Garcia
     */
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

    /**
     * Tests the recalculateCompleted method of the project class after a task with an uncompleted purchase
     * and a completed purchase is added to the project.
     * @author Derek J. Ruiz Garcia
     */
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

    /**
     * Tests the recalculateCompleted method of the project class after a task with all purchases completed
     * is added to the project.
     * @author Derek J. Ruiz Garcia
     */
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

    /**
     * Tests the getAllTaskNames of the project class after one task has been added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getAllTaskNamesOneTask() {
        Set<String> mySet = new HashSet<>();
        mySet.add("testTask");
        assertEquals(mySet, testProject.getAllTaskNames());
    }

    /**
     * Tests the getAllTaskNames of the project class after more than one task has been added to the project.
     * @author Derek J. Ruiz Garcia
     */
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

    /**
     * Tests the getTask of the project class after one task with a purchase has been added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getTask() {
        Task testTask2 = new Task("testTask2");
        Purchase testPurchase = new Purchase("testPurchase", new BigDecimal("12.00"));
        testTask2.addPurchase(testPurchase);

        testProject.addTask(testTask2);
        assertEquals(testTask2, testProject.getTask("testTask2"));
    }

    /**
     * Tests the getProjectCost method of the project class after one task with one purchase was added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getProjectCost() {
        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("12.00"), testProject.getProjectCost());
    }

    /**
     * Tests the getProjectCost method of the project class after one task with more than one purchase
     * was added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getProjectCostMultiplePurchases() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        testProject.getTask("testTask").addPurchase(secondTestPurchase);

        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("20.00"), testProject.getProjectCost());
    }

    /**
     * Tests the getProjectCost method of the project class after more than one task with one purchase
     * each was added to the project.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getProjectCostMultipleTasks() {
        Purchase secondTestPurchase = new Purchase("testPurchase2", new BigDecimal("8.00"));
        Task secondTestTask = new Task("testTask2");
        secondTestTask.addPurchase(secondTestPurchase);
        testProject.addTask(secondTestTask);

        testProject.recalculateTotalCost();
        assertEquals(new BigDecimal("20.00"), testProject.getProjectCost());
    }

    /**
     * Tests the getDirectoryPath method of the project class.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getDirectoryPath() {
        String path = PDC.myDir + "myEmail\\myProject";
        assertEquals(path, testProject.getDirectoryPath().toString());
    }

    /**
     * Tests the getMyFilePath of the project class, to get the path of a specific file.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getMyFilePath() {
        String path = PDC.myDir + "myEmail\\myProject\\myProject.ser";
        assertEquals(path, testProject.getMyFilePath().toString());
    }

    /**
     * Tests the getMyProjectName method in the project class.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getMyProjectName() {
        assertEquals("myProject", testProject.getMyProjectName());
    }

    /**
     * Tests the getCompletedStatus of the project class when is completed.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getCompletedStatus() {
        testProject.getTask("testTask").getPurchase("testPurchase").setCompletedStatus(true);
        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertTrue(testProject.getCompletedStatus());
    }

    /**
     * Tests the getCompletedStatus of the project class when is uncompleted.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getNotCompletedStatus() {
        testProject.getTask("testTask").recalculateCompleted();
        testProject.recalculateCompleted();
        assertFalse(testProject.getCompletedStatus());
    }

}