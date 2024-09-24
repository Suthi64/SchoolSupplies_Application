/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/


import java.util.*;

// line 88 "uml.ump"
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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bundle(int aNumberOfItems)
  {
    numberOfItems = aNumberOfItems;
    discount = 0;
    grades = new ArrayList<Grade>();
    items = new ArrayList<Item>();
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(String aName, double aPrice, SchoolSupply aSchoolSupply)
  {
    return new Item(aName, aPrice, this, aSchoolSupply);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    Bundle existingBundle = aItem.getBundle();
    boolean isNewBundle = existingBundle != null && !this.equals(existingBundle);
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
    if (!this.equals(aItem.getBundle()))
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
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberOfItems" + ":" + getNumberOfItems()+ "," +
            "discount" + ":" + getDiscount()+ "]";
  }
}