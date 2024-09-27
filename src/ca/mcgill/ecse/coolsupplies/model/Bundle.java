/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.util.*;

// line 85 "../../../../../CoolSupplies.ump"
public class Bundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bundle Attributes
  private int numberOfItems;
  private double discount;

  //Bundle Associations
  private List<Grade> grades;
  private List<Item> items;
  private Order order;
  private List<OrderBundle> orderBundles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(int aNumberOfItems, Order aOrder)
  {
    numberOfItems = aNumberOfItems;
    discount = 0;
    grades = new ArrayList<Grade>();
    items = new ArrayList<Item>();
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create bundle due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    orderBundles = new ArrayList<OrderBundle>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfItems(int aNumberOfItems)
  {
    boolean wasSet = false;
    numberOfItems = aNumberOfItems;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiscount(double aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfItems()
  {
    return numberOfItems;
  }

  public double getDiscount()
  {
    return discount;
  }
  /* Code from template association_GetMany */
  public Grade getGrade(int index)
  {
    Grade aGrade = grades.get(index);
    return aGrade;
  }

  public List<Grade> getGrades()
  {
    List<Grade> newGrades = Collections.unmodifiableList(grades);
    return newGrades;
  }

  public int numberOfGrades()
  {
    int number = grades.size();
    return number;
  }

  public boolean hasGrades()
  {
    boolean has = grades.size() > 0;
    return has;
  }

  public int indexOfGrade(Grade aGrade)
  {
    int index = grades.indexOf(aGrade);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGrades()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Grade addGrade(String aName, School aSchool)
  {
    return new Grade(aName, aSchool, this);
  }

  public boolean addGrade(Grade aGrade)
  {
    boolean wasAdded = false;
    if (grades.contains(aGrade)) { return false; }
    Bundle existingBundle = aGrade.getBundle();
    boolean isNewBundle = existingBundle != null && !this.equals(existingBundle);
    if (isNewBundle)
    {
      aGrade.setBundle(this);
    }
    else
    {
      grades.add(aGrade);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGrade(Grade aGrade)
  {
    boolean wasRemoved = false;
    //Unable to remove aGrade, as it must always have a bundle
    if (!this.equals(aGrade.getBundle()))
    {
      grades.remove(aGrade);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGradeAt(Grade aGrade, int index)
  {  
    boolean wasAdded = false;
    if(addGrade(aGrade))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrades()) { index = numberOfGrades() - 1; }
      grades.remove(aGrade);
      grades.add(index, aGrade);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGradeAt(Grade aGrade, int index)
  {
    boolean wasAdded = false;
    if(grades.contains(aGrade))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrades()) { index = numberOfGrades() - 1; }
      grades.remove(aGrade);
      grades.add(index, aGrade);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGradeAt(aGrade, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfItemsValid()
  {
    boolean isValid = numberOfItems() >= minimumNumberOfItems();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Item addItem(String aName, double aPrice, Item.ItemType aType, SchoolSupply aSchoolSupply)
  {
    Item aNewItem = new Item(aName, aPrice, aType, this, aSchoolSupply);
    return aNewItem;
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    Bundle existingBundle = aItem.getBundle();
    boolean isNewBundle = existingBundle != null && !this.equals(existingBundle);

    if (isNewBundle && existingBundle.numberOfItems() <= minimumNumberOfItems())
    {
      return wasAdded;
    }
    if (isNewBundle)
    {
      aItem.setBundle(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a bundle
    if (this.equals(aItem.getBundle()))
    {
      return wasRemoved;
    }

    //bundle already at minimum (1)
    if (numberOfItems() <= minimumNumberOfItems())
    {
      return wasRemoved;
    }

    items.remove(aItem);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeBundle(this);
    }
    order.addBundle(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderBundle addOrderBundle(int aRepetition, Order aOrder)
  {
    return new OrderBundle(aRepetition, aOrder, this);
  }

  public boolean addOrderBundle(OrderBundle aOrderBundle)
  {
    boolean wasAdded = false;
    if (orderBundles.contains(aOrderBundle)) { return false; }
    Bundle existingBundle = aOrderBundle.getBundle();
    boolean isNewBundle = existingBundle != null && !this.equals(existingBundle);
    if (isNewBundle)
    {
      aOrderBundle.setBundle(this);
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
    //Unable to remove aOrderBundle, as it must always have a bundle
    if (!this.equals(aOrderBundle.getBundle()))
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

  public void delete()
  {
    for(int i=grades.size(); i > 0; i--)
    {
      Grade aGrade = grades.get(i - 1);
      aGrade.delete();
    }
    for(int i=items.size(); i > 0; i--)
    {
      Item aItem = items.get(i - 1);
      aItem.delete();
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeBundle(this);
    }
    for(int i=orderBundles.size(); i > 0; i--)
    {
      OrderBundle aOrderBundle = orderBundles.get(i - 1);
      aOrderBundle.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfItems" + ":" + getNumberOfItems()+ "," +
            "discount" + ":" + getDiscount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}