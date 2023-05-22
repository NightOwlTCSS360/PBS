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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb. append("Task Name: " + myTaskName);
        for (Purchase p : myPurchases) {
            sb.append("\n        " + p.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
