/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;

// line 32 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOGradeBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGradeBundle Attributes
  private String name;
  private int discount;
  private String gradeLevel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGradeBundle(String aName, int aDiscount, String aGradeLevel)
  {
    name = aName;
    discount = aDiscount;
    gradeLevel = aGradeLevel;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return name;
  }

  public int getDiscount()
  {
    return discount;
  }

  public String getGradeLevel()
  {
    return gradeLevel;
  }

  public void delete()
  {}


  @Override
  // line 38 "../../../../../CoolSuppliesTransferObjects.ump"
   public String toString(){
    return "Name: "+ name;
  }

}