package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet12Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet12Controller() {}
  
  public static String startSchoolYearForOrder(String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  public static String pickUpOrder(String orderNumber) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  

}