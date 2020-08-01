package com.codecool.access;

import com.codecool.view.BasicView;

public class LoginView extends BasicView {
    private static final int USERNAME_HEIGHT = 6;
    private static final int USERNAME_COLUMN = 11;
    private static final int PASSWORD_HEIGHT = 8;
    private static final int PASSWORD_COLUMN = 11;

    public void displayLoginScreen() {
        clear();
        System.out.println();
        printMessage("Username: \n");
        printMessage("Password: \n");
        System.out.print("\033[" + USERNAME_HEIGHT + ";" + USERNAME_COLUMN + "H");
    }

    @Override
    public void clear(){
        System.out.print("\033[H\033[2J");
        printAnsiFile("ANSI_login");
    }

    public void moveToPswd() {
        System.out.println("\033[" + PASSWORD_HEIGHT + ";" + PASSWORD_COLUMN + "H");
    }
}
