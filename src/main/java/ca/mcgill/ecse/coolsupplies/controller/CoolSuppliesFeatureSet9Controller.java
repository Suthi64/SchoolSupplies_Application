package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet9Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet9Controller() {}
  
  public static String updateOrderItem(String item ,String newQuantity,String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  public static String deleteOrderItem(String item, String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

}