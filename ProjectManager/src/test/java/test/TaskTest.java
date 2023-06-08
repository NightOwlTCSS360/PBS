package test;

import model.projectdata.Purchase;
import model.projectdata.Task;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A tester for the task class.
 * @author Derek J. Ruiz Garcia
 */
class TaskTest {

    /** The task that is going to be tested. */
    Task testerTask;

    /** The setup method for the tests of the task. */
    @BeforeEach
    void setUp() {
        testerTask = new Task("CustomTask");
    }

    /**
     * Tests the addPurchase method of the task class after a purchase is added to it the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void addPurchase() {
        Purchase testerPurchase = new Purchase("PurchaseTest", new BigDecimal("12.50"));
        testerTask.addPurchase(testerPurchase);
        Purchase addedPurchase = testerTask.getPurchase("PurchaseTest");
        assertSame(testerPurchase, addedPurchase);
    }

    /**
     * Tests the addPurchase method of the task class after multiple purchases are added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void addMultiplePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        Purchase addedFirstPurchase = testerTask.getPurchase("PurchaseTest1");
        testerTask.addPurchase(secondTesterPurchase);
        Purchase addedSecondPurchase = testerTask.getPurchase("PurchaseTest2");
        assertSame(firstTesterPurchase, addedFirstPurchase);
        assertSame(secondTesterPurchase, addedSecondPurchase);
    }

    /**
     * Tests the recalculateCompleted method of the task class after a completed purchase is added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateCompletedOnePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        Purchase purchaseToTest = testerTask.getPurchase("PurchaseTest1");
        purchaseToTest.setCompletedStatus(true);
        testerTask.recalculateCompleted();
        assertTrue(testerTask.getCompletedStatus());
    }

    /**
     * Tests the recalculateCompleted method of the task class after multiple completed purchases are added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateCompletedMultiplePurchases() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        Purchase firstPurchaseToTest = testerTask.getPurchase("PurchaseTest1");
        Purchase secondPurchaseToTest = testerTask.getPurchase("PurchaseTest2");
        firstPurchaseToTest.setCompletedStatus(true);
        secondPurchaseToTest.setCompletedStatus(true);
        testerTask.recalculateCompleted();
        assertTrue(testerTask.getCompletedStatus());
    }

    /**
     * Tests the recalculateCompleted method of the task class after two uncompleted purchases are added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateNotCompleted() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        testerTask.recalculateCompleted();
        assertFalse(testerTask.getCompletedStatus());
    }

    /**
     * Tests the recalculateCompleted method of the task class after one completed purchase and
     * one uncompleted purchase is added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void recalculateNotCompletedOnePurchaseCompleted() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        Purchase firstPurchaseToTest = testerTask.getPurchase("PurchaseTest1");
        firstPurchaseToTest.setCompletedStatus(true);
        testerTask.recalculateCompleted();
        assertFalse(testerTask.getCompletedStatus());
    }

    /**
     * Tests the getTotalCost method of the task class after the task is created.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getTotalCostNoPurchases() {
        assertEquals(new BigDecimal("0.0"), testerTask.getTotalCost());
    }

    /**
     * Tests the getTotalCost method of the task class after one purchase with a specific cost
     * is added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getTotalCostOnePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        assertEquals(new BigDecimal("12.50"), testerTask.getTotalCost());
    }

    /**
     * Tests the getTotalCost method of the task class after two purchases with different costs
     * are added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getTotalCostMultiplePurchases() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        assertEquals(new BigDecimal("18.75"), testerTask.getTotalCost());
    }

    /**
     * Tests the getCompletedStatus method of the task class after the task is created which means no purchases.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getCompletedStatusNoPurchases() {
        assertTrue(testerTask.getCompletedStatus());
    }

    /**
     * Tests the deletePurchase method of the task class.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void deletePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.deletePurchase(firstTesterPurchase);
        assertNull(testerTask.getPurchase("PurchaseTest1"));
    }

    /**
     * Tests the getPurchase method of the task class after a purchase is added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getPurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        assertEquals(firstTesterPurchase, testerTask.getPurchase("PurchaseTest1"));
    }

    /**
     * Tests the getAllPurchaseNames method of the task class after more than one purchase is added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getAllPurchaseNamesMultiplePurchases() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        Purchase thirdTesterPurchase = new Purchase("PurchaseTest3", new BigDecimal("3.12"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        testerTask.addPurchase(thirdTesterPurchase);
        Set<String> setOfPurchases = new HashSet<>();
        setOfPurchases.add("PurchaseTest1");
        setOfPurchases.add("PurchaseTest2");
        setOfPurchases.add("PurchaseTest3");

        assertEquals(setOfPurchases, testerTask.getAllPurchaseNames());
    }

    /**
     * Tests the getAllPurchaseNames method of the task class after one purchase is added to the task.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getAllPurchaseNamesOnePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        Set<String> setOfPurchases = new HashSet<>();
        setOfPurchases.add("PurchaseTest1");
        assertEquals(setOfPurchases, testerTask.getAllPurchaseNames());
    }

    /**
     * Tests the getAllPurchaseNames method of the task class task is created, and it has no purchases.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getAllPurchaseNamesNoPurchases() {
        Set<String> setOfPurchases = new HashSet<>();
        assertEquals(setOfPurchases, testerTask.getAllPurchaseNames());
    }

    /**
     * Tests the getTaskName method of the task class after the task is created.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getTaskName() {
        assertEquals("CustomTask", testerTask.getTaskName());
    }
}