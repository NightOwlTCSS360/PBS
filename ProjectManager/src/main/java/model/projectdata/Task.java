package model.projectdata;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Task implements Serializable {
    private String myTaskName;
    private List<Purchase> myPurchases;

    public Task (final String theTaskName) {
        myTaskName = theTaskName;
        myPurchases = new ArrayList<>();
    }

    public void addPurchase(final Purchase thePurchase) {
        myPurchases.add(thePurchase);
    }
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
