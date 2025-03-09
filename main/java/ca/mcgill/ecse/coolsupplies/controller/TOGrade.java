/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;

// line 54 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOGrade
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOGrade Attributes
  private String level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOGrade(String aLevel)
  {
    level = aLevel;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getLevel()
  {
    return level;
  }

  public void delete()
  {}


  @Override
  // line 58 "../../../../../CoolSuppliesTransferObjects.ump"
   public String toString(){
    return "Level: "+ level;
  }

}