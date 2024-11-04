package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.Order.Status;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

/**
*This class manages the order entities in the Cool Supplies application
* This class contains methods to pay order and penalty order and cancel order
* @author  Suthiesan Subramaniam
* 
*/

public class CoolSuppliesFeatureSet10Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet10Controller() {}
  
  /**
 * This method pay an order with its authorization code and order number
 * 
 * @param orderNumber the order number to be paid 
 * @param authorizationCode  the authorization code for payment
 * @return An error message if the operation failed or an empty string if succesfull
 */

  public static String payOrder(String orderNumber,String authorizationCode) {
    
    //Check if the authorization code is valid
    int authorizationCodeInt = Integer.parseInt(authorizationCode);
    Order authorization = Order.getWithNumber(authorizationCodeInt);
    if (authorization == null) {
      return "Authorization code is invalid";
    }


    //Check if the order number exists
	  int orderNumberInt = Integer.parseInt(orderNumber);
	  Order order = Order.getWithNumber(orderNumberInt);
	  if (order == null) {
		  return String.format("Order %d does not exist", orderNumberInt);
	  }

    //Check if state is correct
    try {

		  order.payOrder(authorizationCode);
		  CoolsuppliesPersistence.save();
		   
	  } catch (RuntimeException e) {
			return e.getMessage(); //Error during run time
		}
	  return "";
  }
  
  /**
 * This method pay a penalty for an order with its order number, authorization code and penalty authorization code
 * 
 * @param orderNumber the order number to be paid 
 * @param authorizationCode  the authorization code for payment
 * @param penaltyAuthorizationCode  the authorization code for the penalty payment
 * @return An error message if the operation failed or an empty string if succesfull
 */

  public static String payPenaltyOrder(String item, String penaltyAuthorizationCode, String authorizationCode) {
    
    //Check if the item number exists
	  int orderNumberInt = Integer.parseInt(item);
	  Order order = Order.getWithNumber(orderNumberInt);
	  if (order == null) {
		  return String.format("Order %d does not exist", orderNumberInt);
	  }

    //Check if the penalty authorization code is valid
    int penaltyAuthorizationCodeInt = Integer.parseInt(authorizationCode);
    Order penaltyAuthorization = Order.getWithNumber(penaltyAuthorizationCodeInt);
    if (penaltyAuthorization == null) {
      return "Penalty authorization code is invalid";
    }
    
    //Check if the authorization code is valid
    int authorizationCodeInt = Integer.parseInt(authorizationCode);
    Order authorization = Order.getWithNumber(authorizationCodeInt);
    if (authorization == null) {
      return "Authorization code is invalid";
    }

    
    try {

		  order.payPenaltyOrder(penaltyAuthorizationCode, authorizationCode);
		  CoolsuppliesPersistence.save();
		   
	  } catch (RuntimeException e) {
			return e.getMessage(); //Error during run time
		}
    return "";
  }
  
  /**
 * This method cancel an order with its order number
 * 
 * @param orderNumber the order number to cancel
 * @return An error message if the operation failed or an empty string if succesfull
 */

  public static String cancelOrder(String orderNumber) {
    //Check if the order number exists
	  int orderNumberInt = Integer.parseInt(orderNumber);
	  Order order = Order.getWithNumber(orderNumberInt);
	  if (order == null) {
		  return String.format("Order %d does not exist", orderNumberInt);
	  }
    
    
    try {

		  order.cancelOrder();
		  CoolsuppliesPersistence.save();
		   
	  } catch (RuntimeException e) {
			return e.getMessage(); //Error during run time
		}
	  return "";

  }

}