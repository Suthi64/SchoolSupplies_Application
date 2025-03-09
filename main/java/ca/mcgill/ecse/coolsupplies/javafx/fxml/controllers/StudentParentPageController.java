package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet12Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet11Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet10Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet9Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet8Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet7Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet6Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet5Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet4Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet3Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet2Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet1Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOOrder;
import ca.mcgill.ecse.coolsupplies.controller.TOParent;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.controller.TOBundleItem;
import ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle;
import ca.mcgill.ecse.coolsupplies.controller.TOItem;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;



public class StudentParentPageController {

    @FXML
    private ChoiceBox<TOParent> selectParentChoiceBox;

    @FXML
    private ChoiceBox<TOStudent> selectStudentChoiceBox;

    @FXML
    private Button addStudentParentButton;

    @FXML
    private ChoiceBox<TOStudent> selectStudentParentChoiceBox;

    @FXML
    private Button removeStudentParentButton;

    @FXML
    private Button setParentButton;

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
  public void initialize(){

    selectParentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      selectParentChoiceBox.setItems(ViewUtils.getParents());
      selectParentChoiceBox.setValue(null);
    } );

    selectStudentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      selectStudentChoiceBox.setItems(ViewUtils.getStudents());
      selectStudentChoiceBox.setValue(null);
    } );

    selectStudentParentChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      selectStudentParentChoiceBox.setItems(FXCollections.emptyObservableList());
      selectStudentParentChoiceBox.setValue(null);
    } );

    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(selectParentChoiceBox, selectStudentChoiceBox, selectStudentParentChoiceBox);
   }

   /*
     * @author Suthiesan Subramaniam
     */
   @FXML
    void addStudentParentClicked(ActionEvent event) {
      TOParent parent = selectParentChoiceBox.getValue();
      TOStudent student = selectStudentChoiceBox.getValue();
      if (parent == null) {
        ViewUtils.showError("Please select a valid parent");
      }
      else if (student == null) {
        ViewUtils.showError("Please select a valid student");
      } else {
        callController(CoolSuppliesFeatureSet6Controller.addStudentToParent(student.getName(), parent.getEmail()));
        CoolSuppliesFxmlView.getInstance().refresh();
      }
    }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void setParentClicked(ActionEvent event) {
      TOParent parent = selectParentChoiceBox.getValue();
      if (parent != null){
      selectStudentParentChoiceBox.setItems(ViewUtils.getStudentsOfParent(parent.getEmail()));
      selectStudentParentChoiceBox.setValue(null);
      }
    }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void removeStudentParentClicked(ActionEvent event) {
      TOParent parent = selectParentChoiceBox.getValue();
      TOStudent student = selectStudentParentChoiceBox.getValue();
      if (parent == null) {
        ViewUtils.showError("Please select a valid parent");
      }
      else if (student == null) {
        ViewUtils.showError("Please select a valid student");
      } else {
        callController(CoolSuppliesFeatureSet6Controller.deleteStudentFromParent(student.getName(), parent.getEmail()));
      }
    }
}