package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet10Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * This class handles the payment of orders
 * @author Baptiste Didier
 */
public class PayOrderPageController {

    @FXML
    private TextField authCodeTextField;

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private Button payForOrderButton;

    @FXML
    private Button payPenalityForOrderButton;

    @FXML
    private TextField penAuthCodeTextField;

    /**
     * Updates the order based on its current state and the authorization code
     * @param event The click of the user
     */
    @FXML
    void PayForOrderClicked(ActionEvent event) {
      String orderNum = orderNumberTextField.getText();
      String auth = authCodeTextField.getText();
      TOOrder order = CoolSuppliesFeatureSet11Controller.viewOrder(orderNum);

      if (order == null) {
        ViewUtils.showError("Please enter a valid order number.");
        return;
      }

      if (auth == null || auth.trim().isEmpty()) {
        ViewUtils.showError("Please enter a valid authorization code.");
        return;
      }

      if (successful(CoolSuppliesFeatureSet10Controller.payOrder(orderNum, auth))) {
        authCodeTextField.setText("");
        orderNumberTextField.setText("");
      }
    }

    /**
     * Updates the order based on its current state and the authorization codes
     * @param event The click of the user
     */
    @FXML
    void payPenalityClicked(ActionEvent event) {
      String orderNum = orderNumberTextField.getText();
      String auth = authCodeTextField.getText();
      String penAuth = penAuthCodeTextField.getText();
      TOOrder order = CoolSuppliesFeatureSet11Controller.viewOrder(orderNum);

      if (order == null) {
        ViewUtils.showError("Please enter a valid order number.");
        return;
      }

      if (auth == null || auth.trim().isEmpty()) {
        ViewUtils.showError("Please enter a valid authorization code.");
        return;
      }

      if (penAuth == null || auth.trim().isEmpty()) {
        ViewUtils.showError("Please enter a valid penality authorization code.");
        return;
      }

      if (successful(CoolSuppliesFeatureSet10Controller.payPenaltyOrder(orderNum, auth, penAuth))) {
        authCodeTextField.setText("");
        penAuthCodeTextField.setText("");
        orderNumberTextField.setText("");
      }
    }

}
