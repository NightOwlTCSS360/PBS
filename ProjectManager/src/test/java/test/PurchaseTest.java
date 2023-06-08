package test;

import model.projectdata.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A tester for the purchase class.
 * @author Derek J. Ruiz Garcia
 */
class PurchaseTest {

    /** The purchase we are going to test. */
    Purchase testerPurchase;

    /** The setup method ofr the purchase that is going to be tested. */
    @BeforeEach
    void setUp() {
        testerPurchase = new Purchase("Chair", new BigDecimal("8.50"));
    }

    /**
     * Tests the editCost method of the purchase class using a bigDecimal.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void editCost() {
        testerPurchase.editCost(new BigDecimal("12.75"));
        assertEquals(new BigDecimal("12.75"), testerPurchase.getCost());
    }

    /**
     * Tests the getCost method of the purchase class after the purchase is created.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getCost() {
        assertEquals(new BigDecimal("8.50"), testerPurchase.getCost());
    }

    /**
     * Tests the setCompletedStatus method of the purchase class, setting it to true.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void setCompletedStatus() {
        testerPurchase.setCompletedStatus(true);
        assertTrue(testerPurchase.getCompletedStatus());
    }

    /**
     * Tests the setCompletedStatus method of the purchase class, setting it to true and then setting it to false.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void setNotCompletedStatus() {
        testerPurchase.setCompletedStatus(true);
        testerPurchase.setCompletedStatus(false);
        assertFalse(testerPurchase.getCompletedStatus());
    }

    /**
     * Tests the getCompletedStatus of the purchase class after it is created.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getCompletedStatus() {
        assertFalse(testerPurchase.getCompletedStatus());
    }

    /**
     * Tests the getName method of the purchase class after the purchase is created.
     * @author Derek J. Ruiz Garcia
     */
    @Test
    void getName() {
        assertEquals("Chair", testerPurchase.getName());
    }
}