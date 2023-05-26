package model.projectdata;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
    private String myPurchaseName;
    /**
     * Cost of the Purchase
     */
    private BigDecimal myCost;
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
    public Purchase(final String thePurchaseName, final BigDecimal theCost) {
        myPurchaseName = thePurchaseName;
        myCost = theCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Purchase Name: " + myPurchaseName + " Cost: %.2f", myCost));
        sb.append("\n");
        return sb.toString();
    }

    public BigDecimal getCost() {
        return myCost;
    }

    public void editCost(BigDecimal newCost) {
        myCost = newCost;
    }
    public String getName() {
        return myPurchaseName;
    }
}
