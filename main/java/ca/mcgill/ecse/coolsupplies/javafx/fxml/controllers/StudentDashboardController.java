package ca.mcgill.ecse.coolsupplies.javafx.fxml.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import ca.mcgill.ecse.coolsupplies.controller.*;
import ca.mcgill.ecse.coolsupplies.javafx.fxml.CoolSuppliesFxmlView;
import ca.mcgill.ecse.coolsupplies.controller.TOParent;
import ca.mcgill.ecse.coolsupplies.controller.TOStudent;
import ca.mcgill.ecse.coolsupplies.controller.TOGrade;

public class StudentDashboardController {
   
    @FXML
    private TableView<TOStudent> studentTable;
       
    @FXML
    private TableColumn<TOStudent, String> nameColumn;
   
    @FXML
    private TableColumn<TOStudent, TOGrade> gradeColumn;
   
    @FXML
    private TableColumn<TOStudent, TOParent> parentColumn;
   
    @FXML
    private ChoiceBox<TOParent> selectParentChoiceBox;
   
    @FXML
    private TextField studentNameTextField;
   
    @FXML
    private ChoiceBox<TOGrade> studentGradeLevelChoiceBox;

    // Declare the studentList as an ObservableList
    private ObservableList<TOStudent> studentList;

    @FXML
    public void initialize() {
        // Set up the table columns with the correct property names from the model
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("gradeLevel"));
        parentColumn.setCellValueFactory(new PropertyValueFactory<>("assignedParent"));

        // Populate the table and choice boxes
        refreshStudentTable();
        studentGradeLevelChoiceBox.setItems(ViewUtils.getGrades());
        selectParentChoiceBox.setItems(ViewUtils.getParents());

        // Listener for table selection to auto-fill the form
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateStudentForm(newSelection);
            }
        });
    }

    private void refreshStudentTable() {
        studentList = FXCollections.observableArrayList(ViewUtils.getStudents()); // Get the updated student list
        studentTable.setItems(studentList); // Update the table with refreshed data
    }

    private void populateStudentForm(TOStudent student) {
        studentNameTextField.setText(student.getName());
        studentGradeLevelChoiceBox.setValue(ViewUtils.getGradeByLevel(student.getGradeLevel()));
        selectParentChoiceBox.setValue(ViewUtils.getParentByEmail(student.getParentEmail()));
    }

    @FXML
    public void updateStudentClicked() {
        TOStudent selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            ViewUtils.showError("Please select a student to update.");
            return;
        }

        String newName = studentNameTextField.getText();
        TOGrade newGrade = studentGradeLevelChoiceBox.getValue();
        TOParent newParent = selectParentChoiceBox.getValue();

        if (newName == null || newName.trim().isEmpty()) {
            ViewUtils.showError("Please enter a valid student name.");
            return;
        }

        if (newGrade == null) {
            ViewUtils.showError("Please select a valid grade level.");
            return;
        }

        if (ViewUtils.successful(CoolSuppliesFeatureSet2Controller.updateStudent(selectedStudent.getName(), newName, newGrade.getLevel()))) {
            if (newParent != null) {
                CoolSuppliesFeatureSet6Controller.addStudentToParent(newName, newParent.getEmail());
            }
            refreshStudentTable();
        }
    }

    @FXML
    public void registerStudentClicked() {
        String name = studentNameTextField.getText();
        TOGrade grade = studentGradeLevelChoiceBox.getValue();
        TOParent parent = selectParentChoiceBox.getValue();

        if (name == null || name.trim().isEmpty()) {
            ViewUtils.showError("Please enter a valid student name.");
            return;
        }

        if (grade == null) {
            ViewUtils.showError("Please select a valid grade level.");
            return;
        }

        if (ViewUtils.successful(CoolSuppliesFeatureSet2Controller.addStudent(name, grade.getLevel()))) {
            if (parent != null) {
                CoolSuppliesFeatureSet6Controller.addStudentToParent(name, parent.getEmail());
            }
            refreshStudentTable();
        }
    }

    @FXML
    public void deleteStudentClicked() {
        TOStudent selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            ViewUtils.showError("Please select a student to delete.");
            return;
        }

        if (ViewUtils.successful(CoolSuppliesFeatureSet2Controller.deleteStudent(selectedStudent.getName()))) {
            refreshStudentTable();
        }
    }
}