package com.codecool.access;

import com.codecool.customer.Customer;
import com.codecool.dao.CustomerDAO;
import com.codecool.dao.CustomerDAOPSQL;

import java.io.Console;
import java.util.Scanner;

public class AdminLoginController {
    private static final String HASHED_MASTER_PASSWORD = "385428196";
    private final AdminLoginView view;
    private final Scanner scan;
    private final Console console;

    public AdminLoginController() {
        this.view = new AdminLoginView();
        this.scan = new Scanner(System.in);
        this.console = System.console();
    }

    public boolean checkAdminPassword(){
        String password;
        String input = "default";
        do {
            view.displayLoginScreen();
            password = new String(console.readPassword());
            password = Integer.toString(password.hashCode());
            if (!password.equals(HASHED_MASTER_PASSWORD)){
                view.printMessage("Wrong password. Press Enter and try again or enter q to cancel.");
                input = scan.nextLine();
            }
        } while (isLoginNotComplete(password, input));
        return !(input.equals("q") || input.equals("Q"));
    }

    private boolean isLoginNotComplete(String password, String input){
        boolean output = true;

        if (password.equals(HASHED_MASTER_PASSWORD)) {
            output = false;
        } else if (input.equals("q") || input.equals("Q")) {
            output =false;
        }

        return output;
    }
}
