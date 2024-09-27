/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 94 "../../../../../CoolSupplies.ump"
public class Item
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum ItemType { Mandatory, Recommended, Optional, NotApplicable }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private double price;
  private ItemType type;

  //Item Associations
  private Bundle bundle;
  private SchoolSupply schoolSupply;
  private List<OrderItem> orderItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, double aPrice, ItemType aType, Bundle aBundle, SchoolSupply aSchoolSupply)
  {
    name = aName;
    price = aPrice;
    type = aType;
    boolean didAddBundle = setBundle(aBundle);
    if (!didAddBundle)
    {
      throw new RuntimeException("Unable to create item due to bundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSchoolSupply = setSchoolSupply(aSchoolSupply);
    if (!didAddSchoolSupply)
    {
      throw new RuntimeException("Unable to create item due to schoolSupply. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    orderItems = new ArrayList<OrderItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(ItemType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }

  public ItemType getType()
  {
    return type;
  }
  /* Code from template association_GetOne */
  public Bundle getBundle()
  {
    return bundle;
  }
  /* Code from template association_GetOne */
  public SchoolSupply getSchoolSupply()
  {
    return schoolSupply;
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
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setBundle(Bundle aBundle)
  {
    boolean wasSet = false;
    //Must provide bundle to item
    if (aBundle == null)
    {
      return wasSet;
    }

    if (bundle != null && bundle.numberOfItems() <= Bundle.minimumNumberOfItems())
    {
      return wasSet;
    }

    Bundle existingBundle = bundle;
    bundle = aBundle;
    if (existingBundle != null && !existingBundle.equals(aBundle))
    {
      boolean didRemove = existingBundle.removeItem(this);
      if (!didRemove)
      {
        bundle = existingBundle;
        return wasSet;
      }
    }
    bundle.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSchoolSupply(SchoolSupply aSchoolSupply)
  {
    boolean wasSet = false;
    if (aSchoolSupply == null)
    {
      return wasSet;
    }

    SchoolSupply existingSchoolSupply = schoolSupply;
    schoolSupply = aSchoolSupply;
    if (existingSchoolSupply != null && !existingSchoolSupply.equals(aSchoolSupply))
    {
      existingSchoolSupply.removeItem(this);
    }
    schoolSupply.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderItem addOrderItem(int aRepetition, Order aOrder)
  {
    return new OrderItem(aRepetition, aOrder, this);
  }

  public boolean addOrderItem(OrderItem aOrderItem)
  {
    boolean wasAdded = false;
    if (orderItems.contains(aOrderItem)) { return false; }
    Item existingItem = aOrderItem.getItem();
    boolean isNewItem = existingItem != null && !this.equals(existingItem);
    if (isNewItem)
    {
      aOrderItem.setItem(this);
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
    //Unable to remove aOrderItem, as it must always have a item
    if (!this.equals(aOrderItem.getItem()))
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
    Bundle placeholderBundle = bundle;
    this.bundle = null;
    if(placeholderBundle != null)
    {
      placeholderBundle.removeItem(this);
    }
    SchoolSupply placeholderSchoolSupply = schoolSupply;
    this.schoolSupply = null;
    if(placeholderSchoolSupply != null)
    {
      placeholderSchoolSupply.removeItem(this);
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
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "bundle = "+(getBundle()!=null?Integer.toHexString(System.identityHashCode(getBundle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "schoolSupply = "+(getSchoolSupply()!=null?Integer.toHexString(System.identityHashCode(getSchoolSupply())):"null");
  }
}