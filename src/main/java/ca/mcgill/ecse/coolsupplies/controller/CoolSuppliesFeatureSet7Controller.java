package ca.mcgill.ecse.coolsupplies.controller; 

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class CoolSuppliesFeatureSet7Controller {

	private static final CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
	
	
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

  public static TOGrade getGrade(String level) {
	  Grade grade = Grade.getWithLevel(level);
	  if (grade == null) { //Check if the grade corresponding to the level exists
	    return null;
	  }
	  
	  return new TOGrade(level); //Return the newly created TOGrade
	  
  }

  // returns all grades
  public static List<TOGrade> getGrades() {
	  List<TOGrade> grades = new ArrayList<TOGrade>(); 
	  
	  for (Grade grade: coolSupplies.getGrades()) { //Extract all the grades from the system and add them in grades
		  String level = grade.getLevel();
		  grades.add(new TOGrade(level));
	  }
	  return grades; 
  }
} 