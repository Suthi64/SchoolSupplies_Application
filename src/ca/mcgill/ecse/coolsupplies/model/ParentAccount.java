/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 51 "../../../../../uml.ump"
public class ParentAccount extends UserAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ParentAccount Attributes
  private String name;
  private int phoneNumber;

  //ParentAccount Associations
  private Registration registration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ParentAccount(String aEmail, String aPassword, Registration aRegistration)
  {
    super(aEmail, aPassword);
    name = null;
    phoneNumber = 0;
    if (aRegistration == null || aRegistration.getAccountOf() != null)
    {
      throw new RuntimeException("Unable to create ParentAccount due to aRegistration. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    registration = aRegistration;
  }

  public ParentAccount(String aEmail, String aPassword, Parent aParentForRegistration)
  {
    super(aEmail, aPassword);
    name = null;
    phoneNumber = 0;
    registration = new Registration(this, aParentForRegistration);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetOne */
  public Registration getRegistration()
  {
    return registration;
  }

  public void delete()
  {
    Registration existingRegistration = registration;
    registration = null;
    if (existingRegistration != null)
    {
      existingRegistration.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "registration = "+(getRegistration()!=null?Integer.toHexString(System.identityHashCode(getRegistration())):"null");
  }
}