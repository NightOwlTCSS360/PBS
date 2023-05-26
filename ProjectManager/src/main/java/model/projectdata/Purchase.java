package model.projectdata;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class representing a Purchase
 * @author Paul Schmidt
 */
public class Purchase implements Serializable {
    /**
     * Name of the purchase
     */
    private final String myPurchaseName;
    /**
     * Cost of the Purchase
     */
    private BigDecimal myCost;
    /**
     * The status of the Purchase. Completed: true | Incomplete: false
     */
    private boolean isCompletedStatus;
    /**
     * Serial ID
     */
    @Serial
    private static final long serialVersionUID = 2152023L;

    //CONSTRUCTORS
    /**
     * Constructs a new Purchase with the given Name and Cost
     * @author Paul Schmidt
     * @param thePurchaseName the name of the Purchase
     * @param theCost the Cost of the Purchase
     */
    public Purchase(final String thePurchaseName, final BigDecimal theCost) {
        myPurchaseName = thePurchaseName;
        myCost = theCost;
        isCompletedStatus = false;
    }

    //PUBLIC METHODS

    /**
     * Sets the completed status of this Purchase.
     * @author Paul Schmidt
     * @param isCompleted the value used to set the status.
     */
    public void setCompletedStatus(final boolean isCompleted) {
        isCompletedStatus = isCompleted;
    }

    /**
     * Returns whether this Purchase is completed or not.
     * @author Paul Schmidt
     * @return Not Complete: false | Completed: true
     */
    public boolean getIsCompleted() {
        return isCompletedStatus;
    }

    /**
     * Sets the cost of this purchase to the new value.
     * @author Paul Schmidt
     * @param newCost the value of the new cost.
     */
    public void editCost(BigDecimal newCost) {
        myCost = newCost;
    }

    /**
     * Returns the cost associated with this Purchase.
     * @author Paul Schmidt
     * @return the cost as a BigDecimal.
     */
    public BigDecimal getCost() {
        return myCost;
    }

    /**
     * Returns the name of this Purchase.
     * @author Paul Schmidt
     * @return the name of the Purchase as a String.
     */
    public String getName() {
        return myPurchaseName;
    }
    @Override
    public String toString() {
        return String.format("Purchase Name: " + myPurchaseName + " Cost: %.2f\n", myCost);
    }
}
