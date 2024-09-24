/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.sql.Date;
import java.sql.Time;

// line 57 "uml.ump"
public class LoginSession
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LoginSession Attributes
  private Date loginDate;
  private Time startLogin;
  private Time endLogin;

  //LoginSession Associations
  private User user;
  private UserAccount userAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoginSession(Date aLoginDate, Time aStartLogin, Time aEndLogin, User aUser, UserAccount aUserAccount)
  {
    loginDate = aLoginDate;
    startLogin = aStartLogin;
    endLogin = aEndLogin;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create login due to user. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddUserAccount = setUserAccount(aUserAccount);
    if (!didAddUserAccount)
    {
      throw new RuntimeException("Unable to create login due to userAccount. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLoginDate(Date aLoginDate)
  {
    boolean wasSet = false;
    loginDate = aLoginDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartLogin(Time aStartLogin)
  {
    boolean wasSet = false;
    startLogin = aStartLogin;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndLogin(Time aEndLogin)
  {
    boolean wasSet = false;
    endLogin = aEndLogin;
    wasSet = true;
    return wasSet;
  }

  public Date getLoginDate()
  {
    return loginDate;
  }

  public Time getStartLogin()
  {
    return startLogin;
  }

  public Time getEndLogin()
  {
    return endLogin;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public UserAccount getUserAccount()
  {
    return userAccount;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeLogin(this);
    }
    user.addLogin(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUserAccount(UserAccount aUserAccount)
  {
    boolean wasSet = false;
    if (aUserAccount == null)
    {
      return wasSet;
    }

    UserAccount existingUserAccount = userAccount;
    userAccount = aUserAccount;
    if (existingUserAccount != null && !existingUserAccount.equals(aUserAccount))
    {
      existingUserAccount.removeLogin(this);
    }
    userAccount.addLogin(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeLogin(this);
    }
    UserAccount placeholderUserAccount = userAccount;
    this.userAccount = null;
    if(placeholderUserAccount != null)
    {
      placeholderUserAccount.removeLogin(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "loginDate" + "=" + (getLoginDate() != null ? !getLoginDate().equals(this)  ? getLoginDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "startLogin" + "=" + (getStartLogin() != null ? !getStartLogin().equals(this)  ? getStartLogin().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endLogin" + "=" + (getEndLogin() != null ? !getEndLogin().equals(this)  ? getEndLogin().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "userAccount = "+(getUserAccount()!=null?Integer.toHexString(System.identityHashCode(getUserAccount())):"null");
  }
}