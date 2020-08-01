package com.codecool.access;

import java.io.Console;
import java.util.Scanner;

public class PasswordGenerator {
    private final RegistrationView view;
    private final Scanner scan;
    private final Console console;

    public PasswordGenerator () {
        this.view = new RegistrationView();
        this.scan = new Scanner(System.in);
        this.console = System.console();
    }


    public String getPassword() {
        String password;
        String passwordConfirm;
        PasswordChecker checker = new PasswordChecker();
        do {
            password = new String(console.readPassword("Enter password"));
            while (!checker.isPasswordGood(password)) {
                view.printMessage("This password didn't meet the policy requirements.");
                password = new String(console.readPassword("Enter password"));
            }
            passwordConfirm = new String(console.readPassword("Enter password again"));
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
        return input;
    }
}
