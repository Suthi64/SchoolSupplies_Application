package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.getOrders;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAllOrdersPageController {

    @FXML
    private TableView<TOOrder> orderTable;

   /* 
    * @author Brian Yang 
    */
    @FXML
    public void initialize(){
      orderTable.getColumns().add(createTableColumn("Status", "status"));
      orderTable.getColumns().add(createTableColumn("id", "number"));
      orderTable.getColumns().add(createTableColumn("Parent", "parentEmail"));
      orderTable.getColumns().add(createTableColumn("Student", "studentName"));
      orderTable.getColumns().add(createTableColumn("Purchase Level","level" ));
      orderTable.getColumns().add(createTableColumn("Date", "date"));
      orderTable.getColumns().add(createTableColumn("Total Price", "totalPrice"));
      //
      orderTable.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> orderTable.setItems(getOrders()));
      //
      CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderTable);
    }

   /* 
    * @author Brian Yang 
    */
    public static TableColumn<TOOrder, String> createTableColumn(String header,
    String propertyName) 
    {
      TableColumn<TOOrder, String> column = new TableColumn<>(header);
      column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
      return column;
    }
}