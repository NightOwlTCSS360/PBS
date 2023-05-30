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

class TaskTest {

    Task testerTask;

    @BeforeEach
    void setUp() {
        testerTask = new Task("CustomTask");
    }

    @Test
    void addPurchase() {
        Purchase testerPurchase = new Purchase("PurchaseTest", new BigDecimal("12.50"));
        testerTask.addPurchase(testerPurchase);
        Purchase addedPurchase = testerTask.getPurchase("PurchaseTest");
        assertSame(testerPurchase, addedPurchase);
    }

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

    @Test
    void recalculateCompletedOnePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        Purchase purchaseToTest = testerTask.getPurchase("PurchaseTest1");
        purchaseToTest.setCompletedStatus(true);
        testerTask.recalculateCompleted();
        assertTrue(testerTask.getCompletedStatus());
    }

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

    @Test
    void recalculateNotCompleted() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        testerTask.recalculateCompleted();
        assertFalse(testerTask.getCompletedStatus());
    }

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

    @Test
    void getTotalCostNoPurchases() {
        assertEquals(new BigDecimal("0.0"), testerTask.getTotalCost());
    }

    @Test
    void getTotalCostOnePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        assertEquals(new BigDecimal("12.50"), testerTask.getTotalCost());
    }

    @Test
    void getTotalCostMultiplePurchases() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        Purchase secondTesterPurchase = new Purchase("PurchaseTest2", new BigDecimal("6.25"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.addPurchase(secondTesterPurchase);
        assertEquals(new BigDecimal("18.75"), testerTask.getTotalCost());
    }

    @Test
    void getCompletedStatusNoPurchases() {
        assertTrue(testerTask.getCompletedStatus());
    }

    @Test
    void deletePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        testerTask.deletePurchase(firstTesterPurchase);
        assertNull(testerTask.getPurchase("PurchaseTest1"));
    }

    @Test
    void getPurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        assertEquals(firstTesterPurchase, testerTask.getPurchase("PurchaseTest1"));
    }

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

    @Test
    void getAllPurchaseNamesOnePurchase() {
        Purchase firstTesterPurchase = new Purchase("PurchaseTest1", new BigDecimal("12.50"));
        testerTask.addPurchase(firstTesterPurchase);
        Set<String> setOfPurchases = new HashSet<>();
        setOfPurchases.add("PurchaseTest1");
        assertEquals(setOfPurchases, testerTask.getAllPurchaseNames());
    }

    @Test
    void getAllPurchaseNamesNoPurchases() {
        Set<String> setOfPurchases = new HashSet<>();
        assertEquals(setOfPurchases, testerTask.getAllPurchaseNames());
    }

    @Test
    void getTaskName() {
        assertEquals("CustomTask", testerTask.getTaskName());
    }
}