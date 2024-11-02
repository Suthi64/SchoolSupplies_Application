package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Item;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication.getCoolSupplies;

public class CoolSuppliesFeatureSet3Controller {

    private static final CoolSupplies coolSupplies = getCoolSupplies();

    /**
     * Adds an item to the system with given name and price.
     * The name must be unique, and the price must be greater than or equal to 0.
     * @param name the name of the item to add
     * @param price the price of the item to add
     * @return the item added as a string in the format "name:price" if successful, error message otherwise
     * @author Jiaduo Xing
     */
    public static String addItem(String name, int price){
      if(name == null || name.isEmpty()){
        return "The name must not be empty.";
      }

      if (price < 0){
        return "The price must be greater than or equal to 0.";
      }

      List<Item> items = coolSupplies.getItems();
      for(Item item: items){
        if (item.getName().equals(name)){
          return "The name must be unique.";
        }
      }

      try {
        coolSupplies.addItem(name, price);
        CoolsuppliesPersistence.save();
      } catch (RuntimeException e) {
        return e.getMessage();
      }
      
      return "";
  }

    /**
     * Updates an item in the system with given name and price.
     * The name must be unique, and the price must be greater than or equal to 0.
     * @param name the name of the item to update
     * @param newName the new name of the item to update
     * @param newPrice the new price of the item to update
     * @return the item updated as a string in the format "name:price" if successful, error message otherwise
     * @author Jiaduo Xing
     */
  public static String updateItem(String name, String newName, int newPrice) {
    List<Item> items = coolSupplies.getItems();

    if(newName == null || newName.isEmpty()){
      return "The name must not be empty.";
    }

    if (newPrice < 0){
      return "The price must be greater than or equal to 0.";
    }

    for(Item item: items){
      if (item.getName().equals(newName)){
        return "The name must be unique.";
      }
    }

    for (Item item : items) {
      if (item.getName().equals(name)) {
        try {
          item.setName(newName);
          item.setPrice(newPrice);
          CoolsuppliesPersistence.save();
        } catch (RuntimeException e) {
          return e.getMessage();
        }
        return "";
      }
    }
    return "The item does not exist.";
  }

    /**
     * Deletes an item from the system with given name.
     * The item must exist in the system.
     * @param name the name of the item to delete
     * @return the item deleted as a string in the format "name:price" if successful, error message otherwise
     * @author Jiaduo Xing
     */
  public static String deleteItem(String name){
      List<Item> items = coolSupplies.getItems();
      for (Item item : items) {
        if(item.getName().equals(name)) {
          try {
            item.delete();
            CoolsuppliesPersistence.save();
          } catch (RuntimeException e) {
            return e.getMessage();
          }
          return "";
        }
      }
      return "The item does not exist.";
  }

    /**
     * Retrieves an item from the system with given name.
     * The item must exist in the system.
     * @param name the name of the item to retrieve
     * @return the item retrieved as a TOItem object if successful, null otherwise
     * @author Jiaduo Xing
     */
  public static TOItem getItem(String name) {
      List<Item> items = coolSupplies.getItems();
      for (Item item : items) {
        if(item.getName().equals(name)) {
          return new TOItem(item.getName(), item.getPrice());
        }
      }
    return null;
  }

    /**
     * Retrieves all items from the system.
     * @return a list of all items in the system, each as a TOItem object
     * @author Jiaduo Xing
     */
  public static List<TOItem> getItems() {
      List<Item> items = coolSupplies.getItems();
      List<TOItem> toItems = new ArrayList<TOItem>();
      for (Item item : items) {
        toItems.add(new TOItem(item.getName(), item.getPrice()));
      }
      return toItems;
  }

}
