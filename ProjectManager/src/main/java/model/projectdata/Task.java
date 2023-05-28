package model.projectdata;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

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
    private List<Purchase> myPurchases;
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
        myPurchases = new ArrayList<>();
    }

    /**
     * Adds a Purchase to this Task
     * @author Paul Schmidt
     * @param thePurchase the Purchase to associate with this Task
     */
    public void addPurchase(final Purchase thePurchase) {
        myPurchases.add(thePurchase);
    }

    /**
     * Export method for serialization if needed
     * @author Paul Schmidt
     */
    public void export ()
    {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("/appdata/t_" + myTaskName + ".ser", true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception ex) {

        }
        finally {
            if(oos != null){
                try {oos.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * Deletes the passed purchase from the list of purchases.
     * @param thePurchase the purchase object we want to delete.
     */
    public void deletePurchase(Purchase thePurchase){
        myPurchases.remove(thePurchase);
    }

    /**
     * Returns the purchase with the name passed in the parameter. If the purchase is not found,
     * the method returns null.
     * @param thePurchaseName the name of the purchase we are looking for as a String.
     * @return a Purchase object if the purchase exists in the task, null otherwise.
     * @author Derek J. Ruiz Garcia
     */
    public Purchase getPurchase(String thePurchaseName) {
        for (Purchase p: myPurchases) {
            if (p.getPurchaseName().equals(thePurchaseName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns a list of purchase located in this task.
     * @return a list of purchase objects located in this task.
     * @author Derek J. Ruiz Garcia
     */
    public List<Purchase> getAllPurchases(){
        return List.copyOf(myPurchases);
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
        sb. append("Task Name: " + getTaskName());
        for (Purchase p : myPurchases) {
            sb.append("\n        " + p.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
