/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.34.0.7242.6b8819789 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import java.sql.Date;

/**
 * To accommodate orders status
 */
// line 123 "../../../../../../uml.ump"
public class Payment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Payment Attributes
  private String authorizationCode;
  private Date paymentDate;

  //Payment Associations
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Payment(String aAuthorizationCode, Date aPaymentDate, Order aOrder)
  {
    authorizationCode = aAuthorizationCode;
    paymentDate = aPaymentDate;
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create payment due to order. See https://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public Date getPaymentDate()
  {
    return paymentDate;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    //Must provide order to payment
    if (aOrder == null)
    {
      return wasSet;
    }

    //order already at maximum (2)
    if (aOrder.numberOfPayments() >= Order.maximumNumberOfPayments())
    {
      return wasSet;
    }
    
    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      boolean didRemove = existingOrder.removePayment(this);
      if (!didRemove)
      {
        order = existingOrder;
        return wasSet;
      }
    }
    order.addPayment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removePayment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "authorizationCode" + ":" + getAuthorizationCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paymentDate" + "=" + (getPaymentDate() != null ? !getPaymentDate().equals(this)  ? getPaymentDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}