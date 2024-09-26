/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;
import java.sql.Date;

// line 28 "../../../../../uml.ump"
public class Parent extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Parent Associations
  private List<Student> students;
  private List<Order> orders;
  private Registration registration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Parent()
  {
    super();
    students = new ArrayList<Student>();
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Student getStudent(int index)
  {
    Student aStudent = students.get(index);
    return aStudent;
  }

  public List<Student> getStudents()
  {
    List<Student> newStudents = Collections.unmodifiableList(students);
    return newStudents;
  }

  public int numberOfStudents()
  {
    int number = students.size();
    return number;
  }

  public boolean hasStudents()
  {
    boolean has = students.size() > 0;
    return has;
  }

  public int indexOfStudent(Student aStudent)
  {
    int index = students.indexOf(aStudent);
    return index;
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
  /* Code from template association_GetOne */
  public Registration getRegistration()
  {
    return registration;
  }

  public boolean hasRegistration()
  {
    boolean has = registration != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfStudents()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Student addStudent(String aName, int aId, School aSchool, Grade aGrade)
  {
    return new Student(aName, aId, aSchool, this, aGrade);
  }

  public boolean addStudent(Student aStudent)
  {
    boolean wasAdded = false;
    if (students.contains(aStudent)) { return false; }
    Parent existingParent = aStudent.getParent();
    boolean isNewParent = existingParent != null && !this.equals(existingParent);
    if (isNewParent)
    {
      aStudent.setParent(this);
    }
    else
    {
      students.add(aStudent);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeStudent(Student aStudent)
  {
    boolean wasRemoved = false;
    //Unable to remove aStudent, as it must always have a parent
    if (!this.equals(aStudent.getParent()))
    {
      students.remove(aStudent);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addStudentAt(Student aStudent, int index)
  {  
    boolean wasAdded = false;
    if(addStudent(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStudentAt(Student aStudent, int index)
  {
    boolean wasAdded = false;
    if(students.contains(aStudent))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStudents()) { index = numberOfStudents() - 1; }
      students.remove(aStudent);
      students.add(index, aStudent);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStudentAt(aStudent, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(Autounique aOrderNumber, Date aOrderDate, Date aDueDate, double aTotalAmount, double aPenalty, boolean aIsPickedUp, CoolSupplies aCoolSupplies)
  {
    return new Order(aOrderNumber, aOrderDate, aDueDate, aTotalAmount, aPenalty, aIsPickedUp, this, aCoolSupplies);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Parent existingParent = aOrder.getParent();
    boolean isNewParent = existingParent != null && !this.equals(existingParent);
    if (isNewParent)
    {
      aOrder.setParent(this);
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
    //Unable to remove aOrder, as it must always have a parent
    if (!this.equals(aOrder.getParent()))
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
  /* Code from template association_SetOptionalOneToOne */
  public boolean setRegistration(Registration aNewRegistration)
  {
    boolean wasSet = false;
    if (registration != null && !registration.equals(aNewRegistration) && equals(registration.getParent()))
    {
      //Unable to setRegistration, as existing registration would become an orphan
      return wasSet;
    }

    registration = aNewRegistration;
    Parent anOldParent = aNewRegistration != null ? aNewRegistration.getParent() : null;

    if (!this.equals(anOldParent))
    {
      if (anOldParent != null)
      {
        anOldParent.registration = null;
      }
      if (registration != null)
      {
        registration.setParent(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=students.size(); i > 0; i--)
    {
      Student aStudent = students.get(i - 1);
      aStudent.delete();
    }
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    Registration existingRegistration = registration;
    registration = null;
    if (existingRegistration != null)
    {
      existingRegistration.delete();
    }
    super.delete();
  }

}