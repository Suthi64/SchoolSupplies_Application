package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet8Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet8Controller() {}
  
  public static String updateOrder(String orderNumber,String purchaseLevel,String studentName) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  public static String addOrderItem(String item, String quantity, String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

}