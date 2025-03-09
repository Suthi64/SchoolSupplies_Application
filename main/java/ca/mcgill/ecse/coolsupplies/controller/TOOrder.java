/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.controller;
import java.sql.Date;
import java.util.*;

// line 62 "../../../../../CoolSuppliesTransferObjects.ump"
public class TOOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TOOrder Attributes
  private String parentEmail;
  private String studentName;
  private String status;
  private int number;
  private Date date;
  private String level;
  private String authorizationCode;
  private String penaltyAuthorizationCode;
  private double totalPrice;

  //TOOrder Associations
  private List<TOOrderItem> orderItems;

  //Helper Variables
  private boolean canSetOrderItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOOrder(String aParentEmail, String aStudentName, String aStatus, int aNumber, Date aDate, String aLevel, String aAuthorizationCode, String aPenaltyAuthorizationCode, double aTotalPrice, TOOrderItem... allOrderItems)
  {
    parentEmail = aParentEmail;
    studentName = aStudentName;
    status = aStatus;
    number = aNumber;
    date = aDate;
    level = aLevel;
    authorizationCode = aAuthorizationCode;
    penaltyAuthorizationCode = aPenaltyAuthorizationCode;
    totalPrice = aTotalPrice;
    canSetOrderItems = true;
    orderItems = new ArrayList<TOOrderItem>();
    boolean didAddOrderItems = setOrderItems(allOrderItems);
    if (!didAddOrderItems)
    {
      throw new RuntimeException("Unable to create TOOrder, must not have duplicate orderItems. See http://manual.umple.org?RE001ViolationofImmutability.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getParentEmail()
  {
    return parentEmail;
  }

  public String getStudentName()
  {
    return studentName;
  }

  public String getStatus()
  {
    return status;
  }

  public int getNumber()
  {
    return number;
  }

  public Date getDate()
  {
    return date;
  }

  public String getLevel()
  {
    return level;
  }

  public String getAuthorizationCode()
  {
    return authorizationCode;
  }

  public String getPenaltyAuthorizationCode()
  {
    return penaltyAuthorizationCode;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }
  /* Code from template association_GetMany */
  public TOOrderItem getOrderItem(int index)
  {
    TOOrderItem aOrderItem = orderItems.get(index);
    return aOrderItem;
  }

  public List<TOOrderItem> getOrderItems()
  {
    List<TOOrderItem> newOrderItems = Collections.unmodifiableList(orderItems);
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

  public int indexOfOrderItem(TOOrderItem aOrderItem)
  {
    int index = orderItems.indexOf(aOrderItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderItems()
  {
    return 0;
  }
  /* Code from template association_SetUnidirectionalMany */
  private boolean setOrderItems(TOOrderItem... newOrderItems)
  {
    boolean wasSet = false;
    if (!canSetOrderItems) { return false; }
    canSetOrderItems = false;
    ArrayList<TOOrderItem> verifiedOrderItems = new ArrayList<TOOrderItem>();
    for (TOOrderItem aOrderItem : newOrderItems)
    {
      if (verifiedOrderItems.contains(aOrderItem))
      {
        continue;
      }
      verifiedOrderItems.add(aOrderItem);
    }

    if (verifiedOrderItems.size() != newOrderItems.length)
    {
      return wasSet;
    }

    orderItems.clear();
    orderItems.addAll(verifiedOrderItems);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {}


  @Override
  // line 75 "../../../../../CoolSuppliesTransferObjects.ump"
   public String toString(){
    return "Number: "+ number;
  }

}