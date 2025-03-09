package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.InventoryItem;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;
import ca.mcgill.ecse.coolsupplies.model.Order;

public class CoolSuppliesFeatureSet9Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet9Controller() {}

/*
 * The updateOrderItem method updates the quantity of an item in an order
 * 
 * @author        Sanad Abu Baker
 * @param         itemName a string indicating the name of the item that the user wants to update.
 * @param         newQuantity an int indicating the intended updated quantity of the item.
 * @param         orderNumber an int used to find the corresponding order in the system.
 * @return an error message if any of the inputs are invalid, an empty string indicating success otherwise.
 */
  public static String updateOrderItem(String itemName, String newQuantity, String orderNumber) {

    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (order == null) {
      return "Order " + orderNumber + " does not exist";
    }

    InventoryItem inventoryItem = InventoryItem.getWithName(itemName);
    if (inventoryItem == null) {
      return "Item " + itemName + " does not exist.";
    }

    try {
      order.updateOrderItemQuantity(inventoryItem, Integer.parseInt(newQuantity));
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return "";
  }

  /*
  * The deleteOrderItem method deletes an item from an order.
  * 
  * @author        Sanad Abu Baker
  * @param         itemName a string indicating the name of the item that the user wants to delete.
  * @param         orderNumber an int used to find the corresponding order in the system.
  * @return an error message if any of the inputs are invalid, an empty string indicating success otherwise.
  */
  public static String deleteOrderItem(String itemName, String orderNumber) {

    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (order == null) {
      return "Order " + orderNumber + " does not exist";
    }

    InventoryItem inventoryItem = InventoryItem.getWithName(itemName);
    if (inventoryItem == null) {
      return "Item " + itemName + " does not exist.";
    }

    try {
      order.deleteItemOfOrder(inventoryItem);
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return "";
  }
}