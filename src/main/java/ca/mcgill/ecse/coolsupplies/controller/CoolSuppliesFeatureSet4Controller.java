package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;
import ca.mcgill.ecse.coolsupplies.model.GradeBundle;

import java.util.ArrayList;
import java.util.List;

public class CoolSuppliesFeatureSet4Controller {
  private static final CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  public static String isValidName(String name){
    var error = "";
    //Check if name is empty
    if (name.isEmpty()){
      error = "The name must not be empty";
      return error.trim();
    }

    //Check if name already exists
    for (GradeBundle bundle : coolSupplies.getBundles()){
      if (bundle.getName().equals(name)){
        error = "The name must be unique.";
        break;
      }
    }
    return error.trim();
  }
  public static String isValidDiscount(int discount){
    var error = "";
    if (discount < 0 || discount > 100){
      error = "The discount must be greater than or equal to 0 and less than or equal to 100.";
    }
    return error.trim();
  }

  public static Grade findGrade(String gradeLevel){
    Grade myGrade = null;
    for (Grade grade : coolSupplies.getGrades()){
      if (grade.getLevel().equals(gradeLevel)){
        myGrade = grade;
      }
    }
    return myGrade;
  }
  public static GradeBundle findBundle(String name){
    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()){
      if (bundle.getName().equals(name)){
        myBundle = bundle;
        break;
      }
    }
    return myBundle;
  }
  public static String addBundle(String name, int discount, String gradeLevel) {
    // Validate name
    var error = "";
    error = isValidName(name);
    if (!error.isEmpty()){
      return error;
    }

    //Validate discount
    error = isValidDiscount(discount);
    if (!error.isEmpty()){
      return error;
    }

    // Validate gradeLevel
    Grade myGrade = findGrade(gradeLevel);
    if (myGrade == null){
      error =  "The grade does not exist.";
      return error.trim();
    }
    else if (myGrade.hasBundle()){
      error = "A bundle already exists for the grade.";
      return error.trim();
    }

    //Add GradeBundle
    try {
      coolSupplies.addBundle(name, discount, myGrade);
    } catch (RuntimeException e){
      error = e.getMessage();
    }
    return error;
  }

  public static String updateBundle(String name, String newName, int newDiscount,
      String newGradeLevel) {
    var error = "";

    // Validate name
    GradeBundle myBundle = findBundle(name);
    if (myBundle == null){
      error = "The bundle does not exist.";
      return error.trim();
    }

    // Validate newName
    error = isValidName(newName);
    if (!error.isEmpty()){
      return error.trim();
    }

    // Validate newDiscount
    error = isValidDiscount(newDiscount);
    if (!error.isEmpty()){
      return error.trim();
    }

    // Validate newGradeLevel
    Grade newGrade = findGrade(newGradeLevel);
    if (newGrade == null){
      error =  "The grade does not exist.";
      return error.trim();
    }
    else if (newGrade.hasBundle()){
      error = "A bundle already exists for the grade.";
      return error.trim();
    }

    // Update Bundle
    try{
      myBundle.setName(newName);
      myBundle.setDiscount(newDiscount);
      Grade oldGrade = myBundle.getGrade();
      myBundle.setGrade(newGrade);
      oldGrade.setBundle(null);
    }catch (Exception e){
      error = e.getMessage();
    }
    return error;
  }

  public static String deleteBundle(String name) {
    var error = "";

    // Validate name
    GradeBundle myBundle = findBundle(name);
    if (myBundle == null){
      error = "The grade bundle does not exist.";
      return error.trim();
    }

    // Remove Bundle
    try{
      myBundle.delete();
    }catch (Exception e){
      error = e.getMessage();
    }
    return error;
  }

  public static TOGradeBundle getBundle(String name) {
    var error = "";
    TOGradeBundle result = null;

    // Validate name
    GradeBundle myBundle = findBundle(name);
    if (myBundle == null){
      return result;
    }

    result = new TOGradeBundle(name, myBundle.getDiscount(), myBundle.getGrade().getLevel());
    return result;
  }

  // returns all bundles
  public static List<TOGradeBundle> getBundles() {
    List<TOGradeBundle> list = new ArrayList<TOGradeBundle>();
    for (GradeBundle bundle : coolSupplies.getBundles()){
      list.add(new TOGradeBundle(bundle.getName(), bundle.getDiscount(), bundle.getGrade().getLevel()));
    }
    return list;
  }

}
