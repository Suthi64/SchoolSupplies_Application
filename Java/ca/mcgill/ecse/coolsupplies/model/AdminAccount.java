/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 42 "../../../../../../uml.ump"
public class AdminAccount extends UserAccount
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, AdminAccount> adminaccountsByEmail = new HashMap<String, AdminAccount>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AdminAccount Attributes
  private String email;
  private String password;

  //Helper Variables
  private boolean canSetEmail;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AdminAccount()
  {
    super();
    canSetEmail = true;
    password = “admin”;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See https://manual.umple.org?RE003ViolationofUniqueness.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetImmutable */
  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    if (!canSetEmail) { return false; }
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    canSetEmail = false;
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      adminaccountsByEmail.remove(anOldEmail);
    }
    adminaccountsByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  /**
   * I added unique since there are multiple admins and they all have different emails
   */
  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static AdminAccount getWithEmail(String aEmail)
  {
    return adminaccountsByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public void delete()
  {
    adminaccountsByEmail.remove(getEmail());
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}