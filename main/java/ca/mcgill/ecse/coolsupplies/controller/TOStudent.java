/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;

// line 14 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOStudent
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOStudent Attributes
  private String name;
  private String gradeLevel;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOStudent(String aName, String aGradeLevel)
  {
    name = aName;
    gradeLevel = aGradeLevel;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return name;
  }

  public String getGradeLevel()
  {
    return gradeLevel;
  }

  public void delete()
  {}


  @Override
  // line 19 "../../../../../CoolSuppliesTransferObjects.ump"
   public String toString(){
    return "Name: "+ name;
  }

}