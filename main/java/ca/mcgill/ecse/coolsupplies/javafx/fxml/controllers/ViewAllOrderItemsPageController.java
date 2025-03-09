package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet13Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class ViewAllOrderItemsPageController {

  @FXML
  private ListView<String> ordersListView;

  /*
   * @author Brian Yang
   */

  @FXML
  public void initialize(){
    ordersListView.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->
            ordersListView.setItems(FXCollections.observableList(
                    ViewUtils.getOrders().stream()
                            .map(order -> {
                              String orderInfo = order.toString();
                              String bundleItems = ViewUtils.getOrderItems(order.getNumber()).stream()
                                      .map(Object::toString)
                                      .collect(Collectors.joining("\n"));
                              return orderInfo + ":\norderItems:" + bundleItems;
                            })
                            .toList()
            ))
    );
    //
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(ordersListView);
  }

}
