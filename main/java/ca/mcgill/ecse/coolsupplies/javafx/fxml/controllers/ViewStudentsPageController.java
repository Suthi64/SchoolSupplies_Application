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

public class ViewStudentsPageController {

  @FXML
  private ListView<String> studentsListView;

  /*
   * @author Brian Yang
   */

  @FXML
  public void initialize(){
    studentsListView.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->
            studentsListView.setItems(FXCollections.observableList(
                    ViewUtils.getStudents().stream()
                            .map(student -> {
                              String parent = CoolSuppliesFeatureSet13Controller.getParentOfStudent(student.getName());
                              String studentInfo = student.toString()+", Parent:" + parent + ", gradeLevel: "+ student.getGradeLevel();
                              return studentInfo;
                            })
                            .toList()
            ))
    );
    //
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(studentsListView);
  }

}