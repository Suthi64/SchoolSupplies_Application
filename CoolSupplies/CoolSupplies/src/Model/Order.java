/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 113 "uml.ump"
public class Order
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { Confirmed, Picked-up, Cancelled }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Date orderDate;
  private Date dueDate;
  private int penalty;
  private Status status;

  //Order Associations
  private Parent parent;
  private CoolSupplies coolSupplies;
  private List<Payment> payments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Date aOrderDate, Date aDueDate, int aPenalty, Status aStatus, Parent aParent, CoolSupplies aCoolSupplies)
  {
    orderDate = aOrderDate;
    dueDate = aDueDate;
    penalty = aPenalty;
    status = aStatus;
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create order due to parent. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCoolSupplies = setCoolSupplies(aCoolSupplies);
    if (!didAddCoolSupplies)
    {
      throw new RuntimeException("Unable to create order due to coolSupplies. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    payments = new ArrayList<Payment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDueDate(Date aDueDate)
  {
    boolean wasSet = false;
    dueDate = aDueDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPenalty(int aPenalty)
  {
    boolean wasSet = false;
    penalty = aPenalty;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public Date getDueDate()
  {
    return dueDate;
  }

  public int getPenalty()
  {
    return penalty;
  }

  public Status getStatus()
  {
    return status;
  }
  /* Code from template association_GetOne */
  public Parent getParent()
  {
    return parent;
  }
  /* Code from template association_GetOne */
  public CoolSupplies getCoolSupplies()
  {
    return coolSupplies;
  }
  /* Code from template association_GetMany */
  public Payment getPayment(int index)
  {
    Payment aPayment = payments.get(index);
    return aPayment;
  }

  public List<Payment> getPayments()
  {
    List<Payment> newPayments = Collections.unmodifiableList(payments);
    return newPayments;
  }

  public int numberOfPayments()
  {
    int number = payments.size();
    return number;
  }

  public boolean hasPayments()
  {
    boolean has = payments.size() > 0;
    return has;
  }

  public int indexOfPayment(Payment aPayment)
  {
    int index = payments.indexOf(aPayment);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setParent(Parent aParent)
  {
    boolean wasSet = false;
    if (aParent == null)
    {
      return wasSet;
    }

    Parent existingParent = parent;
    parent = aParent;
    if (existingParent != null && !existingParent.equals(aParent))
    {
      existingParent.removeOrder(this);
    }
    parent.addOrder(this);
    wasSet = true;
    return wasSet;
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
      existingCoolSupplies.removeOrder(this);
    }
    coolSupplies.addOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPayments()
  {
    return 0;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPayments()
  {
    return 2;
  }
  /* Code from template association_AddOptionalNToOne */
  public Payment addPayment(String aAuthorizationCode, Date aDate)
  {
    if (numberOfPayments() >= maximumNumberOfPayments())
    {
      return null;
    }
    else
    {
      return new Payment(aAuthorizationCode, aDate, this);
    }
  }

  public boolean addPayment(Payment aPayment)
  {
    boolean wasAdded = false;
    if (payments.contains(aPayment)) { return false; }
    if (numberOfPayments() >= maximumNumberOfPayments())
    {
      return wasAdded;
    }

    Order existingOrder = aPayment.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aPayment.setOrder(this);
    }
    else
    {
      payments.add(aPayment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePayment(Payment aPayment)
  {
    boolean wasRemoved = false;
    //Unable to remove aPayment, as it must always have a order
    if (!this.equals(aPayment.getOrder()))
    {
      payments.remove(aPayment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPaymentAt(Payment aPayment, int index)
  {  
    boolean wasAdded = false;
    if(addPayment(aPayment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPayments()) { index = numberOfPayments() - 1; }
      payments.remove(aPayment);
      payments.add(index, aPayment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePaymentAt(Payment aPayment, int index)
  {
    boolean wasAdded = false;
    if(payments.contains(aPayment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPayments()) { index = numberOfPayments() - 1; }
      payments.remove(aPayment);
      payments.add(index, aPayment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPaymentAt(aPayment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Parent placeholderParent = parent;
    this.parent = null;
    if(placeholderParent != null)
    {
      placeholderParent.removeOrder(this);
    }
    CoolSupplies placeholderCoolSupplies = coolSupplies;
    this.coolSupplies = null;
    if(placeholderCoolSupplies != null)
    {
      placeholderCoolSupplies.removeOrder(this);
    }
    for(int i=payments.size(); i > 0; i--)
    {
      Payment aPayment = payments.get(i - 1);
      aPayment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "penalty" + ":" + getPenalty()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dueDate" + "=" + (getDueDate() != null ? !getDueDate().equals(this)  ? getDueDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parent = "+(getParent()!=null?Integer.toHexString(System.identityHashCode(getParent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "coolSupplies = "+(getCoolSupplies()!=null?Integer.toHexString(System.identityHashCode(getCoolSupplies())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 117 "uml.ump"
  immutable autounique Integer orderNumber ;

  
}