/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 1 "uml.ump"
public class CoolSupplies
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CoolSupplies Attributes
  private int schoolYear;
  private Date startSchoolYear;
  private Date endSchoolYear;

  //CoolSupplies Associations
  private List<User> users;
  private SchoolSupply schoolSupply;
  private List<School> schools;
  private List<Order> orders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CoolSupplies(int aSchoolYear, Date aStartSchoolYear, Date aEndSchoolYear, SchoolSupply aSchoolSupply)
  {
    schoolYear = aSchoolYear;
    startSchoolYear = aStartSchoolYear;
    endSchoolYear = aEndSchoolYear;
    users = new ArrayList<User>();
    if (aSchoolSupply == null || aSchoolSupply.getCoolSupplies() != null)
    {
      throw new RuntimeException("Unable to create CoolSupplies due to aSchoolSupply. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    schoolSupply = aSchoolSupply;
    schools = new ArrayList<School>();
    orders = new ArrayList<Order>();
  }

  public CoolSupplies(int aSchoolYear, Date aStartSchoolYear, Date aEndSchoolYear)
  {
    schoolYear = aSchoolYear;
    startSchoolYear = aStartSchoolYear;
    endSchoolYear = aEndSchoolYear;
    users = new ArrayList<User>();
    schoolSupply = new SchoolSupply(this);
    schools = new ArrayList<School>();
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSchoolYear(int aSchoolYear)
  {
    boolean wasSet = false;
    schoolYear = aSchoolYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartSchoolYear(Date aStartSchoolYear)
  {
    boolean wasSet = false;
    startSchoolYear = aStartSchoolYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndSchoolYear(Date aEndSchoolYear)
  {
    boolean wasSet = false;
    endSchoolYear = aEndSchoolYear;
    wasSet = true;
    return wasSet;
  }

  public int getSchoolYear()
  {
    return schoolYear;
  }

  public Date getStartSchoolYear()
  {
    return startSchoolYear;
  }

  public Date getEndSchoolYear()
  {
    return endSchoolYear;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  /**
   * Added top-level compositions
   */
  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetOne */
  public SchoolSupply getSchoolSupply()
  {
    return schoolSupply;
  }
  /* Code from template association_GetMany */
  public School getSchool(int index)
  {
    School aSchool = schools.get(index);
    return aSchool;
  }

  public List<School> getSchools()
  {
    List<School> newSchools = Collections.unmodifiableList(schools);
    return newSchools;
  }

  public int numberOfSchools()
  {
    int number = schools.size();
    return number;
  }

  public boolean hasSchools()
  {
    boolean has = schools.size() > 0;
    return has;
  }

  public int indexOfSchool(School aSchool)
  {
    int index = schools.indexOf(aSchool);
    return index;
  }
  /* Code from template association_GetMany_clear */
  protected void clear_schools()
  {
    schools.clear();
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_GetMany_relatedSpecialization */
  public School getSchool_School(int index)
  {
    School aSchool = (School)schools.get(index);
    return aSchool;
  }

  /* required for Java 7. */
  @SuppressWarnings("unchecked")
  public List<School> getSchools_School()
  {
    List<? extends School> newSchools = Collections.unmodifiableList(schools);
    return (List<School>)newSchools;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(UserRole... allRoles)
  {
    return new User(this, allRoles);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    CoolSupplies existingCoolSupplies = aUser.getCoolSupplies();
    boolean isNewCoolSupplies = existingCoolSupplies != null && !this.equals(existingCoolSupplies);
    if (isNewCoolSupplies)
    {
      aUser.setCoolSupplies(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a coolSupplies
    if (!this.equals(aUser.getCoolSupplies()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSchools()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public School addSchool(String aName)
  {
    return new School(aName, this);
  }

  public boolean addSchool(School aSchool)
  {
    boolean wasAdded = false;
    if (schools.contains(aSchool)) { return false; }
    if (schools.contains(aSchool)) { return false; }
    CoolSupplies existingCoolSupplies = aSchool.getCoolSupplies();
    boolean isNewCoolSupplies = existingCoolSupplies != null && !this.equals(existingCoolSupplies);
    if (isNewCoolSupplies)
    {
      aSchool.setCoolSupplies(this);
    }
    else
    {
      schools.add(aSchool);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSchool(School aSchool)
  {
    boolean wasRemoved = false;
    //Unable to remove aSchool, as it must always have a coolSupplies
    if (!this.equals(aSchool.getCoolSupplies()))
    {
      schools.remove(aSchool);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSchoolAt(School aSchool, int index)
  {  
    boolean wasAdded = false;
    if(addSchool(aSchool))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchools()) { index = numberOfSchools() - 1; }
      schools.remove(aSchool);
      schools.add(index, aSchool);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSchoolAt(School aSchool, int index)
  {
    boolean wasAdded = false;
    if(schools.contains(aSchool))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchools()) { index = numberOfSchools() - 1; }
      schools.remove(aSchool);
      schools.add(index, aSchool);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSchoolAt(aSchool, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(Date aOrderDate, Date aDueDate, int aPenalty, Order.Status aStatus, Parent aParent)
  {
    return new Order(aOrderDate, aDueDate, aPenalty, aStatus, aParent, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    CoolSupplies existingCoolSupplies = aOrder.getCoolSupplies();
    boolean isNewCoolSupplies = existingCoolSupplies != null && !this.equals(existingCoolSupplies);
    if (isNewCoolSupplies)
    {
      aOrder.setCoolSupplies(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a coolSupplies
    if (!this.equals(aOrder.getCoolSupplies()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }
  /* Code from template association_set_specialization_reqCommonCode */  /* Code from template association_MinimumNumberOfMethod_relatedSpecialization */
  public static int minimumNumberOfSchools_School()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne_relatedSpecialization */
  public School addSchool(String aName)
  {
    return new School(aName, this);
  }

  public boolean addSchool(School aSchool)
  {
    boolean wasAdded = false;
    if (schools.contains(aSchool)) { return false; }
    if (schools.contains(aSchool)) { return false; }
    CoolSupplies existingCoolSupplies = aSchool.getCoolSupplies();
    boolean isNewCoolSupplies = existingCoolSupplies != null && !this.equals(existingCoolSupplies);
    if (isNewCoolSupplies)
    {
      aSchool.setCoolSupplies(this);
    }
    else
    {
      schools.add(aSchool);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSchool(School aSchool)
  {
    boolean wasRemoved = false;
    //Unable to remove aSchool, as it must always have a coolSupplies
    if (!this.equals(aSchool.getCoolSupplies()))
    {
      schools.remove(aSchool);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions_relatedSpecialization */
  public boolean addSchoolAt(School aSchool, int index)
  {  
    boolean wasAdded = false;
    if(addSchool(aSchool))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchools()) { index = numberOfSchools() - 1; }
      schools.remove(aSchool);
      schools.add(index, aSchool);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSchoolAt(School aSchool, int index)
  {
    boolean wasAdded = false;
    if(schools.contains(aSchool))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchools()) { index = numberOfSchools() - 1; }
      schools.remove(aSchool);
      schools.add(index, aSchool);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSchoolAt(aSchool, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    SchoolSupply existingSchoolSupply = schoolSupply;
    schoolSupply = null;
    if (existingSchoolSupply != null)
    {
      existingSchoolSupply.delete();
    }
    while (schools.size() > 0)
    {
      School aSchool = schools.get(schools.size() - 1);
      aSchool.delete();
      schools.remove(aSchool);
    }
    
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "schoolYear" + ":" + getSchoolYear()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startSchoolYear" + "=" + (getStartSchoolYear() != null ? !getStartSchoolYear().equals(this)  ? getStartSchoolYear().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endSchoolYear" + "=" + (getEndSchoolYear() != null ? !getEndSchoolYear().equals(this)  ? getEndSchoolYear().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "schoolSupply = "+(getSchoolSupply()!=null?Integer.toHexString(System.identityHashCode(getSchoolSupply())):"null");
  }
}