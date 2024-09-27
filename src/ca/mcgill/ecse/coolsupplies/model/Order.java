/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.sql.Date;
import java.util.*;

/**
 * changed from associationClass to class
 */
// line 109 "../../../../../CoolSupplies.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Autounique orderNumber;
  private Date orderDate;
  private Date dueDate;
  private double totalAmount;
  private double penalty;
  private boolean isPickedUp;

  //Order Associations
  private Parent parent;
  private List<Bundle> bundles;
  private CoolSupplies coolSupplies;
  private List<Payment> payments;
  private List<OrderBundle> orderBundles;
  private List<OrderItem> orderItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Autounique aOrderNumber, Date aOrderDate, Date aDueDate, double aTotalAmount, double aPenalty, boolean aIsPickedUp, Parent aParent, CoolSupplies aCoolSupplies)
  {
    orderNumber = aOrderNumber;
    orderDate = aOrderDate;
    dueDate = aDueDate;
    totalAmount = aTotalAmount;
    penalty = aPenalty;
    isPickedUp = aIsPickedUp;
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create order due to parent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bundles = new ArrayList<Bundle>();
    boolean didAddCoolSupplies = setCoolSupplies(aCoolSupplies);
    if (!didAddCoolSupplies)
    {
      throw new RuntimeException("Unable to create order due to coolSupplies. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    payments = new ArrayList<Payment>();
    orderBundles = new ArrayList<OrderBundle>();
    orderItems = new ArrayList<OrderItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderNumber(Autounique aOrderNumber)
  {
    boolean wasSet = false;
    orderNumber = aOrderNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setDueDate(Date aDueDate)
  {
    boolean wasSet = false;
    dueDate = aDueDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalAmount(double aTotalAmount)
  {
    boolean wasSet = false;
    totalAmount = aTotalAmount;
    wasSet = true;
    return wasSet;
  }

  public boolean setPenalty(double aPenalty)
  {
    boolean wasSet = false;
    penalty = aPenalty;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsPickedUp(boolean aIsPickedUp)
  {
    boolean wasSet = false;
    isPickedUp = aIsPickedUp;
    wasSet = true;
    return wasSet;
  }

  public Autounique getOrderNumber()
  {
    return orderNumber;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public Date getDueDate()
  {
    return dueDate;
  }

  public double getTotalAmount()
  {
    return totalAmount;
  }

  public double getPenalty()
  {
    return penalty;
  }

  public boolean getIsPickedUp()
  {
    return isPickedUp;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsPickedUp()
  {
    return isPickedUp;
  }
  /* Code from template association_GetOne */
  public Parent getParent()
  {
    return parent;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
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
  /* Code from template association_GetMany */
  public OrderBundle getOrderBundle(int index)
  {
    OrderBundle aOrderBundle = orderBundles.get(index);
    return aOrderBundle;
  }

  public List<OrderBundle> getOrderBundles()
  {
    List<OrderBundle> newOrderBundles = Collections.unmodifiableList(orderBundles);
    return newOrderBundles;
  }

  public int numberOfOrderBundles()
  {
    int number = orderBundles.size();
    return number;
  }

  public boolean hasOrderBundles()
  {
    boolean has = orderBundles.size() > 0;
    return has;
  }

  public int indexOfOrderBundle(OrderBundle aOrderBundle)
  {
    int index = orderBundles.indexOf(aOrderBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public OrderItem getOrderItem(int index)
  {
    OrderItem aOrderItem = orderItems.get(index);
    return aOrderItem;
  }

  public List<OrderItem> getOrderItems()
  {
    List<OrderItem> newOrderItems = Collections.unmodifiableList(orderItems);
    return newOrderItems;
  }

  public int numberOfOrderItems()
  {
    int number = orderItems.size();
    return number;
  }

  public boolean hasOrderItems()
  {
    boolean has = orderItems.size() > 0;
    return has;
  }

  public int indexOfOrderItem(OrderItem aOrderItem)
  {
    int index = orderItems.indexOf(aOrderItem);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addBundle(int aNumberOfItems)
  {
    return new Bundle(aNumberOfItems, this);
  }

  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    Order existingOrder = aBundle.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aBundle.setOrder(this);
    }
    else
    {
      bundles.add(aBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundle, as it must always have a order
    if (!this.equals(aBundle.getOrder()))
    {
      bundles.remove(aBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
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
  public Payment addPayment(String aAuthorizationCode, Date aPaymentDate)
  {
    if (numberOfPayments() >= maximumNumberOfPayments())
    {
      return null;
    }
    else
    {
      return new Payment(aAuthorizationCode, aPaymentDate, this);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderBundle addOrderBundle(int aRepetition, Bundle aBundle)
  {
    return new OrderBundle(aRepetition, this, aBundle);
  }

  public boolean addOrderBundle(OrderBundle aOrderBundle)
  {
    boolean wasAdded = false;
    if (orderBundles.contains(aOrderBundle)) { return false; }
    Order existingOrder = aOrderBundle.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aOrderBundle.setOrder(this);
    }
    else
    {
      orderBundles.add(aOrderBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderBundle(OrderBundle aOrderBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderBundle, as it must always have a order
    if (!this.equals(aOrderBundle.getOrder()))
    {
      orderBundles.remove(aOrderBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderBundleAt(OrderBundle aOrderBundle, int index)
  {  
    boolean wasAdded = false;
    if(addOrderBundle(aOrderBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderBundles()) { index = numberOfOrderBundles() - 1; }
      orderBundles.remove(aOrderBundle);
      orderBundles.add(index, aOrderBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderBundleAt(OrderBundle aOrderBundle, int index)
  {
    boolean wasAdded = false;
    if(orderBundles.contains(aOrderBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderBundles()) { index = numberOfOrderBundles() - 1; }
      orderBundles.remove(aOrderBundle);
      orderBundles.add(index, aOrderBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderBundleAt(aOrderBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderItem addOrderItem(int aRepetition, Item aItem)
  {
    return new OrderItem(aRepetition, this, aItem);
  }

  public boolean addOrderItem(OrderItem aOrderItem)
  {
    boolean wasAdded = false;
    if (orderItems.contains(aOrderItem)) { return false; }
    Order existingOrder = aOrderItem.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aOrderItem.setOrder(this);
    }
    else
    {
      orderItems.add(aOrderItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderItem(OrderItem aOrderItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderItem, as it must always have a order
    if (!this.equals(aOrderItem.getOrder()))
    {
      orderItems.remove(aOrderItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderItemAt(OrderItem aOrderItem, int index)
  {  
    boolean wasAdded = false;
    if(addOrderItem(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItems()) { index = numberOfOrderItems() - 1; }
      orderItems.remove(aOrderItem);
      orderItems.add(index, aOrderItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderItemAt(OrderItem aOrderItem, int index)
  {
    boolean wasAdded = false;
    if(orderItems.contains(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItems()) { index = numberOfOrderItems() - 1; }
      orderItems.remove(aOrderItem);
      orderItems.add(index, aOrderItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderItemAt(aOrderItem, index);
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
    for(int i=bundles.size(); i > 0; i--)
    {
      Bundle aBundle = bundles.get(i - 1);
      aBundle.delete();
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
    for(int i=orderBundles.size(); i > 0; i--)
    {
      OrderBundle aOrderBundle = orderBundles.get(i - 1);
      aOrderBundle.delete();
    }
    for(int i=orderItems.size(); i > 0; i--)
    {
      OrderItem aOrderItem = orderItems.get(i - 1);
      aOrderItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalAmount" + ":" + getTotalAmount()+ "," +
            "penalty" + ":" + getPenalty()+ "," +
            "isPickedUp" + ":" + getIsPickedUp()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderNumber" + "=" + (getOrderNumber() != null ? !getOrderNumber().equals(this)  ? getOrderNumber().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dueDate" + "=" + (getDueDate() != null ? !getDueDate().equals(this)  ? getDueDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parent = "+(getParent()!=null?Integer.toHexString(System.identityHashCode(getParent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "coolSupplies = "+(getCoolSupplies()!=null?Integer.toHexString(System.identityHashCode(getCoolSupplies())):"null");
  }
}