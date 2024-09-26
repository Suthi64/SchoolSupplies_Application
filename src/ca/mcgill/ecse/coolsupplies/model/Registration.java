/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 33 "../../../../../uml.ump"
public class Registration
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Registration Associations
  private ParentAccount accountOf;
  private Parent parent;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetAccountOf;
  private boolean canSetParent;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Registration(ParentAccount aAccountOf, Parent aParent)
  {
    cachedHashCode = -1;
    canSetAccountOf = true;
    canSetParent = true;
    if (aAccountOf == null || aAccountOf.getRegistration() != null)
    {
      throw new RuntimeException("Unable to create Registration due to aAccountOf. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    accountOf = aAccountOf;
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create registration due to parent. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Registration(String aEmailForAccountOf, String aPasswordForAccountOf, Parent aParent)
  {
    accountOf = new ParentAccount(aEmailForAccountOf, aPasswordForAccountOf, this);
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create registration due to parent. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public ParentAccount getAccountOf()
  {
    return accountOf;
  }
  /* Code from template association_GetOne */
  public Parent getParent()
  {
    return parent;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setParent(Parent aNewParent)
  {
    boolean wasSet = false;
    if (!canSetParent) { return false; }
    if (aNewParent == null)
    {
      //Unable to setParent to null, as registration must always be associated to a parent
      return wasSet;
    }
    
    Registration existingRegistration = aNewParent.getRegistration();
    if (existingRegistration != null && !equals(existingRegistration))
    {
      //Unable to setParent, the current parent already has a registration, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Parent anOldParent = parent;
    parent = aNewParent;
    parent.setRegistration(this);

    if (anOldParent != null)
    {
      anOldParent.setRegistration(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    Registration compareTo = (Registration)obj;
  
    if (getAccountOf() == null && compareTo.getAccountOf() != null)
    {
      return false;
    }
    else if (getAccountOf() != null && !getAccountOf().equals(compareTo.getAccountOf()))
    {
      return false;
    }

    if (getParent() == null && compareTo.getParent() != null)
    {
      return false;
    }
    else if (getParent() != null && !getParent().equals(compareTo.getParent()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getAccountOf() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getAccountOf().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getParent() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getParent().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetAccountOf = false;
    canSetParent = false;
    return cachedHashCode;
  }

  public void delete()
  {
    ParentAccount existingAccountOf = accountOf;
    accountOf = null;
    if (existingAccountOf != null)
    {
      existingAccountOf.delete();
    }
    Parent existingParent = parent;
    parent = null;
    if (existingParent != null)
    {
      existingParent.setRegistration(null);
    }
  }

}