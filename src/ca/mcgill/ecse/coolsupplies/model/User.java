/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

/**
 * The following changes are proposed to accommodate for the features 1-5 especially the registration and the login portion
 */
// line 17 "../../../../../CoolSupplies.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Associations
  private List<UserRole> roles;
  private CoolSupplies coolSupplies;
  private List<LoginSession> logins;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(CoolSupplies aCoolSupplies, UserRole... allRoles)
  {
    roles = new ArrayList<UserRole>();
    boolean didAddRoles = setRoles(allRoles);
    if (!didAddRoles)
    {
      throw new RuntimeException("Unable to create User, must have 1 to 2 roles. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCoolSupplies = setCoolSupplies(aCoolSupplies);
    if (!didAddCoolSupplies)
    {
      throw new RuntimeException("Unable to create user due to coolSupplies. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    logins = new ArrayList<LoginSession>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public UserRole getRole(int index)
  {
    UserRole aRole = roles.get(index);
    return aRole;
  }

  public List<UserRole> getRoles()
  {
    List<UserRole> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(UserRole aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }
  /* Code from template association_GetOne */
  public CoolSupplies getCoolSupplies()
  {
    return coolSupplies;
  }
  /* Code from template association_GetMany */
  public LoginSession getLogin(int index)
  {
    LoginSession aLogin = logins.get(index);
    return aLogin;
  }

  public List<LoginSession> getLogins()
  {
    List<LoginSession> newLogins = Collections.unmodifiableList(logins);
    return newLogins;
  }

  public int numberOfLogins()
  {
    int number = logins.size();
    return number;
  }

  public boolean hasLogins()
  {
    boolean has = logins.size() > 0;
    return has;
  }

  public int indexOfLogin(LoginSession aLogin)
  {
    int index = logins.indexOf(aLogin);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRolesValid()
  {
    boolean isValid = numberOfRoles() >= minimumNumberOfRoles() && numberOfRoles() <= maximumNumberOfRoles();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRoles()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRole(UserRole aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    if (numberOfRoles() >= maximumNumberOfRoles())
    {
      return wasAdded;
    }

    roles.add(aRole);
    if (aRole.indexOfUser(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRole.addUser(this);
      if (!wasAdded)
      {
        roles.remove(aRole);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMNToMany */
  public boolean removeRole(UserRole aRole)
  {
    boolean wasRemoved = false;
    if (!roles.contains(aRole))
    {
      return wasRemoved;
    }

    if (numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasRemoved;
    }

    int oldIndex = roles.indexOf(aRole);
    roles.remove(oldIndex);
    if (aRole.indexOfUser(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRole.removeUser(this);
      if (!wasRemoved)
      {
        roles.add(oldIndex,aRole);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToMany */
  public boolean setRoles(UserRole... newRoles)
  {
    boolean wasSet = false;
    ArrayList<UserRole> verifiedRoles = new ArrayList<UserRole>();
    for (UserRole aRole : newRoles)
    {
      if (verifiedRoles.contains(aRole))
      {
        continue;
      }
      verifiedRoles.add(aRole);
    }

    if (verifiedRoles.size() != newRoles.length || verifiedRoles.size() < minimumNumberOfRoles() || verifiedRoles.size() > maximumNumberOfRoles())
    {
      return wasSet;
    }

    ArrayList<UserRole> oldRoles = new ArrayList<UserRole>(roles);
    roles.clear();
    for (UserRole aNewRole : verifiedRoles)
    {
      roles.add(aNewRole);
      if (oldRoles.contains(aNewRole))
      {
        oldRoles.remove(aNewRole);
      }
      else
      {
        aNewRole.addUser(this);
      }
    }

    for (UserRole anOldRole : oldRoles)
    {
      anOldRole.removeUser(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoleAt(UserRole aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(UserRole aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCoolSupplies(CoolSupplies aCoolSupplies)
  {
    boolean wasSet = false;
    if (aCoolSupplies == null)
    {
      return wasSet;
    }

    CoolSupplies existingCoolSupplies = coolSupplies;
    coolSupplies = aCoolSupplies;
    if (existingCoolSupplies != null && !existingCoolSupplies.equals(aCoolSupplies))
    {
      existingCoolSupplies.removeUser(this);
    }
    coolSupplies.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLogins()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LoginSession addLogin(Date aLoginDate, Time aStartLogin, Time aEndLogin, UserAccount aUserAccount)
  {
    return new LoginSession(aLoginDate, aStartLogin, aEndLogin, this, aUserAccount);
  }

  public boolean addLogin(LoginSession aLogin)
  {
    boolean wasAdded = false;
    if (logins.contains(aLogin)) { return false; }
    User existingUser = aLogin.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aLogin.setUser(this);
    }
    else
    {
      logins.add(aLogin);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLogin(LoginSession aLogin)
  {
    boolean wasRemoved = false;
    //Unable to remove aLogin, as it must always have a user
    if (!this.equals(aLogin.getUser()))
    {
      logins.remove(aLogin);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLoginAt(LoginSession aLogin, int index)
  {  
    boolean wasAdded = false;
    if(addLogin(aLogin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLogins()) { index = numberOfLogins() - 1; }
      logins.remove(aLogin);
      logins.add(index, aLogin);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLoginAt(LoginSession aLogin, int index)
  {
    boolean wasAdded = false;
    if(logins.contains(aLogin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLogins()) { index = numberOfLogins() - 1; }
      logins.remove(aLogin);
      logins.add(index, aLogin);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLoginAt(aLogin, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<UserRole> copyOfRoles = new ArrayList<UserRole>(roles);
    roles.clear();
    for(UserRole aRole : copyOfRoles)
    {
      aRole.removeUser(this);
    }
    CoolSupplies placeholderCoolSupplies = coolSupplies;
    this.coolSupplies = null;
    if(placeholderCoolSupplies != null)
    {
      placeholderCoolSupplies.removeUser(this);
    }
    for(int i=logins.size(); i > 0; i--)
    {
      LoginSession aLogin = logins.get(i - 1);
      aLogin.delete();
    }
  }

}