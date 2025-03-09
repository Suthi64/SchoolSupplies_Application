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

public class ViewAllParentsPageController {

  @FXML
  private ListView<String> parentsListView;

  /*
   * @author Brian Yang
   */

  @FXML
  public void initialize(){
    parentsListView.addEventHandler(CoolSuppliesFxmlView.REFRESH_EVENT, e ->
            parentsListView.setItems(FXCollections.observableList(
                    ViewUtils.getParents().stream()
                            .map(parent -> {
                              String parentInfo = "Name: "+ parent.getName()+"," +parent.toString() + ", Phone Number: "+ parent.getPhoneNumber();
                              String students = ViewUtils.getStudentsOfParent(parent.getEmail()).stream()
                                      .map(Object::toString)
                                      .collect(Collectors.joining("\n"));
                              return parentInfo + "\nStudents: \n" + students;
                            })
                            .toList()
            ))
    );
    //
    CoolSuppliesFxmlView.getInstance().registerRefreshEvent(parentsListView);
  }


}