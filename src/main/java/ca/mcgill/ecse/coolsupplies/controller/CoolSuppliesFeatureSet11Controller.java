package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet11Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet11Controller() {}
  
  public static TOOrder viewOrder(String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  public static List<TOOrder> viewAllOrders() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  

}