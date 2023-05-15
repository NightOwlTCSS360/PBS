package model.projectdata;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Purchase implements Serializable {
    private String myPurchaseName;
    private double myCost;

    public Purchase(final String thePurchaseName, final double theCost) {
        myPurchaseName = thePurchaseName;
        myCost = theCost;
    }
    public void export ()
    {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("pu_" + myPurchaseName + ".ser", true);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        sb.append(String.format("Purchase Name: " + myPurchaseName + " Cost: %.2f", myCost));
        sb.append("\n");
        return sb.toString();
    }
}
