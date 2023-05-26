package model.projectdata;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a Task associated with a Project
 * @author Paul Schmidt
 */
public class Task implements Serializable {
    /**
     * Name of the Task
     */
    private final String myTaskName;
    /**
     * List of the Purchases associated with the Task
     */
    private final Map<String, Purchase> myPurchases;
    /**
     * The cumulative costs of the Purchases associated with this Task.
     */
    private BigDecimal myCost;
    /**
     * Serial ID
     */@Serial
    private static final long serialVersionUID = 3152023L;

     //CONSTRUCTORS
    /**
     * Constructs a new Task with the given Name
     * @author Paul Schmidt
     * @param theTaskName the name of the Task
     */
    public Task (final String theTaskName) {
        myTaskName = theTaskName;
        myPurchases = new HashMap<>();
        myCost = new BigDecimal("0.0");
    }

    //PUBLIC METHODS
    /**
     * Adds a Purchase to this Task
     * @author Paul Schmidt
     * @param thePurchase the Purchase to associate with this Task
     */
    public void addPurchase(final Purchase thePurchase) {
        myPurchases.put(thePurchase.getName(), thePurchase);
        recalculateCost();
    }

    /**
     * Returns the total costs associated with this Task as a BigDecimal.
     * @author Paul Schmidt
     * @return the total costs associated with this Task
     */
    public BigDecimal getTotalCost() {
        return myCost;
    }

    /**
     * Returns the name of this Task as a String.
     * @author Paul Schmidt
     * @return the name of this Task.
     */
    public String getMyTaskName() {
        return myTaskName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task Name: ");
        sb.append(myTaskName);
        for (Purchase p : myPurchases.values()) {
            sb.append("\n        ");
            sb.append(p);
        }
        sb.append("\n");
        return sb.toString();
    }

    //PRIVATE METHODS

    /**
     * Used to recalculate the cumulative cost associated with this Task.
     * @author Paul Schmidt
     */
    void recalculateCost() {
        BigDecimal total = new BigDecimal("0.0");
        for (Purchase p : myPurchases.values()) {
            total =  total.add(p.getCost());
        }
        myCost = total;
    }

}
