/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 28 "uml.ump"
public class Parent extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Parent Associations
  private List<Order> orders;
  private Registration registration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Parent()
  {
    super();
    orders = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(Date aOrderDate, Date aDueDate, int aPenalty, Order.Status aStatus, CoolSupplies aCoolSupplies)
  {
    return new Order(aOrderDate, aDueDate, aPenalty, aStatus, this, aCoolSupplies);
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
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 30 uml.ump
  1 -- * Student students
  
}