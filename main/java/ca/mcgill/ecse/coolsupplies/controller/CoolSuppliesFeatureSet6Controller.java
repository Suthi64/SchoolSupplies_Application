package ca.mcgill.ecse.coolsupplies.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Parent;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;

/**
 * The feature set 6 handles student-parent methods as well as starting orders
 * @author Baptiste Didier
 */

public class CoolSuppliesFeatureSet6Controller {

  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  /**
   * <p> Adds a student with its name to a parent with its email
   * @param studentName the name which identifies the student
   * @param parentEmail the email which identifies the parent
   * @return The error message
   */

  public static String addStudentToParent(String studentName, String parentEmail) {

    var error = "";
    Parent parent = null;
    for (Parent parentX : coolSupplies.getParents()) {
      if (parentX.getEmail().equals(parentEmail)) {
        parent = parentX;
        break;
      }
    }

    if (parent == null) {
      error = "The parent does not exist.";
      return error.trim();
    }

    Student student = null;
    for (Student studentX : coolSupplies.getStudents()) {
      if (studentX.getName().equals(studentName)) {
        student = studentX;
        break;
      }
    }

    if (student == null) {
      error = "The student does not exist.";
      return error.trim();
    }

    try {
      if(!parent.addStudent(student)){
        return "The student already exists in the parent";
      }
      CoolsuppliesPersistence.save();
    } 
    catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;

    }
  
  /**
   * <p> Deletes a student with their name from its parent with their name
   * @param studentName The name which identifies the student
   * @param parentEmail The email which identifies the parent
   * @return The error message
   */

  public static String deleteStudentFromParent(String studentName, String parentEmail) {

    var error = "";
    Parent parent = null;
    for (Parent parentX : coolSupplies.getParents()) {
      if (parentX.getEmail().equals(parentEmail)) {
        parent = parentX;
        break;
      }
    }

    if (parent == null) {
      error = "The parent does not exist.";
      return error.trim();
    }

    Student student = null;
    for (Student studentX : coolSupplies.getStudents()) {
      if (studentX.getName().equals(studentName)) {
        student = studentX;
        break;
      }
    }

    if (student == null) {
      error = "The student does not exist.";
      return error.trim();
    }

    try {
      parent.removeStudent(student);
      CoolsuppliesPersistence.save();
    } 
    catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;

  }

  /**
   * <p> Gets the student from their name from their parent with their email
   * @param studentName The name which identifies the student
   * @param parentEmail The email which identifies the parent
   * @return A reference to the student
   */

  public static TOStudent getStudentOfParent(String studentName, String parentEmail) {
    
    Parent parent = null;
    for (Parent parentX : coolSupplies.getParents()) {
      if (parentX.getEmail().equals(parentEmail)) {
        parent = parentX;
        break;
      }
    }

    if (parent == null) {
      return null;
    }

    Student student = null;
    for (Student studentX : parent.getStudents()) {
      if (studentX.getName().equals(studentName)) {
        student = studentX;
        break;
      }
    }

    if (student == null) {
      return null;
    }

    return new TOStudent(student.getName(), student.getGrade().getLevel());
  }

  /**
   * <p> Gets all the students from a parent with their email
   * @param parentEmail The email that identifies the parent
   * @return A reference to the list of students associated with the parent
   */

  public static List<TOStudent> getStudentsOfParent(String parentEmail) {

    Parent parent = null;
    for (Parent parentX : coolSupplies.getParents()) {
      if (parentX.getEmail().equals(parentEmail)) {
        parent = parentX;
        break;
      }
    }

    if (parent == null) {
      return new ArrayList<TOStudent>();
    }

    List<TOStudent> students = new ArrayList<TOStudent>();
    for (Student student : parent.getStudents()) {
      students.add(new TOStudent(student.getName(), student.getGrade().getLevel()));
    }
    return students;
  }

  /**
   * <p> Creates an order
   * @param number The unique number that identifies the order
   * @param date The date when the order was placed
   * @param level The level of requirement of the order
   * @param parentEmail The email which identifies the parent
   * @param studentName The name which identifies the student
   * @return The error message
   */

  public static String startOrder(int number, Date date, String level, String parentEmail, String studentName) {
        
    var error = "";
    if (number <= 0) {
      error = "The number must be greater than 0.";
      return error.trim();
    }

    for (Order orderX : coolSupplies.getOrders()) {
      if (orderX.getNumber() == number) {
        error = "The number must be unique.";
        return error.trim();
      }
    }

    Parent parent = null;
    for (Parent parentX : coolSupplies.getParents()) {
      if (parentX.getEmail().equals(parentEmail)) {
        parent = parentX;
        break;
      }
    }

    if (parent == null) {
      error = "The parent does not exist.";
      return error.trim();
    }

    Student student = null;
    for (Student studentX : coolSupplies.getStudents()) {
      if (studentX.getName().equals(studentName)) {
        student = studentX;
        break;
      }
    }

    if (student == null) {
      error = "The student does not exist.";
      return error.trim();
    }
  
    PurchaseLevel purchaseLevel = null;
    for (PurchaseLevel purchaseLevelX : PurchaseLevel.values()) {
      if (purchaseLevelX.name().equals(level)) { 
        purchaseLevel = purchaseLevelX;
        break;
      }
    }

    if (purchaseLevel == null) {
      error = "The level must be Mandatory, Recommended, or Optional.";
      return error.trim();
    }

  try {
    Order order = new Order(number, date, purchaseLevel, parent, student, coolSupplies);
    CoolsuppliesPersistence.save();
  }
  catch (RuntimeException e) {
    error = e.getMessage();
  }
    return error;

  }

}
