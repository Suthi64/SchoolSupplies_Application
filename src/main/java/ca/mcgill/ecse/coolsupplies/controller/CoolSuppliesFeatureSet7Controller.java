package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class CoolSuppliesFeatureSet7Controller {

	private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
	
	
  public static String addGrade(String level) {
    
    if (level == null) {
    	return "The grade level must not be null.";
    }
    if (level.isEmpty()) {
    	return "The grade level must not be empty.";
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
    	return "The grade level must not be null.";
    }
    if (level.isEmpty()) {
    	return "The grade level must not be empty.";
    }
    
    Grade grade = Grade.getWithLevel(level); //Get the grade object to be updated
    if (grade == null) {
    	return "The grade does not exist.";
    }
    
    
    if (newLevel == null) {
    	return "The new grade level must not be null.";
    }
    if (newLevel.isEmpty()) {
    	return "The new grade level must not be empty.";
    }
    
    Grade newGrade = Grade.getWithLevel(newLevel); //Create a grade object to replace the "old" grade
    if (newGrade == null){
    	return "The new grade does not exist.";
    }
    
	try {
    	grade.delete();
    	coolSupplies.addGrade(newGrade);
    } catch(RuntimeException e) {
    	return e.getMessage(); //Error during run time
    }
    
    
    return ""; //The grade was successfully updated 
  }

  public static String deleteGrade(String level) {
    
    if (level == null) {
    	return "The grade level must not be null.";
    }
    if (level.isEmpty()) {
    	return "The grade level must not be empty.";
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
