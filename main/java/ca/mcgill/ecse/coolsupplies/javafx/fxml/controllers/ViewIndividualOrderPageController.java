package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import java.util.List;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;

/**
 * This class handles the View Controller for the viewing of individual orders.
 * @author Baptiste Didier
 */
public class ViewIndividualOrderPageController {

    @FXML
    private TableView<TOOrder> orderInformationsTable;

    @FXML
    private ChoiceBox<TOOrder> orderInput;

    @FXML
    private TableView<TOOrderItem> orderSummaryTable;

    @FXML
    private Button submitInput;

    /**
     * Show the detail of a selected order
     */
    @FXML
    void showOrder(ActionEvent event) {
        TOOrder order = orderInput.getValue();

        orderInformationsTable.setItems(FXCollections.observableArrayList(order));
        List<TOOrderItem> orderItems = order.getOrderItems();

        if (orderItems == null || orderItems.isEmpty()) {
            orderSummaryTable.setItems(FXCollections.observableArrayList());
        }
        else {
            orderSummaryTable.setItems(FXCollections.observableArrayList(orderItems));
        }
    }

    /**
     * Initializes the user interface for viewing orders
     */
    @FXML
    private void initialize() {
        orderInput.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
            orderInput.setItems(ViewUtils.getOrders());
            orderInput.setValue(null);
          });

        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderInput);
        
        orderInformationsTable.getColumns().add(createOrderTableColumn("Parent", "parentEmail"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Student", "studentName"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Status", "status"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Number", "number"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Date", "date"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Purchase Level","level" ));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Authorization Code", "authorizationCode"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Penalty Authorization Code", "penaltyAuthorizationCode"));
        orderInformationsTable.getColumns().add(createOrderTableColumn("Total Price", "totalPrice"));

        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Name", "itemName"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Quantity", "quantity"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Price", "price"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Item Discount", "discount"));
        orderSummaryTable.getColumns().add(createOrderItemTableColumn("Bundle Name", "gradeBundleName"));
        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderInput);
    }

    /**
     * Creates a table column in the order informations
     */
    public static TableColumn<TOOrder, String> createOrderTableColumn(String header, String propertyName) {
      TableColumn<TOOrder, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }

    /**
     * Creates a table column in the order summary
     */
    public static TableColumn<TOOrderItem, String> createOrderItemTableColumn(String header, String propertyName) {
        TableColumn<TOOrderItem, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
      }

}
