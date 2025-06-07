package com.bank.util;

public class Validator {

    // Account Number: Must be alphanumeric, at least 3 characters
    public static boolean isValidAccountNumber(String accNo) {
        return accNo != null && accNo.matches("^[a-zA-Z0-9]{3,}$");
    }

    // Name: Only letters and spaces, at least two characters
    public static boolean isValidName(String name) {
        return name != null && name.matches("^[A-Za-z ]{2,}$");
    }

    // Balance: Must be parsable to double and >= 0
    public static boolean isValidBalance(String input) {
        try {
            double val = Double.parseDouble(input);
            return val >= 0.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Menu Option: Between min and max inclusive
    public static boolean isValidMenuOption(String input, int min, int max) {
        try {
            int val = Integer.parseInt(input);
            return val >= min && val <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
