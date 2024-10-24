package ca.mcgill.ecse.coolsupplies.controller; 

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;

import java.util.ArrayList;
import java.util.List;


/**
*This class manages the grade entities in the Cool Supplies application
* This class contains methods to add, update, delete and retrieve Grade
* @author Moustapha El Zein
* 
*/
public class CoolSuppliesFeatureSet7Controller {

	private static final CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
	
	
	/**
 * This method adds a Grade to the Cool Supplies database
 * 
 * @param level the Grade level to be added
 * @return an error message if the operation failed
 * 				 Returns an empty string if it was successfully added
 * @since 1.0
 */
  public static String addGrade(String level) {
    
    if (level == null) {
    	return "The level must not be null.";
    }
    if (level.isEmpty()) {
    	return "The level must not be empty.";
    }

		// Check if the grade level already exists
    for (Grade existingGrade : coolSupplies.getGrades()) {
			if (existingGrade.getLevel().equals(level)) {
					return "The level must be unique."; // Ensure this matches your Cucumber test
			}
	}
    
	try {
		    coolSupplies.addGrade(level);
	} catch (RuntimeException e) {
		return e.getMessage(); //Error during run time
		}
	return ""; //The grade was successfully added
  }


	/**
 * This method updates a Grade level in the Cool Supplies database
 * 
 * @param level the Grade level to be updated
 * @param newLevel the updated Grade level
 * @return an error message if the operation failed
 * 			   Returns an empty string if it was successfully updated
 * @since 1.0
 */
  public static String updateGrade(String level, String newLevel) {
    
    if (level == null) {
    	return "The level must not be null.";
    }

		boolean levelExists = false; //Flag to check if level to be updated exists
    for (Grade existingGrade : coolSupplies.getGrades()) {
			if (existingGrade.getLevel().equals(newLevel)) { //Check if the updatedLevel already exists in the database
				return "The level must be unique.";
			}
			else if (existingGrade.getLevel().equals(level)) { //Check if the level to be updated  exists in the database
					levelExists = true;
					break; //The level exists in the database
			}
		}
		if (!levelExists) {
			return "The grade does not exist."; //Level to be updated does not exist in the first place
		}
    

    //Check errors with the new updated level
    if (newLevel == null) {
    	return "The level must not be null.";
    }
    if (newLevel.isEmpty()) {
    	return "The level must not be empty.";
    }
    
	try {
			Grade grade = Grade.getWithLevel(level); //Get the grade object to be updated
    	grade.setLevel(newLevel); //update the level
    } catch(RuntimeException e) {
    	return e.getMessage(); //Error during run time
    }
    
    
    return ""; //The grade was successfully updated 
  }


	/**
 * This method deletes a Grade from the Cool Supplies database
 * 
 * @param level the Grade level to be deleted
 * @return an error message if the operation failed
 * 				 Returns an empty string if it was successfully deleted
 * @since 1.0
 */
  public static String deleteGrade(String level) {
    
    if (level == null) {
    	return "The level must not be null.";
    }
    if (level.isEmpty()) {
    	return "The level must not be empty.";
    }
    
	Grade grade = Grade.getWithLevel(level); //Get grade to be deleted
	if (grade == null) {
    	return "The grade does not exist.";
    }
   
	try {
		grade.delete();
	}
	catch (RuntimeException e) {
		return e.getMessage(); //Error during run time
	}  
    
    return ""; //The grade was successfully deleted 
  }


	/**
 * This method retrieves a Grade level from the database and creates a transfer object representing it
 * 
 * @param level the Grade level to be retrieved
 * @return a transfer object of the retrieved Grade from the database
 * @since 1.0
 */
  public static TOGrade getGrade(String level) {
	  Grade grade = Grade.getWithLevel(level);
	  if (grade == null) { //Check if the grade corresponding to the level exists
	    return null;
	  }
	  
	  return new TOGrade(level); //Return the newly created TOGrade
	  
  }

  /**
 * This method retrieves all Grade levels from the database and creates a list of transfer object representing them
 * 
 * @return a transfer object list of all the existing Grades in the database
 * @since 1.0
 */
  public static List<TOGrade> getGrades() {
	  List<TOGrade> grades = new ArrayList<TOGrade>(); 
	  
	  for (Grade grade: coolSupplies.getGrades()) { //Extract all the grades from the system and add them in grades
		  String level = grade.getLevel();
		  grades.add(new TOGrade(level));
	  }
	  return grades; 
  }
} 