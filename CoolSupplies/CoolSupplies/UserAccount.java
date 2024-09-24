/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 38 "uml.ump"
public abstract class UserAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserAccount Associations
  private List<LoginSession> logins;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserAccount()
  {
    logins = new ArrayList<LoginSession>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
    for(int i=logins.size(); i > 0; i--)
    {
      LoginSession aLogin = logins.get(i - 1);
      aLogin.delete();
    }
  }

}