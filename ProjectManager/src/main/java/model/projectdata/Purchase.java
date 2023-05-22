package model.projectdata;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class representing a Purchase
 * @author Paul Schmidt
 */
public class Purchase implements Serializable {
    /**
     * Name of the purchase
     */
    private String myPurchaseName;
    /**
     * Cost of the Purchase
     */
    private double myCost;
    /**
     * Serial ID
     */
    private static final long serialVersionUID = 2152023L;

    /**
     * Constructs a new Purchase with the given Name and Cost
     * @author Paul Schmidt
     * @param thePurchaseName the name of the Purchase
     * @param theCost the Cost of the Purchase
     */
    public Purchase(final String thePurchaseName, final double theCost) {
        myPurchaseName = thePurchaseName;
        myCost = theCost;
    }

    /**
     * Export for serialization if needed
     * @author Paul Schmidt
     */
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
