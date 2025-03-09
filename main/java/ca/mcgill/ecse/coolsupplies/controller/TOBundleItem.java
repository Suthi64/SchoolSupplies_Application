/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;

// line 42 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOBundleItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOBundleItem Attributes
  private int quantity;
  private String level;
  private String itemName;
  private String gradeBundleName;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOBundleItem(int aQuantity, String aLevel, String aItemName, String aGradeBundleName)
  {
    quantity = aQuantity;
    level = aLevel;
    itemName = aItemName;
    gradeBundleName = aGradeBundleName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getQuantity()
  {
    return quantity;
  }

  public String getLevel()
  {
    return level;
  }

  public String getItemName()
  {
    return itemName;
  }

  public String getGradeBundleName()
  {
    return gradeBundleName;
  }

  public void delete()
  {}


  @Override
  // line 49 "../../../../../CoolSuppliesTransferObjects.ump"
   public String toString(){
    return "Quantity"+ quantity + ","+ "Level: "+ level +","+ "Item Name: "+ itemName + ","+ "Grade Bundle Name: "+
    gradeBundleName;
  }

}