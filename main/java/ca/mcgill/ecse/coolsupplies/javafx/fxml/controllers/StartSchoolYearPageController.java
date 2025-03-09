package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet12Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;


public class StartSchoolYearPageController {

    @FXML
    private Button startSchoolYearButton;
    @FXML
    private Label messageLabel; // Label to display the message

   /*
    * @author Moustapha El Zein
    */
    @FXML
    void startSchoolYearClicked(ActionEvent event) {
        boolean allSuccessful = true;

        for (TOOrder order : CoolSuppliesFeatureSet11Controller.viewAllOrders()) {
            if (order.getStatus()== "Started" || order.getStatus() == "Paid"){
            String orderNumber = String.valueOf(order.getNumber());
            String result = CoolSuppliesFeatureSet12Controller.startSchoolYearForOrder(orderNumber);
            CoolSuppliesFxmlView.getInstance().refresh();
            if (!result.isEmpty()) { // A non-empty result indicates a failure
                allSuccessful = false;
            }
        }
        }

        if (allSuccessful) {
            messageLabel.setText("The school year has started");
            messageLabel.setVisible(true);
        } else {
            ViewUtils.showError("Some orders failed to start the school year. Please check the logs.");
        }
    }

}
