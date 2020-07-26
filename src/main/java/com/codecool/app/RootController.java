package com.codecool.app;

import com.codecool.access.LoginController;
import com.codecool.access.RegistrationController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RootController {
    RootView view = new RootView();
    Scanner scan = new Scanner(System.in);
    List<String> options;

    public void run() {
        prepareMenu();
        String choice;
        do {
            view.displayMainMenu(options);
            choice = scan.nextLine();
            switch (choice){
                case ("1"):
                    login();
                    break;
                case ("2"):
                    register();
                    break;
                case ("3"):
                    // You can add any function you wish to test here
                    break;
                case ("okon"):
                    // place to hide a 'secret' functionality
                    break;
                default:
                    view.addMessage("Option not on the list.");
            }
        } while (!choice.equals("0"));
    }

    private void prepareMenu() {
        this.options = new ArrayList<>();
        options.add("Login");
        options.add("Register");
        options.add("Test");
    }

    private void login() {
        LoginController logCon = new LoginController();
        logCon.run();
    }

    private void register() {
        RegistrationController regCon = new RegistrationController();
        regCon.run();
    }
}
