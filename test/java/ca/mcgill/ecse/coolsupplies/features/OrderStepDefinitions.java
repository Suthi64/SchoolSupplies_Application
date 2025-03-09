package ca.mcgill.ecse.coolsupplies.features;

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
  int errorCntr = 0;
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

  /**
   * @author Jiaduo Xing
   */
  @Given("the following item entities exist in the system")
  public void the_following_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String itemName = row.get("name");
      int price = Integer.parseInt(row.get("price"));
      coolSupplies.addItem(itemName, price);

    }
  }

  /**
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

  /*
   * @author Sanad Abu Baker
   */
  @Given("the following bundle item entities exist in the system")
  public void the_following_bundle_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        for (Map<String, String> bundleItems : rows) {
          GradeBundle bundle = (GradeBundle) InventoryItem.getWithName(bundleItems.get("gradeBundleName"));
          Item item = (Item) InventoryItem.getWithName(bundleItems.get("itemName"));
          coolSupplies.addBundleItem(Integer.parseInt(bundleItems.get("quantity")),
          BundleItem.PurchaseLevel.valueOf(bundleItems.get("level")),
          bundle, item);
        }
  }

  /**
   * @author Doddy Yang Qiu
   */
  @Given("the following order entities exist in the system")
  public void the_following_order_entities_exist_in_the_system(
          io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> order : rows) {
      coolSupplies.addOrder(Integer.parseInt(order.get("number")), Date.valueOf(order.get("date")),
              BundleItem.PurchaseLevel.valueOf(order.get("level")),
              (Parent) Parent.getWithEmail(order.get("parentEmail")),
              Student.getWithName(order.get("studentName")));

      if(order.containsKey("status")){
        if(Status.valueOf(order.get("status")).equals(Status.Paid)){
          Order aOrder = Order.getWithNumber(Integer.parseInt(order.get("number")));
          aOrder.payOrder(order.get("authorizationCode"));
        }
        else if(Status.valueOf(order.get("status")).equals(Status.Penalized)){
          CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(order.get("number"));
        }
        else if(Status.valueOf(order.get("status")).equals(Status.Prepared)){
          if (order.get("penaltyAuthorizationCode") == null) {
            Order aOrder = Order.getWithNumber(Integer.parseInt(order.get("number")));
            aOrder.payOrder(order.get("authorizationCode"));
            CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(order.get("number"));
          }
          else {
            CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(order.get("number"));
            CoolSuppliesFeatureSet10Controller.payPenaltyOrder(order.get("number"), order.get("penaltyAuthorizationCode"), order.get("authorizationCode"));
          }
        }
        else if(Status.valueOf(order.get("status")).equals(Status.PickedUp)){
          if (order.get("penaltyAuthorizationCode") == null) {
            Order aOrder = Order.getWithNumber(Integer.parseInt(order.get("number")));
            aOrder.payOrder(order.get("authorizationCode"));
            CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(order.get("number"));
          }
          else {
            CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(order.get("number"));
            CoolSuppliesFeatureSet10Controller.payPenaltyOrder(order.get("number"), order.get("penaltyAuthorizationCode"), order.get("authorizationCode"));
          }
          CoolSuppliesFeatureSet12Controller.pickUpOrder(order.get("number"));
        }
      }
    }
  }

  /**
   * @author Jiaduo Xing
   */
  @Given("the following order item entities exist in the system")
  public void the_following_order_item_entities_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();

    for (Map<String, String> orderItem : rows) {
      int myQuantity = Integer.parseInt(orderItem.get("quantity"));
      Order myOrder = Order.getWithNumber(Integer.parseInt(orderItem.get("orderNumber"))); 
      InventoryItem myInventoryItem = InventoryItem.getWithName(orderItem.get("itemName"));
      coolSupplies.addOrderItem(myQuantity, myOrder, myInventoryItem);
    }
  }
  
  /*
   * @author Sanad Abu Baker
   */
  @Given("the order {string} is marked as {string}")
  public void the_order_is_marked_as(String orderNumber, String statusName) {
    Status orderStatus = Order.getWithNumber(Integer.parseInt(orderNumber)).getStatus();
    Status expectedStatus = Order.Status.valueOf(statusName);
    if (orderStatus.equals(expectedStatus)) {
      return;
    }
    else if (statusName.equals("Paid")){
      Order aOrder = Order.getWithNumber(Integer.parseInt(orderNumber));
      aOrder.payOrder("1111");
    }
    else if (statusName.equals("Penalized")){
      CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber);
    }
    else if (statusName.equals("Prepared")){
      Order aOrder = Order.getWithNumber(Integer.parseInt(orderNumber));
      aOrder.payOrder("1111");
      CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber);
    }
    else if (statusName.equals("PickedUp")){
      Order aOrder = Order.getWithNumber(Integer.parseInt(orderNumber));
      aOrder.payOrder("1111");
      CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber);
      CoolSuppliesFeatureSet12Controller.pickUpOrder(orderNumber);
    }
  }

  /**
   * @author Doddy Yang Qiu
   */
  @When("the parent attempts to update an order with number {string} to purchase level {string} and student with name {string}")
  public void the_parent_attempts_to_update_an_order_with_number_to_purchase_level_and_student_with_name(
      String orderNumber, String purchaseLevel, String studentName) {
    callController(CoolSuppliesFeatureSet8Controller.updateOrder(orderNumber, purchaseLevel, studentName));
  }

  /**
   * @author Jiaduo Xing
   */
  @When("the parent attempts to add an item {string} with quantity {string} to the order {string}")
  public void the_parent_attempts_to_add_an_item_with_quantity_to_the_order(String item, String quantity, String orderNumber) {
    callController(CoolSuppliesFeatureSet8Controller.addOrderItem(item,quantity,orderNumber));
  }
