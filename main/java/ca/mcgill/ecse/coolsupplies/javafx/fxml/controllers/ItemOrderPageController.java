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

public class ItemOrderPageController {

    @FXML
    private ChoiceBox<String> selectInventoryItemChoiceBox;

    @FXML
    private ChoiceBox<TOOrder> selectOrderChoiceBox;

    @FXML
    private TextField itemQuantityTextField;

    @FXML
    private ChoiceBox<String> selectItemInOrderChoiceBox;

    /*
     * @author Doddy Yang Qiu
     */

    @FXML
    public void initialize(){
        selectInventoryItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
            selectInventoryItemChoiceBox.setItems(ViewUtils.getInventoryItems());
            selectInventoryItemChoiceBox.setValue(null);
        } );

        selectOrderChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
            selectOrderChoiceBox.setItems(ViewUtils.getStartedOrders());
            selectOrderChoiceBox.setValue(null);
        });
        selectItemInOrderChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
            selectItemInOrderChoiceBox.setItems(FXCollections.observableArrayList());
            selectItemInOrderChoiceBox.setValue(null);
        } );
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(selectInventoryItemChoiceBox, selectOrderChoiceBox);
    }

    /*
     * @author Doddy Yang Qiu
     */
    @FXML
    public void addItemToOrderClicked(ActionEvent event) {
        String item = selectInventoryItemChoiceBox.getValue();
        TOOrder order = selectOrderChoiceBox.getValue();
        int quantity;
        try {
            quantity = Integer.parseInt(itemQuantityTextField.getText());
            if(quantity<1) throw new NumberFormatException();
        }
        catch (NumberFormatException e){
            ViewUtils.showError("Please input a valid quantity.");
            return;
        }
        if(item==null || item.isEmpty()){
            ViewUtils.showError("Please select a valid item.");
        }
        else if(order==null){
            ViewUtils.showError("Please select a valid order.");
        }
        else{
            if (successful(CoolSuppliesFeatureSet8Controller.addOrderItem(item, String.valueOf(quantity), String.valueOf(order.getNumber())))){
                itemQuantityTextField.setText("");
                updateItemChoices();
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

    /*
     * @author Doddy Yang Qiu
     */
    @FXML
    public void updateItemInOrderClicked(ActionEvent event) {
        String item = selectItemInOrderChoiceBox.getValue();
        TOOrder order = selectOrderChoiceBox.getValue();
        int quantity;
        try {
            quantity = Integer.parseInt(itemQuantityTextField.getText());
        }
        catch (NumberFormatException e){
            ViewUtils.showError("Please input a valid quantity.");
            return;
        }
        if(item==null || item.isEmpty()){
            ViewUtils.showError("Please select a valid item from the order.");
        }
        else if(order==null){
            ViewUtils.showError("Please select a valid order.");
        }else{
            if (successful(CoolSuppliesFeatureSet9Controller.updateOrderItem(item, String.valueOf(quantity), String.valueOf(order.getNumber()))))
            {
                itemQuantityTextField.setText("");
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

    /*
     * @author Doddy Yang Qiu
     */
    @FXML
    public void deleteItemFromOrderClicked(ActionEvent event) {
        String item = selectItemInOrderChoiceBox.getValue();
        TOOrder order = selectOrderChoiceBox.getValue();
        if(item==null || item.isEmpty()){
            ViewUtils.showError("Please select a valid item from the order.");
        }
        else if(order==null){
            ViewUtils.showError("Please select a valid order.");
        }else{
            callController(CoolSuppliesFeatureSet9Controller.deleteOrderItem(item, String.valueOf(order.getNumber())));
        }
    }
    @FXML
    public void updateItemChoices()
    {
        TOOrder order = selectOrderChoiceBox.getValue();
        if(order != null){
            selectItemInOrderChoiceBox.setItems(ViewUtils.getInventoryItemsOfOrder(order.getNumber()));
            selectItemInOrderChoiceBox.setValue(null);
        }
    }
}
