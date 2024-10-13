package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Item;

import javax.management.InstanceNotFoundException;
import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.List;

import static ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication.getCoolSupplies;

public class CoolSuppliesFeatureSet3Controller {

    private static CoolSupplies coolSupplies = getCoolSupplies();

    public static String addItem(String name, int price) throws InvalidAttributeValueException {
      if(name == null || name.isEmpty()){
        throw new InvalidAttributeValueException("Item name can not be empty");
      }
      return coolSupplies.addItem(name, price).toString();
    //throw new UnsupportedOperationException("Not implemented yet.");
  }

  public static String updateItem(String name, String newName, int newPrice) throws InstanceNotFoundException {
    List<Item> items = coolSupplies.getItems();
    for (Item item : items) {
      if(item.getName().equals(name)) {
        item.setName(newName);
        item.setPrice(newPrice);
        return item.toString();
      }
    }
    throw new InstanceNotFoundException("Item name not found");
      //throw new UnsupportedOperationException("Not implemented yet.");
  }

  public static String deleteItem(String name) throws InstanceNotFoundException {
      List<Item> items = coolSupplies.getItems();
      for (Item item : items) {
        if(item.getName().equals(name)) {
          coolSupplies.removeItem(item);
          return item.toString();
        }
      }
      throw new InstanceNotFoundException("Item not found");
    //throw new UnsupportedOperationException("Not implemented yet.");
  }

  public static TOItem getItem(String name) throws InstanceNotFoundException {
      List<Item> items = coolSupplies.getItems();
      for (Item item : items) {
        if(item.getName().equals(name)) {
          return new TOItem(item.getName(), item.getPrice());
        }
      }
    throw new InstanceNotFoundException("Item not found");
  }

  // returns all items
  public static List<TOItem> getItems() {
      List<Item> items = coolSupplies.getItems();
      List<TOItem> toItems = new ArrayList<TOItem>();
      for (Item item : items) {
        toItems.add(new TOItem(item.getName(), item.getPrice()));
      }
      return toItems;
  }

}
