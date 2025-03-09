package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.successful;
import static ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers.ViewUtils.callController;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet2Controller;
import ca.mcgill.ecse.coolsupplies.controller.CoolSuppliesFeatureSet3Controller;
import ca.mcgill.ecse.coolsupplies.controller.TOGrade;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class StudentPageController {

  @FXML
  private ChoiceBox<TOStudent> selectStudentChoiceBox1;

  @FXML
  private ChoiceBox<TOGrade> studentGradeLevelChoiceBox;

  @FXML
  private TextField studentNameTextField;
 /* 
  * @author Brian Yang 
  */

  @FXML
  public void initialize(){
    selectStudentChoiceBox1.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e -> {
      selectStudentChoiceBox1.setItems(ViewUtils.getStudents());
      selectStudentChoiceBox1.setValue(null);
    } );

    studentGradeLevelChoiceBox.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->{
      studentGradeLevelChoiceBox.setItems(ViewUtils.getGrades());
      studentGradeLevelChoiceBox.setValue(null);
    });

    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(selectStudentChoiceBox1, studentGradeLevelChoiceBox);
  }

 /* 
  * @author Brian Yang 
  */
  @FXML
  public void addStudentClicked(ActionEvent event) {
    String name = studentNameTextField.getText();
    TOGrade grade = studentGradeLevelChoiceBox.getValue();
    if (name==null || name.trim().isEmpty()){
      ViewUtils.showError("Please input a valid name");
    }else if(grade==null){
      ViewUtils.showError("Please select a valid grade level");
    }else{
      if (successful(CoolSuppliesFeatureSet2Controller.addStudent(name, grade.getLevel()))){
        studentNameTextField.setText("");
        CoolSuppliesFxmlView.getInstance().refresh();
      }
    }
  }

 /* 
  * @author Brian Yang 
  */
  @FXML
  public void deleteStudentClicked(ActionEvent event) {
    TOStudent student = selectStudentChoiceBox1.getValue();
    if (student==null){
      ViewUtils.showError("Please select a valid student.");
    }else{
      callController(CoolSuppliesFeatureSet2Controller.deleteStudent(student.getName()));
    }
  }

 /* 
  * @author Brian Yang 
  */
  @FXML
  public void updateStudentClicked(ActionEvent event) {
    TOStudent student = selectStudentChoiceBox1.getValue();
    String newName = studentNameTextField.getText(); 
    TOGrade newGrade = studentGradeLevelChoiceBox.getValue();
    if (student==null){
      ViewUtils.showError("Please select a valid student");
    }else if (newName==null || newName.trim().isEmpty()){
      ViewUtils.showError("Please input a valid name");
    }else if (newGrade==null){
      ViewUtils.showError("Please select a valid grade level");
    }else{
      if (successful(CoolSuppliesFeatureSet2Controller.updateStudent(student.getName(), newName, newGrade.getLevel()))){
        studentNameTextField.setText("");
        CoolSuppliesFxmlView.getInstance().refresh();
      }
    }
  }

}
