/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oop_assignment;

/**
 *
 * @author teckann
 */
public class Validation {
    public static boolean icNumber(String input) {
        int lengthOfInput = input.length();
                
        if (lengthOfInput == 12) {
            for (int i = 0; i < lengthOfInput; i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean contactNumber(String input) {
        int lengthOfInput = input.length();
                
        if (lengthOfInput == 10 || lengthOfInput == 11) {
            for (int i = 0; i < lengthOfInput; i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean integer(String input) {
        int lengthOfInput = input.length();
                
        for (int i = 0; i < lengthOfInput; i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean string(String input) {
        int lengthOfInput = input.length();
                
        for (int i = 0; i < lengthOfInput; i++) {
            if (Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean email(String input) {
        int checkPoint1 = input.indexOf('@');
        int checkPoint2 = input.lastIndexOf('.');

        // cannot start with @
        if (checkPoint1 > 0) {
            // "." should write after "@"
            if (checkPoint2 > checkPoint1 + 1) {
                // cannot end with "."
                if (checkPoint2 < input.length() - 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean age(String input) {
        try {
            int age = Integer.parseInt(input);
            return age >= 1 && age <= 99;
        } 
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean price(String input) {
        try {
            double price = Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean password(String password) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;
        boolean has8Character = false;

        if (password.length() >= 8) {
            has8Character = true;
        }
        
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSymbol = true;
            }
        }

        return hasUpper && hasLower && hasDigit && hasSymbol && has8Character;
    }
}
