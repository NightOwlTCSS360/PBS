package model.projectdata;

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
    private String myTaskName;
    /**
     * List of the Purchases associated with the Task
     */
    private Map<String, Purchase> myPurchases;
    /**
     *
     */
    private BigDecimal myCost;
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 3152023L;

    /**
     * Constructs a new Task with the given Name
     * @author Paul Schmidt
     * @param theTaskName the name of the Task
     */
    public Task (final String theTaskName) {
        myTaskName = theTaskName;
        myPurchases = new HashMap<>();
        myCost = new BigDecimal(0.0);
    }
    private void updateCost() {
        BigDecimal total = new BigDecimal(0.0);
        for (Purchase p : myPurchases.values()) {
            total.add(p.getCost());
        }
        myCost = total;
    }
    public BigDecimal getTotalCost() {
        return myCost;
    }
    public String getMyTaskName() {
        return myTaskName;
    }

    /**
     * Adds a Purchase to this Task
     * @author Paul Schmidt
     * @param thePurchase the Purchase to associate with this Task
     */
    public void addPurchase(final Purchase thePurchase) {
        myPurchases.put(thePurchase.getName(), thePurchase);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb. append("Task Name: " + myTaskName);
        for (Purchase p : myPurchases.values()) {
            sb.append("\n        " + p.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
