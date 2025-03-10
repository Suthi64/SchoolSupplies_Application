/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 25 "../../../../../CoolSupplies.ump"
public class SchoolAdmin extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SchoolAdmin Associations
  private CoolSupplies coolSupplies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SchoolAdmin(String aEmail, String aPassword, CoolSupplies aCoolSupplies)
  {
    super(aEmail, aPassword);
    boolean didAddCoolSupplies = setCoolSupplies(aCoolSupplies);
    if (!didAddCoolSupplies)
    {
      throw new RuntimeException("Unable to create admin due to coolSupplies. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public CoolSupplies getCoolSupplies()
  {
    return coolSupplies;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCoolSupplies(CoolSupplies aNewCoolSupplies)
  {
    boolean wasSet = false;
    if (aNewCoolSupplies == null)
    {
      //Unable to setCoolSupplies to null, as admin must always be associated to a coolSupplies
      return wasSet;
    }
    
    SchoolAdmin existingAdmin = aNewCoolSupplies.getAdmin();
    if (existingAdmin != null && !equals(existingAdmin))
    {
      //Unable to setCoolSupplies, the current coolSupplies already has a admin, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    CoolSupplies anOldCoolSupplies = coolSupplies;
    coolSupplies = aNewCoolSupplies;
    coolSupplies.setAdmin(this);

    if (anOldCoolSupplies != null)
    {
      anOldCoolSupplies.setAdmin(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    CoolSupplies existingCoolSupplies = coolSupplies;
    coolSupplies = null;
    if (existingCoolSupplies != null)
    {
      existingCoolSupplies.setAdmin(null);
    }
    super.delete();
  }

}