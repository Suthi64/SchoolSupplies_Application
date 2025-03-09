package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;

import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class BundlePageController {

    @FXML
    private ChoiceBox<TOGradeBundle> selectBundleChoiceBox;

    @FXML
    private ChoiceBox<TOGrade> bundleGradeLevelChoiceBox;

    @FXML
    private TextField bundleNameTextField;

    @FXML
    private TextField bundleDiscountTextField;
    /*
     * @author Doddy Yang Qiu
     */

    @FXML
    public void initialize(){
        selectBundleChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
            selectBundleChoiceBox.setItems(ViewUtils.getBundles());
            selectBundleChoiceBox.setValue(null);
        } );

        bundleGradeLevelChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
            bundleGradeLevelChoiceBox.setItems(ViewUtils.getGrades());
            bundleGradeLevelChoiceBox.setValue(null);
        });

        CoolSuppliesFxmlView.getInstance().registerRefreshEvent(selectBundleChoiceBox, bundleGradeLevelChoiceBox);
    }

    /*
     * @author Doddy Yang Qiu
     */
    @FXML
    public void addBundleClicked(ActionEvent event) {
        String name = bundleNameTextField.getText();
        TOGrade grade = bundleGradeLevelChoiceBox.getValue();
        int discount;
        try {
            discount = Integer.parseInt(bundleDiscountTextField.getText());
        }
        catch (NumberFormatException e){
            ViewUtils.showError("Please input a valid discount.");
            return;
        }
        if (name==null || name.trim().isEmpty()){
            ViewUtils.showError("Please input a valid name.");
        }else if(grade==null){
            ViewUtils.showError("Please select a valid grade level.");
        }else{
            if (successful(CoolSuppliesFeatureSet4Controller.addBundle(name, discount, grade.getLevel()))){
                bundleNameTextField.setText("");
                bundleDiscountTextField.setText("");
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

    /*
     * @author Doddy Yang Qiu
     */
    @FXML
    public void deleteBundleClicked(ActionEvent event) {
        TOGradeBundle bundle = selectBundleChoiceBox.getValue();
        if (bundle==null){
            ViewUtils.showError("Please select a valid bundle.");
        }else{
            callController(CoolSuppliesFeatureSet4Controller.deleteBundle(bundle.getName()));
        }
    }

    /*
     * @author Doddy Yang Qiu
     */
    @FXML
    public void updateBundleClicked(ActionEvent event) {
        TOGradeBundle bundle = selectBundleChoiceBox.getValue();
        String newName = bundleNameTextField.getText();
        TOGrade newGrade = bundleGradeLevelChoiceBox.getValue();
        int discount;
        try {
            discount = Integer.parseInt(bundleDiscountTextField.getText());
        }
        catch (NumberFormatException e){
            ViewUtils.showError("Please input a valid discount.");
            return;
        }
        if (bundle==null){
            ViewUtils.showError("Please select a valid bundle.");
        }else if (newName==null || newName.trim().isEmpty()){
            ViewUtils.showError("Please input a valid name.");
        }else if (newGrade==null){
            ViewUtils.showError("Please select a valid grade level.");
        }else{
            if (successful(CoolSuppliesFeatureSet4Controller.updateBundle(bundle.getName(), newName, discount, newGrade.getLevel()))){
                bundleNameTextField.setText("");
                bundleDiscountTextField.setText("");
                CoolSuppliesFxmlView.getInstance().refresh();
            }
        }
    }

}
