/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;

// line 24 "uml.ump"
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
    boolean didAddSchool = setSchool(aSchool);
    if (!didAddSchool)
    {
      throw new RuntimeException("Unable to create admin due to school. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public School getSchool()
  {
    return school;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setSchool(School aSchool)
  {
    boolean wasSet = false;
    //Must provide school to admin
    if (aSchool == null)
    {
      return wasSet;
    }

    if (school != null && school.numberOfAdmins() <= School.minimumNumberOfAdmins())
    {
      return wasSet;
    }

    School existingSchool = school;
    school = aSchool;
    if (existingSchool != null && !existingSchool.equals(aSchool))
    {
      boolean didRemove = existingSchool.removeAdmin(this);
      if (!didRemove)
      {
        school = existingSchool;
        return wasSet;
      }
    }
    school.addAdmin(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    School placeholderSchool = school;
    this.school = null;
    if(placeholderSchool != null)
    {
      placeholderSchool.removeAdmin(this);
    }
    super.delete();
  }

}