package ca.mcgill.ecse.coolsupplies.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Student;
import ca.mcgill.ecse.coolsupplies.model.Grade;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

public class CoolSuppliesFeatureSet2Controller {
  private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private CoolSuppliesFeatureSet2Controller() {}

  /**
   * <p>
   * Adds a student including its grade to CoolSupplies
   * </p>
   * 
   * @param name the name of the student
   * @return an error string if there is any
   * @author Brian Yang
   */
  public static String addStudent(String name, String gradeLevel) {
    // input validation
    var error = "";
    if (name == null) {
      error = "The name must not be null.";
    } else if (name.isEmpty()) {
      error = "The name must not be empty.";
    }

    // call model
    Grade grade = Grade.getWithLevel(gradeLevel);
    if (grade == null) {
      error = "The grade does not exist.";
    }
    if (!error.isEmpty()) {
      return error.trim();
    }
    try {
      coolSupplies.addStudent(name, grade);
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      if (error.startsWith("Cannot create due to duplicate name")) {
        error = "The name must be unique.";
      }
      return error;
    }
    return "";
  }

  /**
   * <p>
   * Updates a student in CoolSupplies including its grade.
   * </p>
   * 
   * @param name the current name of the student 
   * @param newName the new name of the student
   * @param newGradeLevel the new grade level of the student
   * @return an error string if there is any
   * @author Brian Yang
   */
  public static String updateStudent(String name, String newName, String newGradeLevel) {
    // input validation
    var error = "";
    if (newName == null) {
      error = "The name must not be null.";
    } else if (newName.isEmpty()) {
      error = "The name must not be empty.";
    }
    Student student = Student.getWithName(name);
    if (student == null) {
      error = "The student does not exist.";
    }
    Grade grade = Grade.getWithLevel(newGradeLevel);
    if (grade == null) {
      error = "The grade does not exist.";
    }
    if (!error.isEmpty()) {
      return error.trim();
    }

    // call model
    boolean duplicate=student.setName(newName);
    if (!duplicate) {
      error="The name must be unique.";
      return error;
    }
    student.setGrade(grade);
    try {
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return "";
  }

  /**
   * <p>
   * Delete a student from CoolSupplies.
   * </p>
   * 
   * @param name the name of the student 
   * @return an error string if there is any
   * @author Brian Yang
   */
  public static String deleteStudent(String name) {
    var error = "";
    Student student = Student.getWithName(name);
    if (student == null) {
      error = "The student does not exist.";
      return error.trim();
    }
    try {
      student.delete();
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e) {
      error = e.getMessage();
      return error;
    }
    return "";
  }

  /**
   * <p>
   * Getting a student in CoolSupplies using their name
   * </p>
   * 
   * @param name the name of the student 
   * @return TOStudent
   * @author Brian Yang
   */
  public static TOStudent getStudent(String name) {
    Student student = Student.getWithName(name);
    if (student == null) {
      return null;
    } else {
      String gradeLevel = student.getGrade().getLevel();
      return new TOStudent(name, gradeLevel);
    }
  }

  /**
   * <p>
   * Getting all students in CoolSupplies 
   * </p>
   * 
   * @return List<TOStudent>
   * @author Brian Yang
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
