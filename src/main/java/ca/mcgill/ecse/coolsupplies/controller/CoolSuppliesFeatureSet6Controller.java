package ca.mcgill.ecse.coolsupplies.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Parent;
import ca.mcgill.ecse.coolsupplies.model.Order;
import ca.mcgill.ecse.coolsupplies.model.BundleItem.PurchaseLevel;

public class CoolSuppliesFeatureSet6Controller {

  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();


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
      parent.addStudent(student);
    } 
    catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;

    }
  

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
    } 
    catch (RuntimeException e) {
      error = e.getMessage();
    }
    return error;

  }

  
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
  }
  catch (RuntimeException e) {
    error = e.getMessage();
  }
    return error;

  }

}
