package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet12Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

public class PickUpOrderPageController {

  @FXML
  private Button pickUpSelectedOrderButton;

  @FXML
  private ChoiceBox<TOOrder> OrderChoiceBox;



  
  /**
   * Initializes the pick up order page controller by setting up the OrderChoiceBox.
   * It adds an event handler to update the order list and clears the selection
   * when a refresh event is triggered. It also registers the OrderChoiceBox to
   * receive refresh events from the CoolSuppliesFxmlView.
   * @author  Jiaduo Xing
   */
  @FXML
  public void initialize() {
    OrderChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
      OrderChoiceBox.setItems(ViewUtils.getOrders());
      OrderChoiceBox.setValue(null);
    });


    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(OrderChoiceBox);
  }



  
/**
 * Handles the pick up selected order button click event. 
 * Retrieves the selected order from the UI and checks if it is ready.
 * If the order is not selected or not ready, displays an error message.
 * If the order is ready, calls the pickUpOrder method of the CoolSuppliesFeatureSet12Controller.
 * If the order is successfully picked up, refreshes the UI.
 * @author Jiaduo Xing
 * 
 * @param event The action event triggered by the button click.
 * @since 1.0
 */
  @FXML
  void pickUpSelectedOrderClicked(ActionEvent event) {
    TOOrder order = OrderChoiceBox.getValue();
    if (order == null) {
      ViewUtils.showError("Please select an order.");
    } else if (!order.getStatus().equals("Prepared")) {
      ViewUtils.showError("This order is not ready.");
    } else {
      if (successful(
          CoolSuppliesFeatureSet12Controller.pickUpOrder(String.valueOf(order.getNumber())))) {
        CoolSuppliesFxmlView.getInstance().refresh();
      }

    }

  }
}
