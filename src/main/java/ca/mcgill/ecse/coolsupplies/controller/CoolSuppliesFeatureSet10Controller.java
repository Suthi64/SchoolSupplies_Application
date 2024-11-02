package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet10Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet10Controller() {}
  
  public static String payOrder(String orderNumber,String authorizationCode) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  public static String payPenaltyOrder(String item, String penaltyAuthorizationCode, String authorizationCode) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  public static String cancelOrder(String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

}