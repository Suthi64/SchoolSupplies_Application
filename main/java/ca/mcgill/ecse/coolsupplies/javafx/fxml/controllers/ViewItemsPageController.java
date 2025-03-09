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

public class ViewItemsPageController {

  @FXML
  private ListView<String> itemsListView;

  /*
   * @author Brian Yang
   */

  @FXML
  public void initialize(){
    itemsListView.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->
            itemsListView.setItems(FXCollections.observableList(
                    ViewUtils.getItems().stream()
                            .map(item -> {
                              String itemInfo ="Name: "+ item.getName()+", Price: " + item.getPrice();
                              return itemInfo;
                            })
                            .toList()
            ))
    );
    //
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(itemsListView);
  }

}