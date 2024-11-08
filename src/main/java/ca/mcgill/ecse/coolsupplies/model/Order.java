/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.coolsupplies.model;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import java.util.*;
import java.sql.Date;

// line 1 "../../../../../CoolSuppliesState.ump"
// line 32 "../../../../../CoolSuppliesPersistence.ump"
// line 42 "../../../../../CoolSupplies.ump"
public class Order
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Order> ordersByNumber = new HashMap<Integer, Order>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int number;
  private Date date;
  private PurchaseLevel level;
  private String authorizationCode;
  private String penaltyAuthorizationCode;

  //Order State Machines
  public enum Status { Started, Final, Paid, Penalized, Prepared, PickedUp }
  private Status status;

  //Order Associations
  private Parent parent;
  private Student student;
  private CoolSupplies coolSupplies;
  private List<OrderItem> orderItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aNumber, Date aDate, PurchaseLevel aLevel, Parent aParent, Student aStudent, CoolSupplies aCoolSupplies)
  {
    date = aDate;
    level = aLevel;
    authorizationCode = null;
    penaltyAuthorizationCode = null;
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddParent = setParent(aParent);
    if (!didAddParent)
    {
      throw new RuntimeException("Unable to create order due to parent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddStudent = setStudent(aStudent);
    if (!didAddStudent)
    {
      throw new RuntimeException("Unable to create order due to student. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCoolSupplies = setCoolSupplies(aCoolSupplies);
    if (!didAddCoolSupplies)
    {
      throw new RuntimeException("Unable to create order due to coolSupplies. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    orderItems = new ArrayList<OrderItem>();
    setStatus(Status.Started);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (anOldNumber != null && anOldNumber.equals(aNumber)) {
      return true;
    }
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      ordersByNumber.remove(anOldNumber);
    }
    ordersByNumber.put(aNumber, this);
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setLevel(PurchaseLevel aLevel)
  {
    boolean wasSet = false;
    level = aLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorizationCode(String aAuthorizationCode)
  {
    boolean wasSet = false;
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setPenaltyAuthorizationCode(String aPenaltyAuthorizationCode)
  {
    boolean wasSet = false;
    penaltyAuthorizationCode = aPenaltyAuthorizationCode;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template attribute_GetUnique */
  public static Order getWithNumber(int aNumber)
  {
    return ordersByNumber.get(aNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }

  public Date getDate()
  {
    return date;
  }

  public PurchaseLevel getLevel()
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

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean addItemToOrder(InventoryItem aItem,int aQuantity)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 4 "../../../../../CoolSuppliesState.ump"
        doAddOrderItem(aItem, aQuantity);
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 18 "../../../../../CoolSuppliesState.ump"
        rejectAddItemToOrder("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 28 "../../../../../CoolSuppliesState.ump"
        rejectAddItemToOrder("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 39 "../../../../../CoolSuppliesState.ump"
        rejectAddItemToOrder("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 49 "../../../../../CoolSuppliesState.ump"
        rejectAddItemToOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean deleteItemOfOrder(InventoryItem aItem)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 5 "../../../../../CoolSuppliesState.ump"
        doDeleteItemOfOrder(aItem);
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 20 "../../../../../CoolSuppliesState.ump"
        rejectDeleteItemOfOrder("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 30 "../../../../../CoolSuppliesState.ump"
        rejectDeleteItemOfOrder("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 41 "../../../../../CoolSuppliesState.ump"
        rejectDeleteItemOfOrder("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 51 "../../../../../CoolSuppliesState.ump"
        rejectDeleteItemOfOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean updateOrderItemQuantity(InventoryItem aItem,int newQuantity)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 6 "../../../../../CoolSuppliesState.ump"
        doUpdateOrderItemQuantity(aItem , newQuantity);
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 19 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrderItemQuantity("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 29 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrderItemQuantity("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 40 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrderItemQuantity("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 50 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrderItemQuantity("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean updateOrder(PurchaseLevel aLevel,Student aStudent)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 7 "../../../../../CoolSuppliesState.ump"
        doUpdateOrder(aLevel, aStudent);
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 17 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrder("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 27 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrder("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 38 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrder("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 48 "../../../../../CoolSuppliesState.ump"
        rejectUpdateOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelOrder()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        setStatus(Status.Final);
        wasEventProcessed = true;
        break;
      case Paid:
        setStatus(Status.Final);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 32 "../../../../../CoolSuppliesState.ump"
        rejectCancelOrder("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 43 "../../../../../CoolSuppliesState.ump"
        rejectCancelOrder("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 53 "../../../../../CoolSuppliesState.ump"
        rejectCancelOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean payOrder(String authorizationCode)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 9 "../../../../../CoolSuppliesState.ump"
        doPayOrder(authorizationCode);
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 21 "../../../../../CoolSuppliesState.ump"
        rejectPayOrder("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 31 "../../../../../CoolSuppliesState.ump"
        rejectPayOrder("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 42 "../../../../../CoolSuppliesState.ump"
        rejectPayOrder("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 52 "../../../../../CoolSuppliesState.ump"
        rejectPayOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startSchoolYear()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Paid:
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 33 "../../../../../CoolSuppliesState.ump"
        rejectStartSchoolYear();
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 44 "../../../../../CoolSuppliesState.ump"
        rejectStartSchoolYear();
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 54 "../../../../../CoolSuppliesState.ump"
        rejectStartSchoolYear();
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean payPenaltyOrder(String penaltyAuthCode,String authCode)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 11 "../../../../../CoolSuppliesState.ump"
        rejectPayPenaltyOrder("started");
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 22 "../../../../../CoolSuppliesState.ump"
        rejectPayPenaltyOrder("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 26 "../../../../../CoolSuppliesState.ump"
        doPayPenaltyOrder(penaltyAuthCode, authCode);
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case Prepared:
        // line 45 "../../../../../CoolSuppliesState.ump"
        rejectPayPenaltyOrder("prepared");
        setStatus(Status.Prepared);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 55 "../../../../../CoolSuppliesState.ump"
        rejectPayPenaltyOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pickUpOrder()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        // line 12 "../../../../../CoolSuppliesState.ump"
        rejectPickUpOrder("started");
        setStatus(Status.Started);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 23 "../../../../../CoolSuppliesState.ump"
        rejectPickUpOrder("paid");
        setStatus(Status.Paid);
        wasEventProcessed = true;
        break;
      case Penalized:
        // line 34 "../../../../../CoolSuppliesState.ump"
        rejectPickUpOrder("penalized");
        setStatus(Status.Penalized);
        wasEventProcessed = true;
        break;
      case Prepared:
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      case PickedUp:
        // line 56 "../../../../../CoolSuppliesState.ump"
        rejectPickUpOrder("picked up");
        setStatus(Status.PickedUp);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case Final:
        delete();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Parent getParent()
  {
    return parent;
  }
  /* Code from template association_GetOne */
  public Student getStudent()
  {
    return student;
  }
  /* Code from template association_GetOne */
  public CoolSupplies getCoolSupplies()
  {
    return coolSupplies;
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
  /* Code from template association_SetOneToMany */
  public boolean setStudent(Student aStudent)
  {
    boolean wasSet = false;
    if (aStudent == null)
    {
      return wasSet;
    }

    Student existingStudent = student;
    student = aStudent;
    if (existingStudent != null && !existingStudent.equals(aStudent))
    {
      existingStudent.removeOrder(this);
    }
    student.addOrder(this);
    wasSet = true;
    return wasSet;
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
  public static int minimumNumberOfOrderItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderItem addOrderItem(int aQuantity, CoolSupplies aCoolSupplies, InventoryItem aItem)
  {
    return new OrderItem(aQuantity, aCoolSupplies, this, aItem);
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
    ordersByNumber.remove(getNumber());
    Parent placeholderParent = parent;
    this.parent = null;
    if(placeholderParent != null)
    {
      placeholderParent.removeOrder(this);
    }
    Student placeholderStudent = student;
    this.student = null;
    if(placeholderStudent != null)
    {
      placeholderStudent.removeOrder(this);
    }
    CoolSupplies placeholderCoolSupplies = coolSupplies;
    this.coolSupplies = null;
    if(placeholderCoolSupplies != null)
    {
      placeholderCoolSupplies.removeOrder(this);
    }
    for(int i=orderItems.size(); i > 0; i--)
    {
      OrderItem aOrderItem = orderItems.get(i - 1);
      aOrderItem.delete();
    }
  }


  /**
   * 
   * Updates the purchase level and the student of an order
   * author: Brian Yang 
   * param: aLevel the new purchase level 
   * param: aStudent the new student
   */
  // line 68 "../../../../../CoolSuppliesState.ump"
   private void doUpdateOrder(PurchaseLevel aLevel, Student aStudent){
    this.setLevel(aLevel);
    if (aStudent.getParent()==null) {
      throw new RuntimeException("Student "+aStudent.getName()+" is not a child of the parent "+this.getParent().getEmail()+".");
    }
    if (!(this.parent.getEmail().equals(aStudent.getParent().getEmail()))) {
      throw new RuntimeException("Student "+aStudent.getName()+" is not a child of the parent "+this.getParent().getEmail()+".");
    }
     this.setStudent(aStudent);
  }


  /**
   * 
   * Performs input validation and then adds a new OrderItem to   * CoolSupplies 
   * author: Brian Yang 
   * param: aItem an InventoryItem
   * param: aQuantity the quantity of the OrderItem
   */
  // line 86 "../../../../../CoolSuppliesState.ump"
   private void doAddOrderItem(InventoryItem aItem, int aQuantity){
    if (!(aQuantity>0)) {
       throw new RuntimeException("Quantity must be greater than 0.");
     }
    this.getCoolSupplies().addOrderItem(aQuantity, this, aItem);
  }


  /**
   * 
   * Performs input validation and then updates the quantity of 
   * an OrderItem 
   * author: Brian Yang 
   * param: aItem an InventoryItem
   * param: newQuantity the new quantity of the OrderItem
   */
  // line 101 "../../../../../CoolSuppliesState.ump"
   private void doUpdateOrderItemQuantity(InventoryItem aItem, int newQuantity){
    if (!(newQuantity>0)) {
       throw new RuntimeException("Quantity must be greater than 0.");
     }
     boolean existsInOrder=false;
    for (OrderItem aOrderItem:this.getCoolSupplies().getOrderItems()) {
      if (aOrderItem.getItem().getName().equals(aItem.getName())) {
        aOrderItem.setQuantity(newQuantity);
        existsInOrder=true;
        break;
      }
    }
    if (!existsInOrder) {
      throw new RuntimeException("Item "+aItem.getName()+" does not exist in the order "+this.number+".");
    }
  }


  /**
   * 
   * Performs input validation and then deletes an OrderItem 
   * author: Brian Yang 
   * param: aItem an InventoryItem
   */
  // line 124 "../../../../../CoolSuppliesState.ump"
   private void doDeleteItemOfOrder(InventoryItem aItem){
    boolean existsInOrder=false; 
    for(OrderItem aOrderItem: this.getCoolSupplies().getOrderItems()) {
      if (aOrderItem.getItem().getName().equals(aItem.getName())) {
        aOrderItem.delete();
        existsInOrder=true;
        break;
      }
    }
    if (!existsInOrder) {
      throw new RuntimeException("Item "+aItem.getName()+" does not exist in the order "+this.number+".");
    }
  }


  /**
   * 
   * Performs input validation and then updates the order with    
   * the payment authorization code. 
   * author: Brian Yang 
   * param: authorizationCode the payment authorization code
   */
  // line 145 "../../../../../CoolSuppliesState.ump"
   private void doPayOrder(String authorizationCode){
    if (authorizationCode== null) {
       throw new RuntimeException("Authorization code is invalid");
     }
     if (authorizationCode.length()!=4) {
       throw new RuntimeException("Authorization code is invalid");
     }
    this.setAuthorizationCode(authorizationCode);
  }


  /**
   * 
   * Performs input validation and then updates the order with
   * by setting the penalty authorization code and the payment 
   * authorization code 
   * author: Brian Yang 
   * param: penaltyAuthCode the penalty authorization code
   * param: authCode the payment authorization code
   */
  // line 164 "../../../../../CoolSuppliesState.ump"
   private void doPayPenaltyOrder(String penaltyAuthCode, String authCode){
    if (penaltyAuthCode== null) {
       throw new RuntimeException("Penalty authorization code is invalid");
     }
     if (penaltyAuthCode.length()!=4) {
       throw new RuntimeException("Penalty authorization code is invalid");
     }
     if (authCode== null) {
       throw new RuntimeException("Authorization code is invalid");
     }
     if (authCode.length()!=4) {
       throw new RuntimeException("Authorization code is invalid");
     }
    this.setAuthorizationCode(authCode);
    this.setPenaltyAuthorizationCode(penaltyAuthCode);
  }


  /**
   * 
   * Throws a runtime exception indicating that updating an order 
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 188 "../../../../../CoolSuppliesState.ump"
   private void rejectUpdateOrder(String aState){
    throw new RuntimeException("Cannot update a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that adding an OrderItem
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 199 "../../../../../CoolSuppliesState.ump"
   private void rejectAddItemToOrder(String aState){
    throw new RuntimeException("Cannot add items to a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that updating the quantity of an OrderItem
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 210 "../../../../../CoolSuppliesState.ump"
   private void rejectUpdateOrderItemQuantity(String aState){
    throw new RuntimeException("Cannot update items in a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that deleting an OrderItem 
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 221 "../../../../../CoolSuppliesState.ump"
   private void rejectDeleteItemOfOrder(String aState){
    throw new RuntimeException("Cannot delete items from a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that paying for an order
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 232 "../../../../../CoolSuppliesState.ump"
   private void rejectPayOrder(String aState){
    if (aState.equals("paid")) {
      throw new RuntimeException("The order is already paid");
    }
    throw new RuntimeException("Cannot pay for a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that canceling an order
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 246 "../../../../../CoolSuppliesState.ump"
   private void rejectCancelOrder(String aState){
    throw new RuntimeException("Cannot cancel a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that starting the school year for the order
   * is not allowed in the current state
   * author: Brian Yang
   */
  // line 256 "../../../../../CoolSuppliesState.ump"
   private void rejectStartSchoolYear(){
    throw new RuntimeException("The school year has already been started");
  }


  /**
   * 
   * Throws a runtime exception indicating that paying the penalty for the order
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 267 "../../../../../CoolSuppliesState.ump"
   private void rejectPayPenaltyOrder(String aState){
    throw new RuntimeException("Cannot pay penalty for a "+aState+" order");
  }


  /**
   * 
   * Throws a runtime exception indicating that picking up the order
   * is not allowed in the current state
   * author: Brian Yang 
   * param: aState the current state
   */
  // line 278 "../../../../../CoolSuppliesState.ump"
   private void rejectPickUpOrder(String aState){
    if (aState.equals("picked up")) {
      throw new RuntimeException("The order is already picked up");
    }
    throw new RuntimeException("Cannot pickup a "+aState+" order");
  }

  // line 34 "../../../../../CoolSuppliesPersistence.ump"
   public static  void reinitializeUniqueNumber(List<Order> orders){
    ordersByNumber.clear();
    		for (var order : orders) {
      			ordersByNumber.put(order.getNumber(), order);
    		}
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "penaltyAuthorizationCode" + ":" + getPenaltyAuthorizationCode()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "level" + "=" + (getLevel() != null ? !getLevel().equals(this)  ? getLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "parent = "+(getParent()!=null?Integer.toHexString(System.identityHashCode(getParent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "student = "+(getStudent()!=null?Integer.toHexString(System.identityHashCode(getStudent())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "coolSupplies = "+(getCoolSupplies()!=null?Integer.toHexString(System.identityHashCode(getCoolSupplies())):"null");
  }
}