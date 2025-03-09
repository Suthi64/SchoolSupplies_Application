package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;

import ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils;

import ca.mcgill.ecse.coolsupplies.controller.TOParent;

public class ParentDashboardController {

    @FXML
    private TableView<TOParent> parentTable;

    @FXML
    private TableColumn<TOParent, String> emailColumn;

    @FXML
    private TableColumn<TOParent, String> nameColumn;

    @FXML
    private TableColumn<TOParent, String> phoneNumberColumn;

    @FXML
    private TextField parentEmailTextField;

    @FXML
    private TextField parentPasswordTextField;

    @FXML
    private TextField parentNameTextField;

    @FXML
    private TextField parentPhoneNumberTextField;

    private ObservableList<TOParent> parentList;

    @FXML
    public void initialize() {
        // Configure Table Columns
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        refreshParentTable();

        parentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFormWithParent(newSelection);
            }
        });
    }

    private void refreshParentTable() {
        parentList = ViewUtils.getParents();
        parentTable.setItems(parentList);
    }

    private void populateFormWithParent(TOParent parent) {
        parentEmailTextField.setText(parent.getEmail());
        parentNameTextField.setText(parent.getName());
        parentPhoneNumberTextField.setText(String.valueOf(parent.getPhoneNumber()));
        parentPasswordTextField.clear();
    }

    @FXML
    public void registerParentClicked() {
        String email = parentEmailTextField.getText();
        String password = parentPasswordTextField.getText();
        String name = parentNameTextField.getText();
        String phoneNumber = parentPhoneNumberTextField.getText();

        if (ViewUtils.successful(CoolSuppliesFeatureSet1Controller.addParent(email, password, name, Integer.parseInt(phoneNumber)))) {
            clearForm();
            refreshParentTable();
        }
    }

    @FXML
    public void updateParentClicked() {
        TOParent parent = parentTable.getSelectionModel().getSelectedItem();
        if (parent != null) {
            String email = parent.getEmail();
            String newPassword = parentPasswordTextField.getText();
            String newName = parentNameTextField.getText();
            String newPhoneNumber = parentPhoneNumberTextField.getText();

            if (ViewUtils.successful(CoolSuppliesFeatureSet1Controller.updateParent(email, newPassword, newName, Integer.parseInt(newPhoneNumber)))) {
                clearForm();
                refreshParentTable();
            }
        } else {
            ViewUtils.showError("Please select a parent to update.");
        }
    }

    @FXML
    public void deleteParentClicked() {
        TOParent parent = parentTable.getSelectionModel().getSelectedItem();
        if (parent != null) {
            if (ViewUtils.successful(CoolSuppliesFeatureSet1Controller.deleteParent(parent.getEmail()))) {
                clearForm();
                refreshParentTable();
            }
        } else {
            ViewUtils.showError("Please select a parent to delete.");
        }
    }

    private void clearForm() {
        parentEmailTextField.clear();
        parentPasswordTextField.clear();
        parentNameTextField.clear();
        parentPhoneNumberTextField.clear();
    }
}