/**
   * @author Jiaduo Xing
   */
  @When("the parent attempts to update an item {string} with quantity {string} in the order {string}")
  public void the_parent_attempts_to_update_an_item_with_quantity_in_the_order(String item, String quantity, String orderNumber) {
    callController(CoolSuppliesFeatureSet9Controller.updateOrderItem(item, quantity, orderNumber));
  }

  /**
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

  /**
   * @author Suthiesan Subramaniam
   */

  @When("the parent attempts to cancel the order {string}")
  public void the_parent_attempts_to_cancel_the_order(String orderNumber) {
    callController(CoolSuppliesFeatureSet10Controller.cancelOrder(orderNumber));
  }

  /**
   * @author Suthiesan Subramaniam
   */

  @When("the parent attempts to pay for the order {string} with authorization code {string}")
  public void the_parent_attempts_to_pay_for_the_order_with_authorization_code(String orderNumber,
      String authorizationCode) {
        callController(CoolSuppliesFeatureSet10Controller.payOrder(orderNumber, authorizationCode));
  }

  /**
   * @author Moustapha El Zein
   */
  @When("the admin attempts to start a school year for the order {string}")
  public void the_admin_attempts_to_start_a_school_year_for_the_order(String orderNumber) {
	  callController(CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber));
  }

  /**
   * @author Suthiesan Subramaniam
   */

  @When("the parent attempts to pay penalty for the order {string} with penalty authorization code {string} and authorization code {string}")
  public void the_parent_attempts_to_pay_penalty_for_the_order_with_penalty_authorization_code_and_authorization_code(
      String orderNumber, String penaltyAuthorizationCode, String authorizationCode) {
    callController(CoolSuppliesFeatureSet10Controller.payPenaltyOrder(orderNumber, penaltyAuthorizationCode, authorizationCode));
  }

  /*
   * @author Sanad Abu Baker
   */
  @When("the student attempts to pickup the order {string}")
  public void the_student_attempts_to_pickup_the_order(String orderNumber) {
    callController(CoolSuppliesFeatureSet12Controller.pickUpOrder(orderNumber));
  }

  /*
   * @author Sanad Abu Baker
   */
  @When("the school admin attempts to get from the system all orders")
  public void the_school_admin_attempts_to_get_from_the_system_all_orders() {
    toOrdersList = CoolSuppliesFeatureSet11Controller.viewAllOrders();
  }

  /**
   * @author Suthiesan Subramaniam
   */
  @Then("the order {string} shall contain penalty authorization code {string}")
  public void the_order_shall_contain_penalty_authorization_code(String orderNum, String penaltyAuthorizationCode) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    String orderCode = order.getPenaltyAuthorizationCode();
    assertEquals(penaltyAuthorizationCode, orderCode, "Authorization code should be '" 
    + penaltyAuthorizationCode + "' but was '" + orderCode + "'");
  }

  /**
   * @author Suthiesan Subramaniam
   */
  @Then("the order {string} shall not contain penalty authorization code {string}")
  public void the_order_shall_not_contain_penalty_authorization_code(String orderNum,
      String penaltyAuthorizationCode) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    String orderCode = order.getPenaltyAuthorizationCode();
    assertNotEquals(penaltyAuthorizationCode, orderCode, "Authorization code should be '" 
    + penaltyAuthorizationCode + "' but was '" + orderCode + "'");
  }

  /**
   * @author Baptiste Didier
   */
  @Then("the order {string} shall not contain authorization code {string}")
  public void the_order_shall_not_contain_authorization_code(String orderNum, String authorizationCode) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    String orderCode = order.getAuthorizationCode();
    assertNotEquals(authorizationCode, orderCode, "Authorization code should not be '" 
    + authorizationCode + "'");

  }

  /*
   * @author Sanad Abu Baker
   */
  @Then("the order {string} shall not exist in the system")
  public void the_order_shall_not_exist_in_the_system(String orderNumber) {
    assertNull(Order.getWithNumber(Integer.parseInt(orderNumber)));
  }

  /**
   * @author Baptiste Didier
   */
  @Then("the order {string} shall contain authorization code {string}")
  public void the_order_shall_contain_authorization_code(String orderNum, String authorizationCode) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    String orderCode = order.getAuthorizationCode();
    assertEquals(authorizationCode, orderCode, "Authorization code should be '" 
    + authorizationCode + "' but was '" + orderCode + "'");
  }

  /**
   * @author Jiaduo Xing
   */
  @Then("the order {string} shall contain {string} item")
  public void the_order_shall_contain_item(String orderNumber, String expectedQuantity) {
    int actualQuantity = Order.getWithNumber(Integer.parseInt(orderNumber)).getOrderItems().size();
    assertEquals(Integer.parseInt(expectedQuantity), actualQuantity);
  }

  /**
   * @author Baptiste Didier
   */
  @Then("the order {string} shall not contain {string}")
  public void the_order_shall_not_contain(String orderNum, String itemName) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    InventoryItem item = InventoryItem.getWithName(itemName);
    for (OrderItem orderItem : order.getOrderItems()) {
      assertNotEquals(item, orderItem.getItem(), "The order '"
      + orderNum + "' contains '" + itemName + "'");
    }
  }

  /**
   * @author Jiaduo Xing
   */
  @Then("the number of order items in the system shall be {string}")
  public void the_number_of_order_items_in_the_system_shall_be(String expectedCount) {
    int actualCount = coolSupplies.getOrderItems().size();
    assertEquals(Integer.parseInt(expectedCount), actualCount);
  }

