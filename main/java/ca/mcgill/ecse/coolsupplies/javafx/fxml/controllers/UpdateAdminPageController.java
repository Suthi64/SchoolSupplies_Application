package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UpdateAdminPageController {

    @FXML
    private TextField adminEmailField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    void updateAdminPasswordClicked(ActionEvent event) {
      String newPassword = newPasswordField.getText();
      if (newPassword == null || newPassword.trim().isEmpty()){
        ViewUtils.showError("Please input a valid password.");
      }
      else {
        if (successful(CoolSuppliesFeatureSet1Controller.updateAdmin(newPassword))) {
                newPasswordField.clear();
                CoolSuppliesFxmlView.getInstance().refresh();
      }
    }

}

}