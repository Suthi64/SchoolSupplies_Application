/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/



// line 105 "uml.ump"
public class BundleItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BundleItem Attributes
  private ItemType type;
  private int number;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BundleItem(ItemType aType, int aNumber)
  {
    type = aType;
    number = aNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(ItemType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public ItemType getType()
  {
    return type;
  }

  public int getNumber()
  {
    return number;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 107 "uml.ump"
  0..1 type -- 1 Item  
  enum ItemType {Mandatory, Recommended, Optional};

  
}