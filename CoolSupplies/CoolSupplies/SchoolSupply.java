/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 101 "uml.ump"
public class SchoolSupply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SchoolSupply Associations
  private List<Item> items;
  private CoolSupplies coolSupplies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SchoolSupply(CoolSupplies aCoolSupplies)
  {
    items = new ArrayList<Item>();
    if (aCoolSupplies == null || aCoolSupplies.getSchoolSupply() != null)
    {
      throw new RuntimeException("Unable to create SchoolSupply due to aCoolSupplies. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    coolSupplies = aCoolSupplies;
  }

  public SchoolSupply(int aSchoolYearForCoolSupplies, Date aStartSchoolYearForCoolSupplies, Date aEndSchoolYearForCoolSupplies)
  {
    items = new ArrayList<Item>();
    coolSupplies = new CoolSupplies(aSchoolYearForCoolSupplies, aStartSchoolYearForCoolSupplies, aEndSchoolYearForCoolSupplies, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  public CoolSupplies getCoolSupplies()
  {
    return coolSupplies;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(String aName, double aPrice, Bundle aBundle)
  {
    return new Item(aName, aPrice, aBundle, this);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    SchoolSupply existingSchoolSupply = aItem.getSchoolSupply();
    boolean isNewSchoolSupply = existingSchoolSupply != null && !this.equals(existingSchoolSupply);
    if (isNewSchoolSupply)
    {
      aItem.setSchoolSupply(this);
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
    //Unable to remove aItem, as it must always have a schoolSupply
    if (!this.equals(aItem.getSchoolSupply()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
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

  public void delete()
  {
    while (items.size() > 0)
    {
      Item aItem = items.get(items.size() - 1);
      aItem.delete();
      items.remove(aItem);
    }
    
    CoolSupplies existingCoolSupplies = coolSupplies;
    coolSupplies = null;
    if (existingCoolSupplies != null)
    {
      existingCoolSupplies.delete();
    }
  }

}