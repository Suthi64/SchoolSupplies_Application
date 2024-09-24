/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/



// line 96 "uml.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private double price;

  //Item Associations
  private Bundle bundle;
  private SchoolSupply schoolSupply;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, double aPrice, Bundle aBundle, SchoolSupply aSchoolSupply)
  {
    name = aName;
    price = aPrice;
    boolean didAddBundle = setBundle(aBundle);
    if (!didAddBundle)
    {
      throw new RuntimeException("Unable to create item due to bundle. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSchoolSupply = setSchoolSupply(aSchoolSupply);
    if (!didAddSchoolSupply)
    {
      throw new RuntimeException("Unable to create item due to schoolSupply. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
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
  /* Code from template association_SetOneToMany */
  public boolean setBundle(Bundle aBundle)
  {
    boolean wasSet = false;
    if (aBundle == null)
    {
      return wasSet;
    }

    Bundle existingBundle = bundle;
    bundle = aBundle;
    if (existingBundle != null && !existingBundle.equals(aBundle))
    {
      existingBundle.removeItem(this);
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
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "bundle = "+(getBundle()!=null?Integer.toHexString(System.identityHashCode(getBundle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "schoolSupply = "+(getSchoolSupply()!=null?Integer.toHexString(System.identityHashCode(getSchoolSupply())):"null");
  }
}