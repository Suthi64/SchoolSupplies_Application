package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.BundleItem;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;
import ca.mcgill.ecse.coolsupplies.model.Item;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.Order.Status;
import ca.mcgill.ecse.coolsupplies.model.OrderItem;
import ca.mcgill.ecse.coolsupplies.model.Parent;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.model.GradeBundle;
import ca.mcgill.ecse.coolsupplies.model.InventoryItem;;


public class CoolSuppliesFeatureSet13Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet13Controller() {}

  private static boolean itemIsOfLevel(BundleItem.PurchaseLevel level, BundleItem.PurchaseLevel itemLevel)
  {
    return level == BundleItem.PurchaseLevel.Optional || itemLevel == BundleItem.PurchaseLevel.Mandatory || level == BundleItem.PurchaseLevel.Recommended && itemLevel == BundleItem.PurchaseLevel.Recommended;
  }

  public static List<TOOrderItem> getOrderItems(int orderNumber){
    Order myOrder = Order.getWithNumber(orderNumber);
    List<TOOrderItem> toItems = new ArrayList<>();
    for (OrderItem orderItem : myOrder.getOrderItems())
    {
      // If the item is part of a bundle
      if(orderItem.getItem() instanceof GradeBundle myGradeBundle)
      {
        int discount = myGradeBundle.getDiscount();
        List<BundleItem> aListBundleItems =new ArrayList<BundleItem>();
        for (BundleItem bundleItem : myGradeBundle.getBundleItems())
        {
          if(itemIsOfLevel(myOrder.getLevel(), bundleItem.getLevel()))
          {
            aListBundleItems.add(bundleItem);
          }
        }
        for (BundleItem someBundleItem : aListBundleItems){
          if (aListBundleItems.size()>=2){
            toItems.add(new TOOrderItem(orderItem.getQuantity() *  someBundleItem.getQuantity(),
                    someBundleItem.getItem().getName(),
                    myGradeBundle.getName(),
                    someBundleItem.getItem().getPrice(),
                    -discount * 0.01 * someBundleItem.getItem().getPrice()));

          }else{
            toItems.add(new TOOrderItem(orderItem.getQuantity() *  someBundleItem.getQuantity(),
                    someBundleItem.getItem().getName(),
                    myGradeBundle.getName(),
                    someBundleItem.getItem().getPrice(),
                    0));
          }
        }
      }
      // If the item is not part of a bundle
      else if(orderItem.getItem() instanceof Item myItem)
      {
        toItems.add(new TOOrderItem(orderItem.getQuantity(),
                myItem.getName(),
                "",
                myItem.getPrice(),
                0));
      }
    }
    return toItems;
  }

  public static List<TOOrderItem> viewItemsAllOrderItems(){
    List<TOOrderItem> orderItems= new ArrayList<TOOrderItem>();
    List<TOOrder> orders = CoolSuppliesFeatureSet11Controller.viewAllOrders();
    for (TOOrder aOrder:orders){
      orderItems.addAll(aOrder.getOrderItems());
    }
    return orderItems;
  }

  public static String getParentOfStudent(String studentName){
    Student aStudent = Student.getWithName(studentName);
    Parent parent = aStudent.getParent();
    if (parent == null){
      return "no parent";
    }
    return parent.getEmail();
  }

  public static List<String> getInventoryItemsOfOrder(int orderNumber){
    List<String> inventoryItems= new ArrayList<String>();
    Order order = Order.getWithNumber(orderNumber);
    if (order == null){
      return inventoryItems;
    }
    for (OrderItem orderItem :order.getOrderItems()){
      inventoryItems.add(orderItem.getItem().getName());
    }
    return inventoryItems;
  }

}