package com.codecool;

import java.util.Random;

public class IDGenerator {
    private final String lettersUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lettersLower = "abcdefghijklmnopqrstuvwxyz";
    private final String specialCharacters = "^!$%&/()=?{[]}+~#-_.:,<>";
    private final String digits = "0123456789";
    private String allCharacters = lettersLower + lettersUpper + specialCharacters + digits;
    private final int IDLength = 8;

    public String generateID() {
        Random random = new Random();
        StringBuilder randomID = new StringBuilder();

        for(int i = 0; i < IDLength; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            randomID.append(allCharacters.charAt(randomIndex));
        }
        return randomID.toString();
    }
}
