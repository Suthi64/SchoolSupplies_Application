package ca.mcgill.ecse.coolsupplies.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.model.*;
import ca.mcgill.ecse.coolsupplies.model.Order.Status;
import io.cucumber.datatable.internal.difflib.myers.Equalizer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStepDefinitions {
  static String error = "";
  int errorCntr;
  private List<TOOrder> toOrdersList = new ArrayList<>();
  private CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  /**
   * @author Brian Yang
   */
  @Given("the following parent entities exist in the system")
  public void the_following_parent_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      int phoneNumber = Integer.parseInt(row.get("phoneNumber"));
      coolSupplies.addParent(email, password, name, phoneNumber);
    }
  }

  /**
   * @author Brian Yang
   */
  @Given("the following grade entities exist in the system")
  public void the_following_grade_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String level = row.get("level");
      coolSupplies.addGrade(level);
    }
  }

  /**
   * @author Brian Yang
   */
  @Given("the following student entities exist in the system")
  public void the_following_student_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name");
      Grade grade = Grade.getWithLevel(row.get("gradeLevel"));
      coolSupplies.addStudent(name, grade);
    }
  }

  /**
   * @author Brian Yang
   */
  @Given("the following student entities exist for a parent in the system")
  public void the_following_student_entities_exist_for_a_parent_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      Student student = Student.getWithName(row.get("name"));
      Parent parent = (Parent) User.getWithEmail(row.get("parentEmail"));
      parent.addStudent(student);
    }
  }

  @Given("the following item entities exist in the system")
  public void the_following_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /**
   * This method gets the grade bundle entities from the resources 
   * 							and adds it to the cool supplies database
   * 
   * @author Moustapha El Zein
   */
  @Given("the following grade bundle entities exist in the system")
  public void the_following_grade_bundle_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
	  
	  //Convert data table into a list of strings
	  List<Map<String, String>> rows = dataTable.asMaps();
	    
	  for (var row : rows) {
		  Grade gradeLevel = Grade.getWithLevel(row.get("gradeLevel"));
		  String name = row.get("name");
		  int discount = Integer.parseInt(row.get("discount"));
		  coolSupplies.addBundle(name, discount, gradeLevel);
		  }
  }

  @Given("the following bundle item entities exist in the system")
  public void the_following_bundle_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Given("the following order entities exist in the system")
  public void the_following_order_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> order : rows) {
      new Order(Integer.parseInt(order.get("number")), Date.valueOf(order.get("date")),
          BundleItem.PurchaseLevel.valueOf(order.get("level")),
          (Parent) Parent.getWithEmail(order.get("parentEmail")),
          Student.getWithName(order.get("studentName")), coolSupplies);
    }
  }

  @Given("the following order item entities exist in the system")
  public void the_following_order_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the order {string} is marked as {string}")
  public void the_order_is_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Doddy Yang Qiu
   */
  @When("the parent attempts to update an order with number {string} to purchase level {string} and student with name {string}")
  public void the_parent_attempts_to_update_an_order_with_number_to_purchase_level_and_student_with_name(
      String orderNumber, String purchaseLevel, String studentName) {
    error = CoolSuppliesFeatureSet8Controller.updateOrder(orderNumber, purchaseLevel, studentName);
  }

  @When("the parent attempts to add an item {string} with quantity {string} to the order {string}")
  public void the_parent_attempts_to_add_an_item_with_quantity_to_the_order(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the parent attempts to update an item {string} with quantity {string} in the order {string}")
  public void the_parent_attempts_to_update_an_item_with_quantity_in_the_order(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * This method deletes the specified item from the order
   * 
   * @param item The item to be deleted from the order
   * @param orderNumber The number of the order
   * 
   * @author Moustapha El Zein
   */
  @When("the parent attempts to delete an item {string} from the order {string}")
  public void the_parent_attempts_to_delete_an_item_from_the_order(String item, String orderNumber) {
	  callController(CoolSuppliesFeatureSet9Controller.deleteOrderItem(item, orderNumber));
  }

  /**
   * @author Doddy Yang Qiu
   */
  @When("the parent attempts to get from the system the order with number {string}")
  public void the_parent_attempts_to_get_from_the_system_the_order_with_number(String orderNumber) {
    TOOrder toOrder = CoolSuppliesFeatureSet11Controller.viewOrder(orderNumber);
    if (toOrder != null)
      toOrdersList.add(toOrder);
  }



  @When("the parent attempts to cancel the order {string}")
  public void the_parent_attempts_to_cancel_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the parent attempts to pay for the order {string} with authorization code {string}")
  public void the_parent_attempts_to_pay_for_the_order_with_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * This method starts the school year for a specified order
   * 
   * @param orderNumber The number of the order
   * 
   * @author Moustapha El Zein
   */
  @When("the admin attempts to start a school year for the order {string}")
  public void the_admin_attempts_to_start_a_school_year_for_the_order(String orderNumber) {
	  callController(CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber));
  }

  @When("the parent attempts to pay penalty for the order {string} with penalty authorization code {string} and authorization code {string}")
  public void the_parent_attempts_to_pay_penalty_for_the_order_with_penalty_authorization_code_and_authorization_code(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the student attempts to pickup the order {string}")
  public void the_student_attempts_to_pickup_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the school admin attempts to get from the system all orders")
  public void the_school_admin_attempts_to_get_from_the_system_all_orders() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall contain penalty authorization code {string}")
  public void the_order_shall_contain_penalty_authorization_code(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall not contain penalty authorization code {string}")
  public void the_order_shall_not_contain_penalty_authorization_code(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param orderNum This string represents the number of the order
   * @param authorizationCode This string represents an authorization code
   * @author Baptiste Didier
   */
  @Then("the order {string} shall not contain authorization code {string}")
  public void the_order_shall_not_contain_authorization_code(String orderNum, String authorizationCode) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    boolean hasAuthorizationCode = order.getAuthorizationCode().equals(authorizationCode);
    assertFalse(hasAuthorizationCode);
  }

  @Then("the order {string} shall not exist in the system")
  public void the_order_shall_not_exist_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param orderNum This string represents the number of the order
   * @param authorizationCode This string represents an authorization code
   * @author Baptiste Didier
   */
  @Then("the order {string} shall contain authorization code {string}")
  public void the_order_shall_contain_authorization_code(String orderNum, String authorizationCode) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    boolean hasAuthorizationCode = order.getAuthorizationCode().equals(authorizationCode);
    assertTrue(hasAuthorizationCode);
  }

  @Then("the order {string} shall contain {string} item")
  public void the_order_shall_contain_item(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param orderNum This string represents the number of the order
   * @param itemName This string represents the name of an item
   * @author Baptiste Didier
   */
  @Then("the order {string} shall not contain {string}")
  public void the_order_shall_not_contain(String orderNum, String itemName) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    Item item = (Item) InventoryItem.getWithName(itemName);
    for (OrderItem orderItem : order.getOrderItems()) {
      assertFalse(orderItem.getItem().equals(item));
    }
  }



  @Then("the number of order items in the system shall be {string}")
  public void the_number_of_order_items_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall contain {string} items")
  public void the_order_shall_contain_items(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @param orderNum This string represents the number of the order
   * @param itemName This string represents the name of an item
   * @param quantity This string represents the quantity of the item
   * @author Baptiste Didier
   */
  @Then("the order {string} shall not contain {string} with quantity {string}")
  public void the_order_shall_not_contain_with_quantity(String orderNum, String itemName, String quantity) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    Item item = (Item) InventoryItem.getWithName(itemName);
    int number = Integer.parseInt(quantity);
    for (OrderItem orderItem : order.getOrderItems()) {
      if (orderItem.getItem().equals(item)) {
        assertNotEquals(orderItem.getQuantity(), number);
      }
    }
  }


  @Then("the order {string} shall contain {string} with quantity {string}")
  public void the_order_shall_contain_with_quantity(String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  /**
   * @param orderNum This string represents the number of the order
   * @param statusName This string represents the status of the order
   * @author Baptiste Didier
   */
  @Then("the order {string} shall be marked as {string}")
  public void the_order_shall_be_marked_as(String orderNum, String statusName) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    Status status = Order.Status.valueOf(statusName);
    assertEquals(order.getStatus(), status);
  }


  /**
   * This method checks if the actual number of orders is equals 
   * 									to the expected number of orders
   * 
   * @param numOfOrders The number of orders in the cool supplies database
   * 
   * @author Moustapha El Zein
   */
  @Then("the number of orders in the system shall be {string}")
  public void the_number_of_orders_in_the_system_shall_be(String numOfOrders) {
	  int number = Integer.parseInt(numOfOrders);
	  assertEquals(number, coolSupplies.getOrders().size());
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Then("the order {string} shall contain level {string} and student {string}")
  public void the_order_shall_contain_level_and_student(String orderNumber, String level,
      String student) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    assertNotNull(order, "Could not find order " + orderNumber + " using Order.getWithNumber");
    assertEquals(order.getLevel().name(), level, "Expected Purchase Level: " + level
        + "\nActual Purchase Level: " + order.getLevel().name());
    assertEquals(order.getStudent().getName(), student,
        "Expected Student: " + student + "\nActual Student: " + order.getStudent().getName());
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Given("order {string} is marked as {string}")
  public void order_is_marked_as(String orderNumber, String level) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    if (order != null)
      order.setLevel(BundleItem.PurchaseLevel.valueOf(level));
  }

  /**
   * @param errorMessage The string represents the received error message 
   * @author Baptiste Didier
   */
  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String errorMessage) {
    assertTrue(error.contains(errorMessage), "Expected error message '" 
    + errorMessage + "' not found in: " + error);
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Then("the following order entities shall be presented")
  public void the_following_order_entities_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    assertEquals(rows.size(), toOrdersList.size(),
        "Expected toOrdersList.size(): " + (rows.size()) + "\nActual size: " + toOrdersList.size());
    for (Map<String, String> toOrder : rows) {
      TOOrder myTOOrder = null;
      for (TOOrder order : toOrdersList) {
        if (Objects.equals(toOrder.get("parentEmail"), order.getParentEmail())
            && Objects.equals(toOrder.get("studentName"), order.getStudentName())
            && Objects.equals(toOrder.get("status"), order.getStatus())
            && Objects.equals(toOrder.get("number"), order.getNumber())
            && Objects.equals(Date.valueOf(toOrder.get("date")), order.getDate())
            && Objects.equals(toOrder.get("level"), order.getLevel())
            && Objects.equals(toOrder.get("authorizationCode"), order.getAuthorizationCode())
            && Objects.equals(toOrder.get("penaltyAuthorizationCode"),
                order.getPenaltyAuthorizationCode())
            && Objects.equals(Double.parseDouble(toOrder.get("totalPrice")),
                order.getTotalPrice())) {
          myTOOrder = order;
        }
      }
      assertNotNull(myTOOrder, "Could not find TOOrder in toOrdersList with" + "\nparentEmail: "
          + toOrder.get("parentEmail") + "\nstudentName: " + toOrder.get("studentName")
          + "\nstatus: " + toOrder.get("status") + "\nnumber: " + toOrder.get("number") + "\ndate: "
          + toOrder.get("date") + "\nlevel: " + toOrder.get("level") + "\nauthorizationCode: "
          + toOrder.get("authorizationCode") + "\npenaltyAuthorizationCode: "
          + toOrder.get("penaltyAuthorizationCode") + "\ntotalPrice: " + toOrder.get("totalPrice"));
    }

  }

  /**
   * @author Doddy Yang Qiu
   */
  @Then("the following order items shall be presented for the order with number {string}")
  public void the_following_order_items_shall_be_presented_for_the_order_with_number(
      String orderNumber, io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    // Find the specified TOOrder
    TOOrder myOrder = null;
    for (TOOrder order : toOrdersList)
      if (order.getNumber().equals(orderNumber))
        myOrder = order;
    assertNotNull(myOrder, "Could not find order with number " + orderNumber + " in toOrdersList");

    // Compare the number of OrderItems
    assertEquals(rows.size(), myOrder.numberOfOrderItems(), "Expected number of order Items: "
        + (rows.size()) + "\nActual number: " + myOrder.numberOfOrderItems());
    for (Map<String, String> orderItem : rows) {
      TOOrderItem myItem = null;
      for (TOOrderItem item : myOrder.getOrderItems()) {
        if (item.getQuantity() == Integer.parseInt(orderItem.get("quantity"))
            && item.getItemName().equals(orderItem.get("itemName"))
            && item.getGradeBundleName().equals(orderItem.get("gradeBundleName"))
            && item.getPrice() == Integer.parseInt(orderItem.get("price"))
            && ((orderItem.get("discount").isEmpty() && item.getDiscount() == 0)
                || item.getDiscount() == Double.parseDouble(orderItem.get("discount")))) {
          myItem = item;
        }
      }
      assertNotNull(myItem,
          "Could not find TOOrderItem with" + "\nquantity: " + orderItem.get("quantity")
              + "\nitemName: " + orderItem.get("itemName") + "\ngradeBundleName: "
              + orderItem.get("gradeBundleName") + "\nprice: " + orderItem.get("price")
              + "\ndiscount: " + orderItem.get("discount"));
    }
  }

  @Then("no order entities shall be presented")
  public void no_order_entities_shall_be_presented() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  
  
  /** Calls controller and sets error and error counter **/
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
      errorCntr += 1;
    }
  }

}
