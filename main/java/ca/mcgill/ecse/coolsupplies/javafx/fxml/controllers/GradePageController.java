package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet7Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOGrade;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GradePageController {

    @FXML
    private Button addGradeButton;

    @FXML
    private TextField addGradeNameTextField;

    @FXML
    private Button deleteGradeButton;

    @FXML
    private ChoiceBox<TOGrade> deleteGradeChoiceBox;

    @FXML
    private Button updateGradeButton;

    @FXML
    private ChoiceBox<TOGrade> updateGradeChoiceBox;

    @FXML
    private TextField updateGradeNameTextField;
    
    /* 
     * @author Moustapha El Zein 
     */
     @FXML
     public void initialize(){
    	 updateGradeChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
         updateGradeChoiceBox.setItems(ViewUtils.getGrades());
         updateGradeChoiceBox.setValue(null);
       } );
    	 
    	 deleteGradeChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
         deleteGradeChoiceBox.setItems(ViewUtils.getGrades());
         deleteGradeChoiceBox.setValue(null);
       } );

       CoolSuppliesFxmlView.getInstance().registerRefreshEvent(updateGradeChoiceBox, deleteGradeChoiceBox);
     }

   /* 
    * @author Moustapha El Zein 
    */
    @FXML
    void addGradeClicked(ActionEvent event) {
    	String gradeName = addGradeNameTextField.getText();
    	
    	if (gradeName == null || gradeName.trim().isEmpty()){
    	      ViewUtils.showError("Please input a valid grade name.");
    	}
    	else{
    	      if (successful(CoolSuppliesFeatureSet7Controller.addGrade(gradeName))){
    	    	addGradeNameTextField.setText("");
    	        CoolSuppliesFxmlView.getInstance().refresh();
    	      }
    	}
    }

   /* 
    * @author Moustapha El Zein 
    */
    @FXML
    void deleteGradeClicked(ActionEvent event) {
    	TOGrade grade = deleteGradeChoiceBox.getValue();
        if (grade == null){
          ViewUtils.showError("Please select a valid grade level.");
        }
        else{
          callController(CoolSuppliesFeatureSet7Controller.deleteGrade(grade.getLevel()));
        }
    }

   /* 
    * @author Moustapha El Zein 
    */
    @FXML
    void updateGradeClicked(ActionEvent event) {
    	TOGrade grade = updateGradeChoiceBox.getValue();
    	String newGradeLevel = updateGradeNameTextField.getText();
    	
    	if (grade == null) {
    		ViewUtils.showError("Please select a valid grade level.");
    	}
    	else if (newGradeLevel == null || newGradeLevel.trim().isEmpty()){
    		ViewUtils.showError("Please input a valid grade name.");
    	}
    	else{
    	      if (successful(CoolSuppliesFeatureSet7Controller.updateGrade(grade.getLevel(), newGradeLevel))){
    	    	updateGradeNameTextField.setText("");
    	        CoolSuppliesFxmlView.getInstance().refresh();
    	      }
    	    }
    }

}
