package com.codecool.access;

public class PasswordChecker {
    public boolean isPasswordGood(String password) {
        boolean result = true;
        if (password.length()<8 || !hasDifferentChars(password)){
            result = false;
        }
        return result;
    }

    private boolean hasDifferentChars(String password) {
        int hasDigit = 0;
        int hasUpper = 0;
        int hasLower = 0;
        int hasSpecial = 0;
        char[] chars = password.toCharArray();

        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                hasDigit = 1;
            } else if (Character.isLowerCase(aChar)){
                hasLower = 1;
            } else if (Character.isUpperCase(aChar)){
                hasUpper = 1;
            } else if (!Character.isLetterOrDigit(aChar)){
                hasSpecial = 1;
            }
        }

        int sum = hasDigit + hasLower + hasUpper + hasSpecial;
        return sum >= 3;
    }
}
