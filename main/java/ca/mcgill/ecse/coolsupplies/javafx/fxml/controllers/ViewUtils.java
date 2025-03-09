package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet13Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet1Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet2Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet3Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet4Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet5Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet6Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet7Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOBundleItem;
import ca.mcgill.ecse.coolsupplies.controller.TOGrade;
import ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle;
import ca.mcgill.ecse.coolsupplies.controller.TOItem;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOOrderItem;
import ca.mcgill.ecse.coolsupplies.controller.TOParent;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtils {

  /** Calls the controller and shows an error, if applicable. */
  public static boolean callController(String result) {
    if (result.isEmpty()) {
      CoolSuppliesFxmlView.getInstance().refresh();
      return true;
    }
    showError(result);
    return false;
  }

  /** Calls the controller and returns true on success. This method is included for readability. */
  public static boolean successful(String controllerResult) {
    return callController(controllerResult);
  }

  /**
   * Creates a popup window.
   *
   * @param title: title of the popup window
   * @param message: message to display
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  public static void showError(String message) {
    makePopupWindow("Error", message);
  }
  
  public static ObservableList<TOParent> getParents(){
    List<TOParent> parents = CoolSuppliesFeatureSet1Controller.getParents();

    return FXCollections.observableArrayList(parents);
  }

  public static ObservableList<TOStudent> getStudents(){
    return FXCollections.observableList(CoolSuppliesFeatureSet2Controller.getStudents());
  }

  public static ObservableList<TOItem> getItems(){
    return FXCollections.observableList(CoolSuppliesFeatureSet3Controller.getItems());
  }

  public static ObservableList<TOGradeBundle> getBundles(){
    return FXCollections.observableList(CoolSuppliesFeatureSet4Controller.getBundles());
  }

  public static ObservableList<TOBundleItem> getBundleItems(String bundleName){
    return FXCollections.observableList(CoolSuppliesFeatureSet5Controller.getBundleItems(bundleName));
  }

  public static ObservableList<TOStudent> getStudentsOfParent(String parentEmail){
    return FXCollections.observableList(CoolSuppliesFeatureSet6Controller.getStudentsOfParent(parentEmail));
  }

  public static ObservableList<TOOrderItem> getOrderItems(int orderNumber){
    return FXCollections.observableList(CoolSuppliesFeatureSet13Controller.getOrderItems(orderNumber));
  }

  public static ObservableList<TOGrade> getGrades(){
    return FXCollections.observableList(CoolSuppliesFeatureSet7Controller.getGrades());
  }

  public static ObservableList<TOOrder> getOrders(){
    return FXCollections.observableList(CoolSuppliesFeatureSet11Controller.viewAllOrders());
  }
  public static ObservableList<TOOrder> getStartedOrders(){
    return FXCollections.observableList(CoolSuppliesFeatureSet11Controller.viewAllOrders().stream().filter(order -> order.getStatus().equals("Started")).toList());
  }

  public static ObservableList<String> getInventoryItems(){
    List<String> items = new ArrayList<>();
    for (TOItem item : CoolSuppliesFeatureSet3Controller.getItems()) {
      items.add(item.getName());
    }
    for (TOGradeBundle bundle : CoolSuppliesFeatureSet4Controller.getBundles()) {
      items.add(bundle.getName());
    }
    return FXCollections.observableList(items);
  }

  public static ObservableList<String> getInventoryItemsOfOrder(int orderNumber){
    return FXCollections.observableList(CoolSuppliesFeatureSet13Controller.getInventoryItemsOfOrder(orderNumber));
  }
  
}