/**
   * @author Jiaduo Xing
   */
  @Then("the order {string} shall contain {string} items")
  public void the_order_shall_contain_items(String orderNumber, String expectedQuantity) {
    int actualQuantity = Order.getWithNumber(Integer.parseInt(orderNumber)).getOrderItems().size();
    assertEquals(Integer.parseInt(expectedQuantity), actualQuantity);
  }

  /**
   * @author Baptiste Didier
   */
  @Then("the order {string} shall not contain {string} with quantity {string}")
  public void the_order_shall_not_contain_with_quantity(String orderNum, String itemName, String quantity) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    InventoryItem item = InventoryItem.getWithName(itemName);
    int number = Integer.parseInt(quantity);
    for (OrderItem orderItem : order.getOrderItems()) {
      if (orderItem.getItem().equals(item)) {
        assertNotEquals(orderItem.getQuantity(), number, "The order '" + orderNum +
        "' contains '" + itemName + "' with quantity '" + quantity + "'");
      }
    }
  }

  /*
   * @author Sanad Abu Baker
   */
  @Then("the order {string} shall contain {string} with quantity {string}")
  public void the_order_shall_contain_with_quantity(String orderNumber, String itemName, String quantity) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNumber));
    assertNotNull(order, "Could not find order " + orderNumber + " using Order.getWithNumber");

    InventoryItem item = InventoryItem.getWithName(itemName);
    for (OrderItem orderItem : order.getOrderItems()){
      if(orderItem.getItem().equals(item)){
        assertEquals(Integer.parseInt(quantity), orderItem.getQuantity(), "Expected '" 
        + Integer.parseInt(quantity) + "' '" + itemName + "' but was '"
        + orderItem.getQuantity() + "' '" + orderItem.getItem().getName() + "'\n");
      }
    }
  }

  /**
   * @author Baptiste Didier
   */
  @Then("the order {string} shall be marked as {string}")
  public void the_order_shall_be_marked_as(String orderNum, String status) {
    Order order = Order.getWithNumber(Integer.parseInt(orderNum));
    assertEquals(order.getStatusFullName(), status, "The order '" + orderNum + 
    "' was marked as '" + order.getStatusFullName() + "' but expected '" + status + "'");
  }

  /**
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
    assertEquals(level, order.getLevel().name(), "Expected Purchase Level: " + level
        + "\nActual Purchase Level: " + order.getLevel().name());
    assertEquals(student, order.getStudent().getName(),
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
    boolean found = false;
    for (Map<String, String> toOrder : rows) {
      for (TOOrder order : toOrdersList) {
        if (Objects.equals(toOrder.get("parentEmail"), order.getParentEmail())
                && Objects.equals(toOrder.get("studentName"), order.getStudentName())
                && Objects.equals(toOrder.get("status"), order.getStatus())
                && Objects.equals(toOrder.get("number"), String.valueOf(order.getNumber()))
                && Objects.equals(Date.valueOf(toOrder.get("date")), order.getDate())
                && Objects.equals(toOrder.get("level"), order.getLevel())
                && Objects.equals(toOrder.get("authorizationCode"), order.getAuthorizationCode())
                && Objects.equals(toOrder.get("penaltyAuthorizationCode"),
                order.getPenaltyAuthorizationCode())
                && Objects.equals(Double.parseDouble(toOrder.get("totalPrice")),
                order.getTotalPrice())) {
          found = true;
          break;
        }
      }
      assertTrue(found, "Could not find TOOrder in toOrdersList with" + "\nparentEmail: "
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
      if (String.valueOf(order.getNumber()).equals(orderNumber))
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
                && (item.getGradeBundleName().equals(orderItem.get("gradeBundleName")) || (item.getGradeBundleName().isEmpty() && orderItem.get("gradeBundleName") == null))
                && item.getPrice() == Integer.parseInt(orderItem.get("price"))
                && ((orderItem.get("discount") == null && item.getDiscount() == 0)
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

  /*
   * @author  Sanad Abu baker
   */
  @Then("no order entities shall be presented")
  public void no_order_entities_shall_be_presented() {
    assertTrue(toOrdersList.isEmpty(), "Expected no order entities, but the list is not empty)");
  }

  /** Calls controller and sets error and error counter **/
  private void callController(String result) {
    if (!result.isEmpty()) {
      error += result;
      errorCntr += 1;
    }
  }

}
