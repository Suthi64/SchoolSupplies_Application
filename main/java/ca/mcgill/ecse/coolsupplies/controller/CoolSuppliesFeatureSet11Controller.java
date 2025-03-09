package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.*;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet11Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet11Controller() {}

  private static boolean itemIsOfLevel(BundleItem.PurchaseLevel level, BundleItem.PurchaseLevel itemLevel)
  {
    return level == BundleItem.PurchaseLevel.Optional || itemLevel == BundleItem.PurchaseLevel.Mandatory || level == BundleItem.PurchaseLevel.Recommended && itemLevel == BundleItem.PurchaseLevel.Recommended;
  }

  /**
   * The viewOrder method returns a TOOrder object containing all relevant
   * information on an Order.
   *
   * @author Doddy Yang Qiu
   * @param orderNumber The number of the Order.
   * @return null if given an invalid orderNumber, a TOOrder object otherwise.
   */
  public static TOOrder viewOrder(String orderNumber) {
    int num;

    // Validate orderNumber
    try
    {
      num = Integer.parseInt(orderNumber);
    }
    catch (Exception e)
    {
      return null;
    }
    Order myOrder = Order.getWithNumber(num);
    if(myOrder == null)
    {
      return null;
    }

    List<TOOrderItem> toItems = new ArrayList<>();
    double totalPrice = 0;
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
            totalPrice +=orderItem.getQuantity() * someBundleItem.getQuantity() * someBundleItem.getItem().getPrice() * (1 - discount * 0.01);

          }else{
            toItems.add(new TOOrderItem(orderItem.getQuantity() *  someBundleItem.getQuantity(),
                    someBundleItem.getItem().getName(),
                    myGradeBundle.getName(),
                    someBundleItem.getItem().getPrice(),
                    0));
            totalPrice +=orderItem.getQuantity() * someBundleItem.getQuantity() * someBundleItem.getItem().getPrice() ;
          }
        }
      }
      // If the item is not part of a bundle
      else if(orderItem.getItem() instanceof Item myItem)
      {
        totalPrice += orderItem.getQuantity() * myItem.getPrice();
        toItems.add(new TOOrderItem(orderItem.getQuantity(),
                myItem.getName(),
                "",
                myItem.getPrice(),
                0));
      }
    }
    totalPrice = Math.floor(totalPrice * 100)/100;
    return new TOOrder(myOrder.getParent().getEmail(),
            myOrder.getStudent().getName(),
            myOrder.getStatus().name(),
            myOrder.getNumber(),
            myOrder.getDate(),
            String.valueOf(myOrder.getLevel()),
            myOrder.getAuthorizationCode(),
            myOrder.getPenaltyAuthorizationCode(),
            totalPrice,
            toItems.toArray(new TOOrderItem[0]));
  }

  /**
   * The viewAllOrders method returns a list of TOOrder objects containing all relevant
   * information on all Orders in the system.
   *
   * @author Doddy Yang Qiu
   * @return a List of TOOrder corresponding to all Orders in the system.
   */
  public static List<TOOrder> viewAllOrders() {
    List<TOOrder> toOrders = new ArrayList<>();
    for (Order order : coolSupplies.getOrders())
    {
      toOrders.add(viewOrder(String.valueOf(order.getNumber())));
    }
    return toOrders;
  }
  

}