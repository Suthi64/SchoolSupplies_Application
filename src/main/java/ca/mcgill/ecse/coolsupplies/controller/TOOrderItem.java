/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;

// line 57 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOOrderItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOOrderItem Attributes
  private int quantity;
  private String itemName;
  private String gradeBundleName;
  private int price;
  private double discount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOOrderItem(int aQuantity, String aItemName, String aGradeBundleName, int aPrice, double aDiscount)
  {
    quantity = aQuantity;
    itemName = aItemName;
    gradeBundleName = aGradeBundleName;
    price = aPrice;
    discount = aDiscount;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getQuantity()
  {
    return quantity;
  }

  public String getItemName()
  {
    return itemName;
  }

  public String getGradeBundleName()
  {
    return gradeBundleName;
  }

  public int getPrice()
  {
    return price;
  }

  public double getDiscount()
  {
    return discount;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "," +
            "itemName" + ":" + getItemName()+ "," +
            "gradeBundleName" + ":" + getGradeBundleName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "discount" + ":" + getDiscount()+ "]";
  }
}