package com.codecool.access;

public class PasswordChecker {
    private static final int MINIMAL_PASS_LENGTH= 8;
    private static final int PASSWORD_CHARACTER_GROUPS = 3;

    public boolean isPasswordGood(String password) {
        boolean result = true;
        if (password.length()<MINIMAL_PASS_LENGTH || !hasDifferentChars(password)){
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
        return sum >= PASSWORD_CHARACTER_GROUPS;
    }
}
