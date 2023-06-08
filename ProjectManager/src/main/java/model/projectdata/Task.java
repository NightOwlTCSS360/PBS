package model.projectdata;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents a Task associated with a Project
 * @author Paul Schmidt
 */
public class Task implements Serializable {

    /** Name of the Task */
    private final String myTaskName;

    /** List of the Purchases associated with the Task */
    private final Map<String, Purchase> myPurchases;

    /** The cumulative costs of the Purchases associated with this Task. */
    private BigDecimal myCost;

    /** Reflects whether this Task is complete or not. */
    private boolean completedStatus;

    /** Serial ID */
    @Serial
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
        recalculateCompleted();
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
     * Iterates through this Task's Purchases to calculate whether all Purchases are completed or not.
     * Assigns this Task's completed status to the result.
     * @author Paul Schmidt
     */
    public void recalculateCompleted() {
        boolean result = true;
        for (Purchase thePurchase : myPurchases.values()) {
            if (!thePurchase.getCompletedStatus()) {
                result = false;
                break;
            }
        }
        completedStatus = result;
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
     * Returns whether this Task is completed or not.
     * @author Paul Schmidt
     * @return true if all Purchases are complete, false otherwise.
     */
    public boolean getCompletedStatus() {
        return completedStatus;
    }

    /**
     * Deletes the passed purchase from the list of purchases.
     * @author Derek J. Ruiz Garcia
     * @param thePurchase the purchase object we want to delete.
     */
    public void deletePurchase(Purchase thePurchase){
        myPurchases.remove(thePurchase.getName());
    }

    /**
     * Returns the purchase with the name passed in the parameter. If the purchase is not found,
     * the method returns null.
     * @param thePurchaseName the name of the purchase we are looking for as a String.
     * @return a Purchase object if the purchase exists in the task, null otherwise.
     * @author Derek J. Ruiz Garcia
     */
    public Purchase getPurchase(String thePurchaseName) {
        return myPurchases.get(thePurchaseName);
    }

    /**
     * Returns a Set of purchase located in this task.
     * @return a Set of purchase objects located in this task.
     * @author Derek J. Ruiz Garcia
     */
    public Set<String> getAllPurchaseNames(){
        return myPurchases.keySet();
    }

    /**
     * Returns the name of the task.
     * @return the name of the task as a string.
     * @author Derek J. Ruiz Garcia
     */
    public String getTaskName(){
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
