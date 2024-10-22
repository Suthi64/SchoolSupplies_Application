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

    /*
     * Defining admin object with set username and accessible list of parents
     */
    private static final CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
    private static SchoolAdmin admin = new SchoolAdmin("admin@cool.ca", "admin", coolSupplies); 
    // Predefined admin object
    private static List<Parent> parents = new ArrayList<>(); // List of parents

    /*
     * Helper methods for identifying Parent from list of parents, and to help in validating emails.
     */
    private static Parent findParentByEmail(String email) {
        for (Parent parent : CoolSuppliesApplication.getCoolSupplies().getParents()) {
            if (parent.getEmail().equals(email)) {
                return parent;
            }
        }
        return null;
    }

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
        if (password == null || password.equals("")) {
            return "Password must not be empty or null.";
        }
        if (password.length() < 4) {
            return "Password must be at least 4 characters long.";
        }
        if (!password.contains("!") && !password.contains("#") || !password.contains("$")) {
            return "Password must contain a special character out of !#$.";
        }
        if (!containsUpperCase(password) || !containsLowerCase(password)) {
            return "Password must contain both an uppercase and lowercase character.";
        }

        admin.setPassword(password);
        return "Admin password updated successfully.";
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
            return "Spaces are not allowed in emails!";
        }

        if (email.equals("admin@cool.ca")) { //admin's email cannot be taken.
            return "Email unavailable.";
        }

        if (!(email.indexOf("@") > 0)) {
            return "Email must have a handle.";
        }

        if (!(email.indexOf("@") == email.lastIndexOf("@")) || !(email.lastIndexOf(".") < email.length() - 1)) {
            return "Invalid email format.";
        }

        for (Parent parent : parents) { //checking if entered email is taken.
            if (parent.getEmail().equals(email)) {
                return "A parent with this email already exists.";
            }
        }

        if (password.equals("") || password == null) {
            return "Please enter a password.";
        }

        Parent newParent = new Parent(email, password, name, phoneNumber, coolSupplies);

        if (name != null && !name.equals("")) {
            newParent.setName(name);
        }

        if (phoneNumber < 1000000 || phoneNumber > 9999999) {
            return "Phone number must be 7 digits and cannot have trailing zeroes.";
        }

        parents.add(newParent);
        return "Parent added successfully.";
        // throw new UnsupportedOperationException("Not implemented yet.");
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
        
        // Find parent by email. If parent not found, function returns an error message.
        Parent parent = findParentByEmail(email);
        if (parent == null) {
            return "Parent not found.";
        }

        // Update the password if it is not empty.
        if (newPassword != null && !newPassword.equals("")) {
            parent.setPassword(newPassword);
        }

        // Update the name if it is not empty.
        if (newName != null && !newName.equals("")) {
            parent.setName(newName);
        }
 
        // Update the phone number if it's a valid 7 digit number.
        if (newPhoneNumber > 999999 && newPhoneNo < 10000000) {
                parent.setPhoneNumber(newPhoneNo);
            }
        
        // Success message
        return "Parent updated successfully.";
        // throw new UnsupportedOperationException("Not implemented yet.");
    }


    /*
     * This method is used to delete a parent from the system.
     * 
     * @param email     We use this email to find the parent object in our list of parents
     * 
     * @return      A string indicating if the operation was successful.
     */
    public static String deleteParent(String email) {
        Parent parent = findParentByEmail(email);
        if (parent == null) {
            return "Parent not found.";
        }

        parents.remove(parent);
        return "Parent deleted successfully.";
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    /*
     * Retrieves the details of a Parent based on their email.
     * 
     * @param email     The email of the parent used to identify them.
     * 
     * @return          TOParent transfer object containng their information.
     */
    public static TOParent getParent(String email) {
        Parent parent = findParentByEmail(email);
        if (parent == null) {
            return null;
        }
        return new TOParent(email, parent.getPassword(), parent.getName(), parent.getPhoneNumber());
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    /* 
     * Retrieves a list of all parents in the system.
     * 
     * @param none
     * 
     * @return    A list of TOParent transfer objects.
    */
    public static List<TOParent> getParents() {
        List<TOParent> toParents = new ArrayList<>();

        for (Parent parent : parents) {
            TOParent toParent = new TOParent(parent.getEmail(), parent.getPassword(), parent.getName(), parent.getPhoneNumber());
            toParents.add(toParent);
        }

        return toParents;
        // throw new UnsupportedOperationException("Not implemented yet.");
    }
}
