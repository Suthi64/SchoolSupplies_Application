package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Item;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet8Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet8Controller() {}

  public static String updateOrder(String orderNumber, String purchaseLevel, String studentName) {
    if (orderNumber == null || orderNumber.isEmpty()) {
      return "Error: Order number is required.";
    }
    if (purchaseLevel == null || purchaseLevel.isEmpty()) {
      return "Error: Purchase level is required.";
    }
    if (studentName == null || studentName.isEmpty()) {
      return "Error: Student name is required.";
    }

    List<Order> orders = coolSupplies.getOrders();
    for (Order order : orders) {
      if (order.getNumber() == Integer.parseInt(orderNumber)) {
        order.setLevel(PurchaseLevel.valueOf(purchaseLevel));
        Student student = order.getStudent();
        student.setName(studentName);
        order.setStudent(student);
        CoolsuppliesPersistence.save();
        return "Order updated successfully.";
      }
    }
    return "Error: Order not found.";

  }

  public static String addOrderItem(String item, String quantity, String orderNumber) {
    if (item == null || item.isEmpty()) {
      return "Error: Item name is required.";
    }
    if (quantity == null || quantity.isEmpty()) {
      return "Error: Quantity is required.";
    }
    if (orderNumber == null || orderNumber.isEmpty()) {
      return "Error: Order number is required.";
    }
    List<Item> items = coolSupplies.getItems();
    for (Item currentitem : items) {
      if (currentitem.getName().equals(item)) {
        List<Order> orders = coolSupplies.getOrders();
        for (Order order : orders) {
          if (order.getNumber() == Integer.parseInt(orderNumber)) {
            order.addItemToOrder(currentitem, Integer.parseInt(quantity));
            CoolsuppliesPersistence.save();
            return "Order updated successfully.";
          }
        }
        return "Error: Order not found.";
      }
    }
    return "Error: Item not found.";
  }



}
