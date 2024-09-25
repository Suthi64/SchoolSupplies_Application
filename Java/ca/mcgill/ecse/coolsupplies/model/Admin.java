/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 25 "../../../../../../uml.ump"
public class Admin extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Associations
  private School school;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(School aSchool)
  {
    super();
    if (aSchool == null || aSchool.getAdmins() != null)
    {
      throw new RuntimeException("Unable to create Admin due to aSchool. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    school = aSchool;
  }

  public Admin(String aNameForSchool, CoolSupplies aCoolSuppliesForSchool)
  {
    super();
    school = new School(aNameForSchool, this, aCoolSuppliesForSchool);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public School getSchool()
  {
    return school;
  }

  public void delete()
  {
    School existingSchool = school;
    school = null;
    if (existingSchool != null)
    {
      existingSchool.delete();
    }
    super.delete();
  }

}