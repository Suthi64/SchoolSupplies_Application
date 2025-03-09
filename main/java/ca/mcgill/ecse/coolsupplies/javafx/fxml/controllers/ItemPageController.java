package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet3Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;

public class ItemPageController {

    @FXML
    private Button addItemButton;

    @FXML
    private Button updateItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private TableView<TOItem> itemTable;

    @FXML
    private TableColumn<TOItem, String> itemNameColumn;

    @FXML
    private TableColumn<TOItem, Integer> itemPriceColumn;

    @FXML
    private TextField itemNameTextField;

    @FXML
    private TextField itemPriceTextField;

    /**
     * Initializes the item page controller by setting up the TableView and refreshing the item list.
     */
    @FXML
    public void initialize() {
        // Set up TableView columns
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        // Refresh item list
        itemTable.setItems(ViewUtils.getItems());

        // Register refresh event for TableView and other UI updates
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(itemTable);
    }

    /**
     * Handles the add item button click event. Retrieves item details from the UI and calls the addItem method.
     */
    @FXML
    void addItemClicked(ActionEvent event) {
        String itemName = itemNameTextField.getText();
        String itemPrice = itemPriceTextField.getText();

        if (itemName == null || itemName.trim().isEmpty()) {
            ViewUtils.showError("Please input a valid item name.");
        } else if (itemPrice == null || itemPrice.trim().isEmpty() || !itemPrice.matches("[0-9]+(\\.[0-9]+)?")) {
            ViewUtils.showError("Please input a valid item price.");
        } else {
            if (successful(CoolSuppliesFeatureSet3Controller.addItem(itemName, Integer.parseInt(itemPrice)))) {
                clearFields();
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

    /**
     * Handles the delete item button click event. Deletes the selected item from the list.
     */
    @FXML
    void deleteItemClicked(ActionEvent event) {
        TOItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            ViewUtils.showError("Please select an item to delete.");
        } else {
            if (successful(CoolSuppliesFeatureSet3Controller.deleteItem(selectedItem.getName()))) {
                clearFields();
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

    /**
     * Handles the update item button click event. Updates the selected item with new details.
     */
    @FXML
    void updateItemClicked(ActionEvent event) {
        TOItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
        String newName = itemNameTextField.getText();
        String newPrice = itemPriceTextField.getText();

        if (selectedItem == null) {
            ViewUtils.showError("Please select an item to update.");
        } else if (newName == null || newName.trim().isEmpty()) {
            ViewUtils.showError("Please input a valid item name.");
        } else if (newPrice == null || newPrice.trim().isEmpty() || !newPrice.matches("[0-9]+(\\.[0-9]+)?")) {
            ViewUtils.showError("Please input a valid item price.");
        } else {
            if (successful(CoolSuppliesFeatureSet3Controller.updateItem(selectedItem.getName(), newName, Integer.parseInt(newPrice)))) {
                clearFields();
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

    /**
     * Clears the input fields after an operation.
     */
    private void clearFields() {
        itemNameTextField.clear();
        itemPriceTextField.clear();
    }
}

