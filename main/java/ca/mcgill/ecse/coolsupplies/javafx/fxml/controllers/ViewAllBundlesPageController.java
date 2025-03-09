package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet13Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class ViewAllBundlesPageController {

  @FXML
  private ListView<String> bundlesListView;

  /*
   * @author Brian Yang
   */

  @FXML
  public void initialize(){
    bundlesListView.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->
            bundlesListView.setItems(FXCollections.observableList(
                    ViewUtils.getBundles().stream()
                            .map(bundle -> {
                              String bundleInfo = bundle.toString() + ", Discount: " + String.valueOf(bundle.getDiscount()) + ", GradeLevel: " + bundle.getGradeLevel();
                              String bundleItems = ViewUtils.getBundleItems(bundle.getName()).stream()
                                      .map(Object::toString)
                                      .collect(Collectors.joining("\n"));
                              return bundleInfo + "\nBundleItems:\n" + bundleItems;
                            })
                            .toList()
            ))
    );
    //
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(bundlesListView);
  }

  /* 
  * @author Brian Yang 
  */
  public static TableColumn<TOGradeBundle, String> createTableColumn(String header,
  String propertyName) 
  {
    TableColumn<TOGradeBundle, String> column = new TableColumn<>(header);
    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    return column;
  }



}
