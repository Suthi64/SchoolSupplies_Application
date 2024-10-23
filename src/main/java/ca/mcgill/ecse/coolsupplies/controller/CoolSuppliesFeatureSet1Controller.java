package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Parent;
import ca.mcgill.ecse.coolsupplies.model.User;
import ca.mcgill.ecse.coolsupplies.model.SchoolAdmin;

import java.util.List;
import java.util.ArrayList;


/** 
 * This is the main controller implementation for FeatureSet1 of the CoolSuppliesApplication
 * It contains methods relating to manipulating Admin and Parent objects
 * 
 * @author Sanad Abu Baker
*/
public class CoolSuppliesFeatureSet1Controller {


    private static CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
    
    /*
     * Helper methods for identifying Parent from list of parents, and to help in validating emails.
     */
    
    private static boolean containsUpperCase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowerCase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Updating the admin's password.
     * 
     * @param password    The new password entered to be updated. If the password is less than the minimum length and doesn't contain the appropriate characters an error message is returned.
     * 
     * @return      A string indicating if the operation was successful or not.
     */
    public static String updateAdmin(String password) {
        SchoolAdmin admin = coolSupplies.getAdmin();
        
        if (!admin.getEmail().equals("admin@cool.ca")) {
            return "The admin does not exist";
          }

        // Validate Password length requirements
        if (!(password.length() > 3)) {
            return "Password must be at least four characters long.";
        }
        // Validate Password special character requirements
        if (!(password.contains("!") || password.contains("#") || password.contains("$"))) {
            return "Password must contain a special character out of !#$, an upper case character, and a lower case character.";
          }

        // Validate Password Uppercase requirement
        if (!containsUpperCase(password)){
          return "Password must contain a special character out of !#$, an upper case character, and a lower case character.";
        }

        // Validate Password Lowercase requirement
        if(!containsLowerCase(password)){
          return "Password must contain a special character out of !#$, an upper case character, and a lower case character.";
        }

        try {
          admin.setPassword(password);
        } catch (RuntimeException e) {
          return e.getMessage();
        }
        return "";
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    /*
     * Method to add a new Parent with fields of email, password, name, and phone number. This method also adds the created parent to our initialized array of parents.
     * 
     * @param email         This email must be checked against various character constraints and if it's already taken.
     * @param password      The password must not be empty or null.
     * @param name          The name must not be empty or null.
     * @param phoneNumber   The phone number must be 7 digits long and cannot contain trailing zeroes.
     * 
     * @return              A string indicating if the operation was successful or not.
     */
    public static String addParent(String email, String password, String name, int phoneNumber) {
        
      
      if (email.contains(" ")) {
            return "The email must not contain spaces.";
          }
      
          if (email.equals("admin@cool.ca")) { // admin's email cannot be taken.
            return "The email must not be admin@cool.ca.";
          }
      
          if (!(email.indexOf("@") > 0)) {
            return "The email must be well-formed.";
          }
      
          if (!((email.indexOf("@") == email.lastIndexOf("@"))
              && (email.lastIndexOf(".") < email.length() - 1))) {
            return "The email must be well-formed.";
          }
          if (!(email.indexOf("@") < email.indexOf(".") - 1)) {
            return "The email must be well-formed.";
          }
          if (password == null) {
            return "The password must not be null";
          }
          if (password.equals("")) {
            return "The password must not be empty.";
          }
          if (name == null) {
            return "The name must not be null.";
          }

          if (name.isEmpty()) {
            return "The name must not be empty.";
          }
      
          if (!(phoneNumber < 10000000 && phoneNumber > 999999)) {
            return "The phone number must be seven digits.";
          }
      
          try {
            coolSupplies.addParent(email, password, name, phoneNumber);
          }catch(RuntimeException e) {
            var error = e.getMessage();
            if(error.startsWith("Cannot create due to duplicate email")){
              error = "The email must be unique.";
            }
            return error;
          }
          return "";
    }

    /*
     * This method is used to update the various fields of the Parent object such as the email, password, name, and phone number.
     * 
     * @param email           The email/username of the parent. The email cannot be changed and is thus used to perform checks such as making sure the entered email corresponds to an existing parent.
     * @param newPassword     The new password of the parent. This password cannot be null or empty and we thus perform a check for that.
     * @param newName         The new name of the parent. The name cannot be null or empty and we thus perform a check for that.
     * @param newPhoneNumber  The new phone number of the parent. The new phone number must be exactly 7 digits and cannot have trailing zeroes (1000000).
     * 
     * @return                A string indicating if the operation was successful.
     */
    public static String updateParent(String email, String newPassword, String newName, int newPhoneNumber) {
        var error = "";
        
        // Find parent by email. If parent not found, function returns an error message.
        Parent parent = (Parent) User.getWithEmail(email);
        if (parent == null) {
            error = "The parent does not exist.";
            return error.trim();
        }

        // Validate if newPassword is empty or not.
        if (newPassword == null) {
            error = "The password must not be null.";
            return error.trim();
        }

        if (newPassword.equals("")){
          error = "The password must not be empty.";
          return error.trim();
        }

        // Validate if newName is empty or not.
        if (newName == null) {
            error = "The name must not be null.";
            return error.trim();
        }

        if (newName.equals("")){
          error = "The name must not be empty.";
          return error.trim();
        }
 
        // Validate if newPhoneNumber fits constraints.
        if (!(newPhoneNumber > 9999999 && newPhoneNumber < 10000000)) {
                error = "The phone number must be seven digits.";
                return error.trim();
            }
        
        // Update Parent fields.
        try {
          parent.setPassword(newPassword);
          parent.setName(newName);
          parent.setPhoneNumber(newPhoneNumber);
        } catch(RuntimeException e){
          return e.getMessage();}
        
        return ""; // Success Message
    }


    /*
     * This method is used to delete a parent from the system.
     * 
     * @param email     We use this email to find the parent object in our list of parents
     * 
     * @return      A string indicating if the operation was successful.
     */
    public static String deleteParent(String email) {
        Parent parent = (Parent) User.getWithEmail(email);
        if (parent == null) {
            return "The parent does not exist.";
        }

        try {
        parent.delete();
        } catch (RuntimeException e){
          return e.getMessage();
        }
        return ""; // Success message.
    }

    /*
     * Retrieves the details of a Parent based on their email.
     * 
     * @param email     The email of the parent used to identify them.
     * 
     * @return          TOParent transfer object containng their information.
     */
    public static TOParent getParent(String email) {
      Parent parent = (Parent) User.getWithEmail(email);
    
    
    if (parent == null) {
        throw new IllegalArgumentException("The parent does not exist.");
    }

    // If parent exists, get password and other details.
    String password = parent.getPassword();
    return new TOParent(email, password, parent.getName(), parent.getPhoneNumber());
}
  
  

    /* 
     * Retrieves a list of all parents in the system.
     * 
     * @param none
     * 
     * @return    A list of TOParent transfer objects.
    */
    public static List<TOParent> getParents() {
        List<TOParent> toParents = new ArrayList<TOParent>();

        for (Parent parent : coolSupplies.getParents()) {
            toParents.add(new TOParent(parent.getEmail(), parent.getPassword(), parent.getName(), parent.getPhoneNumber()));
        }

        return toParents;
    }
}
