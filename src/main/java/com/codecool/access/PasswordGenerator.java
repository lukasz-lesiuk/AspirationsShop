package com.codecool.access;

import java.util.Scanner;

public class PasswordGenerator {
    RegistrationView view;
    Scanner scan;

    public PasswordGenerator () {
        this.view = new RegistrationView();
        this.scan = new Scanner(System.in);
    }


    public String getPassword() {
        String password;
        String passwordConfirm;
        PasswordChecker checker = new PasswordChecker();
        do {
            password = input("Enter password");
            while (!checker.isPasswordGood(password)) {
                view.printMessage("This password didn't meet the policy requirements.");
                password = input("Enter password");
            }
            passwordConfirm = input("Enter password again");
            if (!passwordConfirm.equals(password)){
                view.printMessage("Passwords didn't match. Press Enter and try again.");
                scan.nextLine();
            }
        } while (!passwordConfirm.equals(password));

        return Integer.toString(password.hashCode());
    }

    private String input(String message){
        System.out.println(message + ": ");
        String input = scan.nextLine();
//        view.clear();
        return input;
    }
}
