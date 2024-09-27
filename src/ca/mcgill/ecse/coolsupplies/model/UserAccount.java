/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 38 "../../../../../CoolSupplies.ump"
public abstract class UserAccount
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, UserAccount> useraccountsByEmail = new HashMap<String, UserAccount>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserAccount Attributes
  private String email;
  private String password;

  //UserAccount Associations
  private List<LoginSession> logins;

  //Helper Variables
  private boolean canSetEmail;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserAccount(String aEmail, String aPassword)
  {
    canSetEmail = true;
    password = aPassword;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    logins = new ArrayList<LoginSession>();
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
      useraccountsByEmail.remove(anOldEmail);
    }
    useraccountsByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static UserAccount getWithEmail(String aEmail)
  {
    return useraccountsByEmail.get(aEmail);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLogins()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public LoginSession addLogin(Date aLoginDate, Time aStartLogin, Time aEndLogin, User aUser)
  {
    return new LoginSession(aLoginDate, aStartLogin, aEndLogin, aUser, this);
  }

  public boolean addLogin(LoginSession aLogin)
  {
    boolean wasAdded = false;
    if (logins.contains(aLogin)) { return false; }
    UserAccount existingUserAccount = aLogin.getUserAccount();
    boolean isNewUserAccount = existingUserAccount != null && !this.equals(existingUserAccount);
    if (isNewUserAccount)
    {
      aLogin.setUserAccount(this);
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
    //Unable to remove aLogin, as it must always have a userAccount
    if (!this.equals(aLogin.getUserAccount()))
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
    useraccountsByEmail.remove(getEmail());
    for(int i=logins.size(); i > 0; i--)
    {
      LoginSession aLogin = logins.get(i - 1);
      aLogin.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]";
  }
}