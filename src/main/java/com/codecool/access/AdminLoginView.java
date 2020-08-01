package com.codecool.access;

import com.codecool.view.BasicView;

public class AdminLoginView extends BasicView {
    private static final int PASSWORD_HEIGHT = 6;
    private static final int PASSWORD_COLUMN = 11;

    public void displayLoginScreen() {
        clear();
        System.out.println();
        printMessage("Password: \n");
        System.out.print("\033[" + PASSWORD_HEIGHT + ";" + PASSWORD_COLUMN + "H");
    }

    @Override
    public void clear(){
        System.out.print("\033[H\033[2J");
        printAnsiFile("ANSI_adminLogin");
    }
}
