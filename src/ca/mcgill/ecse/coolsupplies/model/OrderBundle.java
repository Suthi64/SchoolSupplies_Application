/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;

// line 130 "../../../../../CoolSupplies.ump"
public class OrderBundle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderBundle Attributes
  private int repetition;

  //OrderBundle Associations
  private Order order;
  private Bundle bundle;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetOrder;
  private boolean canSetBundle;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderBundle(int aRepetition, Order aOrder, Bundle aBundle)
  {
    cachedHashCode = -1;
    canSetOrder = true;
    canSetBundle = true;
    repetition = aRepetition;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create orderBundle due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBundle = setBundle(aBundle);
    if (!didAddBundle)
    {
      throw new RuntimeException("Unable to create orderBundle due to bundle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRepetition(int aRepetition)
  {
    boolean wasSet = false;
    repetition = aRepetition;
    wasSet = true;
    return wasSet;
  }

  public int getRepetition()
  {
    return repetition;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public Bundle getBundle()
  {
    return bundle;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (!canSetOrder) { return false; }
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeOrderBundle(this);
    }
    if (!order.addOrderBundle(this))
    {
      order = existingOrder;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setBundle(Bundle aBundle)
  {
    boolean wasSet = false;
    if (!canSetBundle) { return false; }
    if (aBundle == null)
    {
      return wasSet;
    }

    Bundle existingBundle = bundle;
    bundle = aBundle;
    if (existingBundle != null && !existingBundle.equals(aBundle))
    {
      existingBundle.removeOrderBundle(this);
    }
    if (!bundle.addOrderBundle(this))
    {
      bundle = existingBundle;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    OrderBundle compareTo = (OrderBundle)obj;
  
    if (getOrder() == null && compareTo.getOrder() != null)
    {
      return false;
    }
    else if (getOrder() != null && !getOrder().equals(compareTo.getOrder()))
    {
      return false;
    }

    if (getBundle() == null && compareTo.getBundle() != null)
    {
      return false;
    }
    else if (getBundle() != null && !getBundle().equals(compareTo.getBundle()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getOrder() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getOrder().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getBundle() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getBundle().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetOrder = false;
    canSetBundle = false;
    return cachedHashCode;
  }

  public void delete()
  {
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeOrderBundle(this);
    }
    Bundle placeholderBundle = bundle;
    this.bundle = null;
    if(placeholderBundle != null)
    {
      placeholderBundle.removeOrderBundle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "repetition" + ":" + getRepetition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bundle = "+(getBundle()!=null?Integer.toHexString(System.identityHashCode(getBundle())):"null");
  }
}