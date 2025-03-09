/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;

// line 23 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOItem Attributes
  private String name;
  private int price;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOItem(String aName, int aPrice)
  {
    name = aName;
    price = aPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return name;
  }

  public int getPrice()
  {
    return price;
  }

  public void delete()
  {}


  @Override
  // line 28 "../../../../../CoolSuppliesTransferObjects.ump"
   public String toString(){
    return "Name: "+ name;
  }

}