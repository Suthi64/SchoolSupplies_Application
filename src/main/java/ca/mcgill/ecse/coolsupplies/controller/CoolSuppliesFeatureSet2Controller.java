package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.model.Grade;

public class CoolSuppliesFeatureSet2Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet2Controller() {}

  /*
   * adding a Student to CoolSupplies including its grade ;
   *  Author: Brian Yang
   */
  public static String addStudent(String name, String gradeLevel) {
    // input validation
    var error = "";
    if (name == null) {
      error = "The name must not be null";
    } else if (name.isEmpty()) {
      error = "The name must not be empty";
    }

    // call model
    Grade grade = null;
    for (Grade gradei : coolSupplies.getGrades()) {
      if (gradei.getLevel().equals(gradeLevel)) {
        grade = gradei;
        break;
      }
    }
    if (grade == null) {
      error = "The grade does not exist";
    }
    if (!error.isEmpty()) {
      return error.trim();
    }
    try {
      coolSupplies.addStudent(name, grade);
    } catch (RuntimeException e) {
      error = e.getMessage();
      if (error.startsWith("Cannot create due to duplicate name")) {
        error = "The name must be unique";
      }
      return error;
    }
    return "";
  }

  /*
   * updating a student (including it grade) 
   * Author: Brian Yang
   */
  public static String updateStudent(String name, String newName, String newGradeLevel) {
    // input validation
    var error = "";
    if (newName == null) {
      error = "The name must not be null.";
    } else if (newName.isEmpty()) {
      error = "The name must not be empty.";
    }
    Student student = null;
    for (Student studentI : coolSupplies.getStudents()) {
      if (studentI.getName().equals(name)) {
        student = studentI;
        break;
      }
    }
    if (student == null) {
      error = "The student does not exist.";
    }
    Grade grade = null;
    for (Grade gradei : coolSupplies.getGrades()) {
      if (gradei.getLevel().equals(newGradeLevel)) {
        grade = gradei;
        break;
      }
    }
    if (grade == null) {
      error = "The grade does not exist.";
    }
    if (!error.isEmpty()) {
      return error.trim();
    }

    // call model
    try {
      coolSupplies.removeStudent(student);
      coolSupplies.addStudent(newName, grade);
    } catch (RuntimeException e) {
      error = e.getMessage();
      if (error.startsWith("Cannot create due to duplicate name")) {
        error = "The name must be unique.";
      }
      return error;
    }
    return "";
  }

  /*
   * deleting a student from CoolSupplies
   * Author : Brian Yang
   */
  public static String deleteStudent(String name) {
    var error = "";
    Student student = null;
    for (Student studenti : coolSupplies.getStudents()) {
      if (studenti.getName().equals(name)) {
        student = studenti;
      }
    }
    if (student == null) {
      error = "The student does not exist.";
      return error.trim();
    }
    try {
      student.delete();
    } catch (RuntimeException e) {
      error = e.getMessage();
      return error;
    }
    return "";
  }

  /*
   * get a student from CoolSupplies
   * Author: Brian Yang
   */
  public static TOStudent getStudent(String name) {
    Student student = null;
    for (Student studenti : coolSupplies.getStudents()) {
      if (studenti.getName().equals(name)) {
        student = studenti;
      }
    }
    if (student == null) {
      return null;
    } else {
      String gradeLevel = student.getGrade().getLevel();
      return new TOStudent(name, gradeLevel);
    }
  }

  /*
   * get all the students in CoolSupplies
   * Author: Brian Yang
   */
  public static List<TOStudent> getStudents() {
    List<TOStudent> students = new ArrayList<TOStudent>();
    for (Student student : coolSupplies.getStudents()) {
      String name = student.getName();
      String gradeLevel = student.getGrade().getLevel();
      students.add(new TOStudent(name, gradeLevel));
    }
    return students;
  }

}
