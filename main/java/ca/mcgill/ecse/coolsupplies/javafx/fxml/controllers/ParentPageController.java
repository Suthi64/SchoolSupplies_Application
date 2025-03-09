package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ParentPageController {

    @FXML
    private TextField parentEmailTextField;

    @FXML
    private TextField parentNameTextField;

    @FXML
    private TextField parentPasswordTextField;

    @FXML
    private TextField parentPhoneNumberTextField;

    @FXML
    private ChoiceBox<TOParent> selectParentChoiceBox;

    @FXML
    public void initialize(){
      selectParentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
        selectParentChoiceBox.setItems(ViewUtils.getParents());
        selectParentChoiceBox.setValue(null);
      });

      CoolSuppliesFxmlView.getInstance().registerRefreshEvent(
        selectParentChoiceBox, 
        parentEmailTextField, 
        parentPasswordTextField, 
        parentNameTextField, 
        parentPhoneNumberTextField
    );

    }

    @FXML
    void registerParentClicked(ActionEvent event) {
      String email = parentEmailTextField.getText();
      String password = parentPasswordTextField.getText();
      String name = parentNameTextField.getText();
      String phoneNumber = parentPhoneNumberTextField.getText();

      if (email == null || email.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid email address.");
      } else if (password == null || password.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid password.");
      } else if (name == null || name.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid name.");
      } else if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
          ViewUtils.showError("Please input a valid phone number.");
      } else {
        if (successful(CoolSuppliesFeatureSet1Controller.addParent(email, password, name, Integer.parseInt(phoneNumber)))) {
            parentEmailTextField.setText("");
            parentPasswordTextField.setText("");
            parentNameTextField.setText("");
            parentPhoneNumberTextField.setText("");
            CoolSuppliesFxmlView.getInstance().refresh();
          }
    }
    }

    @FXML
    public void deleteParentClicked(ActionEvent event) {
      TOParent parent = selectParentChoiceBox.getValue();
      if (parent==null){
        ViewUtils.showError("Please select a valid parent.");
      } else{
        callController(CoolSuppliesFeatureSet1Controller.deleteParent(parent.getEmail()));
      }
    }

    @FXML
    void updateParentClicked(ActionEvent event) {
      TOParent parent = selectParentChoiceBox.getValue();
      String email = parentEmailTextField.getText();
      String newPassword = parentPasswordTextField.getText();
      String newName = parentNameTextField.getText();
      String newPhoneNumber = parentPhoneNumberTextField.getText();

      if(parent == null){
        ViewUtils.showError("Please select a valid parent.");
      } else if (newPassword == null || newPassword.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid password.");
      } else if (newName == null || newName.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid name.");
      } else if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid phone number.");
      } else {
        if (successful(CoolSuppliesFeatureSet1Controller.updateParent(email, newPassword, newName, Integer.parseInt(newPhoneNumber)))) {
          parentEmailTextField.setText("");  
          parentPasswordTextField.setText("");
          parentNameTextField.setText("");
          parentPhoneNumberTextField.setText("");
          CoolSuppliesFxmlView.getInstance().refresh();
        }
      }
    }

}