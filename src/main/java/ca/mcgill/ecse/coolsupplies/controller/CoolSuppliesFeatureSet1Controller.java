package ca.mcgill.ecse.coolsupplies.controller;

import ca.mcgill.ecse.coolsupplies.application.CoolSuppliesApplication;
import ca.mcgill.ecse.coolsupplies.model.CoolSupplies;
import ca.mcgill.ecse.coolsupplies.model.Parent;
import ca.mcgill.ecse.coolsupplies.model.User;
import ca.mcgill.ecse.coolsupplies.model.SchoolAdmin;

import java.util.List;
import java.util.ArrayList;

public class CoolSuppliesFeatureSet1Controller {

    private static final CoolSupplies coolSupplies = CoolSuppliesApplication.getCoolSupplies();
    private static SchoolAdmin admin = new SchoolAdmin("admin@cool.ca", "admin", coolSupplies); 
    // Predefined admin object
    private static List<Parent> parents = new ArrayList<>(); // List of parents

    // Helper methods
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

    public static String addParent(String email, String password, String name, String phoneNumber) {
        if (email.contains(" ")) {
            return "Spaces are not allowed in emails!";
        }

        if (email.equals("admin@cool.ca")) {
            return "Email unavailable.";
        }

        if (!(email.indexOf("@") > 0)) {
            return "Email must have a handle.";
        }

        if (!(email.indexOf("@") == email.lastIndexOf("@")) || !(email.lastIndexOf(".") < email.length() - 1)) {
            return "Invalid email format.";
        }

        for (Parent parent : parents) {
            if (parent.getEmail().equals(email)) {
                return "A parent with this email already exists.";
            }
        }

        if (password.equals("") || password == null) {
            return "Please enter a password.";
        }

        int phoneNo = Integer.parseInt(phoneNumber);
        Parent newParent = new Parent(email, password, name, phoneNo, coolSupplies);

        if (name != null && !name.equals("")) {
            newParent.setName(name);
        }

        if (phoneNumber != null && !phoneNumber.equals("") && phoneNumber.length() > 6 && !phoneNumber.equals("10000000")) {
            newParent.setPhoneNumber(phoneNo);
        }

        parents.add(newParent);
        return "Parent added successfully.";
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    public static String updateParent(String email, String newPassword, String newName, String newPhoneNumber) {
        Parent parent = findParentByEmail(email);
        if (parent == null) {
            return "Parent not found.";
        }

        if (newPassword != null && !newPassword.equals("")) {
            parent.setPassword(newPassword);
        }
        if (newName != null && !newName.equals("")) {
            parent.setName(newName);
        }

        int newPhoneNo = Integer.parseInt(newPhoneNumber);

        if (newPhoneNumber != null) {
            if (newPhoneNo > 999999 && newPhoneNo < 10000000) {
                parent.setPhoneNumber(newPhoneNo);
            }
        }
        return "Parent updated successfully.";
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    public static String deleteParent(String email) {
        Parent parent = findParentByEmail(email);
        if (parent == null) {
            return "Parent not found.";
        }

        parents.remove(parent);
        return "Parent deleted successfully.";
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    public static TOParent getParent(String email) {
        Parent parent = findParentByEmail(email);
        if (parent == null) {
            return null;
        }
        return new TOParent(email, parent.getPassword(), parent.getName(), parent.getPhoneNumber());
        // throw new UnsupportedOperationException("Not implemented yet.");
    }

    // Returns all parents
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
