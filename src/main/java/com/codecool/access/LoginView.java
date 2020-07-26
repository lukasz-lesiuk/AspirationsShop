package com.codecool.access;

import com.codecool.view.basicView;

public class LoginView extends basicView {
    public void displayLoginScreen() {
        clear();
        System.out.println();
        printMessage("Username: \n");
        printMessage("Password: \n");
        System.out.println("\033[10;5H");
    }

    @Override
    public void clear(){
        System.out.print("\033[H\033[2J");
        printAnsiFile("ANSI_login");
    }

    public void moveToPswd() {
        System.out.println("\033[10;7H");
    }
}
