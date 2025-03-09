package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.GradeBundle;
import ca.mcgill.ecse.coolsupplies.model.Item;
import ca.mcgill.ecse.coolsupplies.model.BundleItem;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

/**
*This class manages the bundle item entities in the Cool Supplies application
* This class contains methods to add, update, delete and retrieve bundleItems
* @author  Suthiesan Subramaniam
* 
*/

public class CoolSuppliesFeatureSet5Controller {

  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

/**
 * This method adds a bundle item with its name, quantity and level to a specified  grade bundle
 * 
 * @param quantity the quantity of item to be added 
 * @param level  the purchase level of the item  to be added
 * @param itemName the name of item to be added
 * @param bundleName the name of the bundle where the item is going to be added
 * @return An error message if the operation failed or an empty string if succesfull
 */

  public static String addBundleItem(int quantity, String level, String itemName,
      String bundleName) {
    var error = "";
    if (quantity <= 0) {
      error = "The quantity must be greater than 0.";
      return error.trim();
    } 

    PurchaseLevel purchaseLevel = null;
    for (PurchaseLevel purchaseLevelX : PurchaseLevel.values()) {
      if (purchaseLevelX.name().equals(level)) { 
        purchaseLevel = purchaseLevelX;
        break;
      }
    } if (purchaseLevel == null) {
        error = "The level must be Mandatory, Recommended, or Optional.";
      return error.trim();
    }

    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()) {
      if (bundle.getName().equals(bundleName)) {
        myBundle = bundle;
        break;
      }
    } if (myBundle == null) {
        error = "The grade bundle does not exist.";
      return error.trim();
    }

    Item item = null;
    for (Item itemi : coolSupplies.getItems()) {
      if (itemi.getName().equals(itemName)) {
        item = itemi;
        break;
      }
    } if (item == null) {
      error = "The item does not exist.";
      return error.trim();
    }

    BundleItem itemBun = null;
    for (BundleItem bundleItem : myBundle.getBundleItems()) {
      if (bundleItem.getItem().getName().equals(itemName)) {
        itemBun = bundleItem;
        break;
      }
    } if (itemBun != null) {
        error = "The item already exists for the bundle.";
        return error.trim();
      }
    try {
      coolSupplies.addBundleItem(quantity, purchaseLevel, myBundle, item);
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;
    }
/**
 * This method update an existing bundle item in a specified  grade bundle with new quantity and new level
 * 
 * @param itemName the name of item to update 
 * @param bundleName the name of the bundle containing the item
 * @param newQuantity the new quantity of item
 * @param newLevel the new purchase level of the item
 * @return An error message if the operation failed or an empty string if succesfull
 */

  public static String updateBundleItem(String itemName, String bundleName, int newQuantity,
      String newLevel) {
    var error = "";
    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()) {
      if (bundle.getName().equals(bundleName)) {
        myBundle = bundle;
        break;
      }
    } if (myBundle == null) {
      error = "The grade bundle does not exist.";
      return error.trim();
    } else {
      BundleItem item = null;
      for (BundleItem bundleItem : myBundle.getBundleItems()) {
        if (bundleItem.getItem().getName().equals(itemName)) {
          item = bundleItem;
          break;
        }
      }
        if (item == null) {
          error = "The bundle item does not exist for the grade bundle.";
          return error.trim();
      } else {
        if (newQuantity <= 0) {
          error = "The quantity must be greater than 0.";
          return error.trim();
        } 

      PurchaseLevel purchaseLevel = null;
        for (PurchaseLevel purchaseLevelX : PurchaseLevel.values()) {
          if (purchaseLevelX.name().equals(newLevel)) { 
            purchaseLevel = purchaseLevelX;
            break;
          }
        } if (purchaseLevel == null) {
            error = "The level must be Mandatory, Recommended, or Optional.";
          return error.trim();
        }
    
      try{
        item.setQuantity(newQuantity);
        item.setLevel(purchaseLevel);
        CoolsuppliesPersistence.save();
      }catch (Exception e){
        error = e.getMessage();
      }
    }
    }
    return error;
  }

/**
 * This method delete an existing bundle item in a specified  grade bundle
 * 
 * @param itemName the name of item to delete 
 * @param bundleName the name of the bundle containing the item
 * @return An error message if the operation failed or an empty string if succesfull
 */

  public static String deleteBundleItem(String itemName, String bundleName) {
    var error = "";
    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()) {
      if (bundle.getName().equals(bundleName)) {
        myBundle = bundle;
        break;
      }
    } if (myBundle == null) {
        error = "The grade bundle does not exist.";
      return error.trim();
    }

    BundleItem item = null;
    for (BundleItem bundleItem : myBundle.getBundleItems()) {
      if (bundleItem.getItem().getName().equals(itemName)) {
        item = bundleItem;
        break;
      }
    }
    if (item == null) {
      error = "The bundle item does not exist.";
    return error.trim();
    }

    try {
      item.delete();
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;
  }

/**
 * This method retrieves a bundle item in a specified  grade bundle
 * 
 * @param itemName the name of item to retrieve 
 * @param bundleName the name of the bundle containing the item
 * @return An object representing the bundle item  or null if not found
 */
  public static TOBundleItem getBundleItem(String itemName, String bundleName) {
    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()) {
      if (bundle.getName().equals(bundleName)) {
        myBundle = bundle;
        break;
      }
    }
    if (myBundle == null) {
      return null;
    } 
    
    BundleItem item = null;
    for (BundleItem bundleItem : myBundle.getBundleItems()) {
      if (bundleItem.getItem().getName().equals(itemName)) {
        item = bundleItem;
        break;
      }
    }
    
    if (item == null) {
      return null;
    } 

    int quantity = item.getQuantity();
    String level = item.getLevel().name();
    return new TOBundleItem(quantity, level, itemName, bundleName);
  }

  /**
 * This method retrieves all bundle Items in a specified  grade bundle
 * 
 * @param bundleName the name of the bundle
 * @return A list of objects representing the items in the bundle or null if the bundle does not exist
 */
     
  public static List<TOBundleItem> getBundleItems(String bundleName) {
    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()) {
      if (bundle.getName().equals(bundleName)) {
        myBundle = bundle;
        break;
      }
    }
    if (myBundle == null) {
      return null;
    } 

    List<TOBundleItem> bundleItems = new ArrayList<TOBundleItem>();
    for (BundleItem bundleItem : myBundle.getBundleItems()) {
      bundleItems.add(new TOBundleItem(bundleItem.getQuantity(), bundleItem.getLevel().name(), bundleItem.getItem().getName(), bundleName));
    }
    return bundleItems;
  }
}
