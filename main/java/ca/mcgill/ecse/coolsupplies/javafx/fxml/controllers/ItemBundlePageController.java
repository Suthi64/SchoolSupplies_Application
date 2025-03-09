package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
//import java.sql.Date;
//import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet13Controller;
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
import javafx.scene.control.TextField;


public class ItemBundlePageController {

    @FXML
    private ChoiceBox<TOItem> itemChoiceBox;

    @FXML
    private ChoiceBox<TOGradeBundle> gradeBundleChoiceBox;

    @FXML
    private TextField itemQuantityTextField;

    @FXML
    private ChoiceBox<String> itemTypeChoiceBox;

    @FXML
    private ChoiceBox<TOBundleItem> bundleItemChoiceBox;

    @FXML
    private Button addItemButton;
    
    @FXML
    private Button deleteItemButton;

    @FXML
    private Button updateItemButton;

    @FXML
    private Button setGradeBundleButton;


    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
  public void initialize(){
    itemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      itemChoiceBox.setItems(ViewUtils.getItems());
      itemChoiceBox.setValue(null);
    } );

    bundleItemChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      bundleItemChoiceBox.setItems(FXCollections.emptyObservableList());
      bundleItemChoiceBox.setValue(null);
    } );

    gradeBundleChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      gradeBundleChoiceBox.setItems(ViewUtils.getBundles());
      gradeBundleChoiceBox.setValue(null);
    });


    ObservableList<String> purchaseLevels = FXCollections.observableArrayList("Mandatory","Recommended","Optional");
    itemTypeChoiceBox.setItems(purchaseLevels);
    itemTypeChoiceBox.setValue(purchaseLevels.get(0));

    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(bundleItemChoiceBox, gradeBundleChoiceBox, itemChoiceBox);
   }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void addItemClicked(ActionEvent event) {
      try {
        TOGradeBundle gradeBundle = gradeBundleChoiceBox.getValue();
        TOItem item = itemChoiceBox.getValue();
        int quantity = Integer.parseInt(itemQuantityTextField.getText());
        String itemType = itemTypeChoiceBox.getValue();
        var error = "";
        if (gradeBundle == null){
          error += "Invalid gradeBundle";
        }
        if (item == null){
          error += "Invalid item";
        }
        if (itemType == null){
          error += "Invalid item type";
        }
        if (!error.isEmpty()){
          ViewUtils.showError(error);
        }else{
          if (successful(CoolSuppliesFeatureSet5Controller.addBundleItem(quantity, itemType, item.getName(), gradeBundle.getName()))){
            itemQuantityTextField.setText("");
            CoolSuppliesFxmlView.getInstance().refresh();
          }
        }
      }catch(NumberFormatException e){
        ViewUtils.showError("Please input a valid quantity number");
      }
    }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void setGradeBundleClicked(ActionEvent event) {
      TOGradeBundle gradeBundle = gradeBundleChoiceBox.getValue();
      if (gradeBundle != null){
      bundleItemChoiceBox.setItems(ViewUtils.getBundleItems(gradeBundle.getName()));
      bundleItemChoiceBox.setValue(null);
      }
    }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void deleteItemClicked(ActionEvent event) {
      TOGradeBundle gradeBundle = gradeBundleChoiceBox.getValue();
      TOBundleItem bundleItem = bundleItemChoiceBox.getValue();
      if (gradeBundle == null) {
        ViewUtils.showError("Please select a valid grade bundle");
      }
      else if (bundleItem == null) {
        ViewUtils.showError("Please select a valid bundle item");
      } else {
        callController(CoolSuppliesFeatureSet5Controller.deleteBundleItem(bundleItem.getItemName(), gradeBundle.getName()));
      }

      }

    /*
     * @author Suthiesan Subramaniam
     */
    @FXML
    void updateItemClicked(ActionEvent event) {
      try {
        TOGradeBundle gradeBundle = gradeBundleChoiceBox.getValue();
        TOBundleItem item = bundleItemChoiceBox.getValue();
        int newQuantity = Integer.parseInt(itemQuantityTextField.getText());
        String newItemType = itemTypeChoiceBox.getValue();
        var error = "";
        if (gradeBundle == null){
          error += "Invalid grade";
        }
        if (item == null){
          error += "Invalid item";
        }
        if (newItemType == null){
          error += "Invalid item type";
        }
        if (!error.isEmpty()){
          ViewUtils.showError(error);
        }else{
          if (successful(CoolSuppliesFeatureSet5Controller.updateBundleItem(item.getItemName(), gradeBundle.getName(), newQuantity, newItemType))){
            itemQuantityTextField.setText("");
            CoolSuppliesFxmlView.getInstance().refresh();
          }
        }
      }catch(NumberFormatException e){
        ViewUtils.showError("Please input a valid quantity number");
      }
    }

}