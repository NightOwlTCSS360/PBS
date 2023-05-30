package test;

import model.projectdata.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {

    Purchase testerPurchase;

    @BeforeEach
    void setUp() {
        testerPurchase = new Purchase("Chair", new BigDecimal("8.50"));
    }

    @Test
    void editCost() {
        testerPurchase.editCost(new BigDecimal("12.75"));
        assertEquals(new BigDecimal("12.75"), testerPurchase.getCost());
    }

    @Test
    void getCost() {
        assertEquals(new BigDecimal("8.50"), testerPurchase.getCost());
    }

    @Test
    void setCompletedStatus() {
        testerPurchase.setCompletedStatus(true);
        assertTrue(testerPurchase.getCompletedStatus());
    }

    @Test
    void setNotCompletedStatus() {
        testerPurchase.setCompletedStatus(true);
        testerPurchase.setCompletedStatus(false);
        assertFalse(testerPurchase.getCompletedStatus());
    }

    @Test
    void getCompletedStatus() {
        assertFalse(testerPurchase.getCompletedStatus());
    }

    @Test
    void getName() {
        assertEquals("Chair", testerPurchase.getName());
    }
}