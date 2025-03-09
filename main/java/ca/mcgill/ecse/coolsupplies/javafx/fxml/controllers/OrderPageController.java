package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import java.sql.Date;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet10Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet6Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet8Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOParent;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.getStudentsOfParent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class OrderPageController {

  @FXML
  private DatePicker orderDatePicker;

  @FXML
  private TextField orderNumberTextField;

  @FXML
  private ChoiceBox<TOParent> orderParentChoiceBox;

  @FXML
  private ChoiceBox<TOStudent> orderStudentChoiceBoxA;

  @FXML
  private ChoiceBox<String> purchaseLevelChoiceBox;

  @FXML
  private ChoiceBox<TOOrder> selectOrderChoiceBox;

  /* 
   * @author Brian Yang
  */
  @FXML
  public void initialize(){
    orderParentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      orderParentChoiceBox.setItems(ViewUtils.getParents());
      orderParentChoiceBox.setValue(null);
    } );

    orderStudentChoiceBoxA.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      orderStudentChoiceBoxA.setItems(FXCollections.emptyObservableList());
      orderStudentChoiceBoxA.setValue(null);
    });

    ObservableList<String> purchaseLevels = FXCollections.observableArrayList("Mandatory","Recommended","Optional");
    purchaseLevelChoiceBox.setItems(purchaseLevels);
    purchaseLevelChoiceBox.setValue(purchaseLevels.get(0));

    selectOrderChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      selectOrderChoiceBox.setItems(ViewUtils.getOrders());
      selectOrderChoiceBox.setValue(null);
    });
    orderDatePicker.setEditable(false);
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(orderParentChoiceBox,selectOrderChoiceBox,selectOrderChoiceBox, orderStudentChoiceBoxA);
   }
  
  /* 
   * @author Brian Yang
   */ 
  @FXML
  void cancelOrderClicked(ActionEvent event) {
    TOOrder order = selectOrderChoiceBox.getValue();
    if (order==null){
      ViewUtils.showError("Please select a valid order");
    }else{
      callController(CoolSuppliesFeatureSet10Controller.cancelOrder(Integer.toString(order.getNumber())));
    }
  }

  /* 
   * @author Brian Yang
   */ 
  @FXML
  void startOrderClicked(ActionEvent event) {
    try{
      int number = Integer.parseInt(orderNumberTextField.getText());
      var date= orderDatePicker.getValue();
      String level = purchaseLevelChoiceBox.getValue();
      TOParent parent = orderParentChoiceBox.getValue();
      TOStudent student = orderStudentChoiceBoxA.getValue();
      var error = "";
      if (date == null){
        error += "Invalid date";
      }
      if (level == null){
        error += "Invalid level";
      }
      if (parent == null){
        error += "Invalid parent";
      }
      if (student == null){
        error += "Invalid student";
      }
      if (!error.isEmpty()){
        ViewUtils.showError(error);
      }else{
        var realDate= Date.valueOf(date);
        if (successful(CoolSuppliesFeatureSet6Controller.startOrder(number, realDate, level, parent.getEmail(), student.getName()))){
          orderNumberTextField.setText("");
        }
      }
    }catch(NumberFormatException e){
      ViewUtils.showError("Please input a valid order number");
    }
  }

  /* 
   * @author Brian Yang
   */ 
  @FXML
  void updateOrderClicked(ActionEvent event) {
      TOOrder order = selectOrderChoiceBox.getValue();
      String newLevel = purchaseLevelChoiceBox.getValue();
      TOStudent newStudent = orderStudentChoiceBoxA.getValue();
      var error = "";
      if (newLevel == null){
        error += "Invalid level";
      }
      if (newStudent == null){
        error += "Invalid student";
      }
      if (!error.isEmpty()){
        ViewUtils.showError(error);
      }else{
        callController(CoolSuppliesFeatureSet8Controller.updateOrder(Integer.toString(order.getNumber()), newLevel, newStudent.getName()));
      }
  }

  @FXML
  void setOrderClicked(ActionEvent event) {
    TOOrder order = selectOrderChoiceBox.getValue();
    if (order != null){
    orderStudentChoiceBoxA.setItems(getStudentsOfParent(order.getParentEmail()));
    orderStudentChoiceBoxA.setValue(null);
    }

  }

  @FXML
  void setParentClicked(ActionEvent event) {
    TOParent parent = orderParentChoiceBox.getValue();
    if (parent != null){
    orderStudentChoiceBoxA.setItems(getStudentsOfParent(parent.getEmail()));
    orderStudentChoiceBoxA.setValue(null);
    }

  }

}
