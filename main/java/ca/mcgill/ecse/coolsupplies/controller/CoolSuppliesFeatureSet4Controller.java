package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;
import ca.mcgill.ecse.coolsupplies.model.GradeBundle;
import ca.mcgill.ecse.coolsupplies.persistence.CoolsuppliesPersistence;

import java.util.ArrayList;
import java.util.List;

public class CoolSuppliesFeatureSet4Controller {
  private static final CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();

  private static String isValidName(String name){
    var error = "";
    //Check if name is empty
    if (name.isEmpty()){
      error = "The name must not be empty.";
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
  private static String isValidDiscount(int discount){
    var error = "";
    if (discount < 0 || discount > 100){
      error = "The discount must be greater than or equal to 0 and less than or equal to 100.";
    }
    return error.trim();
  }
  private static GradeBundle findBundle(String name){
    GradeBundle myBundle = null;
    for (GradeBundle bundle : coolSupplies.getBundles()){
      if (bundle.getName().equals(name)){
        myBundle = bundle;
        break;
      }
    }
    return myBundle;
  }

  /**
   * The addBundle method adds a GradeBundle to the list of Bundles using the
   * given name, discount, and gradeLevel.
   *
   * @author Doddy Yang Qiu
   * @param name The name of the new GradeBundle.
   * @param discount The discount of the new GradeBundle.
   * @param gradeLevel The gradeLevel of the new GradeBundle.
   * @return an empty string if successful, or an error message otherwise.
   */
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
    Grade myGrade = Grade.getWithLevel(gradeLevel);
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
      CoolsuppliesPersistence.save();
    } catch (RuntimeException e){
      error = e.getMessage();
    }
    return error;
  }

  /**
   * The updateBundle method finds a specified GradeBundle by name and updates
   * its name, discount, and grade to the specified values.
   *
   * @author Doddy Yang Qiu
   * @param name The name of the GradeBundle to update.
   * @param newName The name to update to.
   * @param newDiscount The discount to update to.
   * @param newGradeLevel The Grade to update to.
   * @return an empty string if successful, or an error message otherwise.
   */
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
    if (!newName.equals(name)){
      error = isValidName(newName);
      if (!error.isEmpty()){
        return error.trim();
      }
    }

    // Validate newDiscount
    error = isValidDiscount(newDiscount);
    if (!error.isEmpty()){
      return error.trim();
    }

    // Validate newGradeLevel
    Grade newGrade = Grade.getWithLevel(newGradeLevel);
    if (!myBundle.getGrade().getLevel().equals(newGradeLevel)){
      if (newGrade == null){
        error =  "The grade does not exist.";
        return error.trim();
      }
      else if (newGrade.hasBundle()){
        error = "A bundle already exists for the grade.";
        return error.trim();
      }
    }

    // Update Bundle
    try{
      myBundle.setName(newName);
      myBundle.setDiscount(newDiscount);
      Grade oldGrade = myBundle.getGrade();
      myBundle.setGrade(newGrade);
      oldGrade.setBundle(null);
      CoolsuppliesPersistence.save();
    }catch (Exception e){
      error = e.getMessage();
    }
    return error;
  }

  /**
   * The deleteBundle method finds a specified GradeBundle by name
   * and deletes it.
   *
   * @author Doddy Yang Qiu
   * @param name The name of the GradeBundle to delete.
   * @return an empty string if it successful, or an error message otherwise.
   */
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
      CoolsuppliesPersistence.save();
    }catch (Exception e){
      error = e.getMessage();
    }
    return error;
  }

  /**
   * The getBundle method finds a specified Bundle by name and returns
   * relevant data about it if found.
   *
   * @author Doddy Yang Qiu
   * @param name The name of the GradeBundle to retrieve.
   * @return a TOGradeBundle.
   * @see ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle
   */
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

  /**
   * The getBundles method returns a list of TOGradeBundle corresponding
   * to the list of Bundles in the system.
   *
   * @author Doddy Yang Qiu
   * @return a list of TOGradeBundle.
   * @see ca.mcgill.ecse.coolsupplies.controller.TOGradeBundle
   */
  public static List<TOGradeBundle> getBundles() {
    List<TOGradeBundle> list = new ArrayList<TOGradeBundle>();
    for (GradeBundle bundle : coolSupplies.getBundles()){
      list.add(new TOGradeBundle(bundle.getName(), bundle.getDiscount(), bundle.getGrade().getLevel()));
    }
    return list;
  }

}
