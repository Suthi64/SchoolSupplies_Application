package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.standard.MediaSize.Other;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.*;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet8Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet8Controller() {}


  

   
  /**
   * Updates an order in CoolSupplies including its purchase level and student.
   * 
   * @param orderNumber the number of the order
   * @param purchaseLevel the new purchase level of the order
   * @param studentName the new student associated with the order
   * @return an error string if there is any
   * @author Jiaduo Xing
   */
public static String updateOrder(String orderNumber, String purchaseLevel, String studentName) {
  Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
  
  if (order == null) {
      return "Order " + orderNumber + " does not exist.";
  }

  Parent parent = order.getParent();
  Student student = Student.getWithName(studentName);
  
  if (student == null) {
      return "Student " + studentName + " does not exist.";
  }

  if (!student.getParent().getEmail().equals(parent.getEmail())) {
      return "Student " + studentName + " is not a child of the parent " + parent.getEmail() + ".";
  }
  
  PurchaseLevel aPurchaseLevel;
  try {
      aPurchaseLevel = PurchaseLevel.valueOf(purchaseLevel);
  } catch (IllegalArgumentException e) {
      return "Purchase level " + purchaseLevel + " does not exist.";
  }
  
  try {
      order.updateOrder(aPurchaseLevel, student);
      CoolsuppliesPersistence.save();
  } catch (Exception e) {
      return e.getMessage();
  }

  return "";
}

  
/**
 * Adds an item to an order with the given quantity.
 * The item must exist, the order must exist, and the quantity must be greater than 0.
 * The item must not already be in the order.
 * @param item the name of the item to add
 * @param quantity the quantity of the item to add
 * @param orderNumber the number of the order to add the item to
 * @return an error message if the operation fails, an empty string otherwise
 * @author Jiaduo Xing
 */
  public static String addOrderItem(String item, String quantity, String orderNumber) {
    InventoryItem anitem = InventoryItem.getWithName(item);
    if (anitem == null){
      return "Item " + item + " does not exist.";
    }
    if (anitem instanceof GradeBundle)
    {
      GradeBundle bundle = (GradeBundle) anitem;
      if(bundle.getBundleItems().isEmpty()) return "Cannot add empty bundle";
    }
    Order anOrder = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (anOrder == null){
      return "Order " + orderNumber + " does not exist";
    }
    for(OrderItem orderItem : anOrder.getOrderItems())
    {
      if(orderItem.getItem().equals(InventoryItem.getWithName(item)))
      {
        return "Item " + item + " already exists in the order " + orderNumber + ".";
      }
    }
    try {
      anOrder.addItemToOrder(anitem, Integer.parseInt(quantity));
      CoolsuppliesPersistence.save();
    } catch (Exception e) {
      if (e.getMessage().startsWith("Unable to create order item due to")){
        return "Item "+ item + " already exists in the order " + orderNumber + ".";
      }
      return e.getMessage();
    }
    return "";
  }
}
