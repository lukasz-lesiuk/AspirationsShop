package com.codecool.view;

import com.codecool.customer.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;


public class basicView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    Scanner scan = new Scanner(System.in);

    public void printCustomer(Customer customer) {
        StringBuilder customerString = new StringBuilder();
        Field[] fields = customer.getClass().getDeclaredFields();
        customerString.append(customer.getCustomerId());
        customerString.append(" | ");
        customerString.append(customer.getFirstName());
        customerString.append(" | ");
        customerString.append(customer.getLastName());
        customerString.append(" | ");
        customerString.append(customer.getPhoneNumber());
        customerString.append(" | ");
        customerString.append(customer.getEmailAddress());
        customerString.append(" | ");
        customerString.append(customer.getCity());
        customerString.append(" | ");
        customerString.append(customer.getStreet());
        System.out.println(customerString.toString());
    }

    public void printOptions(List<String> optionsList, String message) {

        String logo =ANSI_YELLOW + "" + ANSI_YELLOW + "\n\n";
        printAnsiFile(   "ANSI_Aspiration_Shop");

        System.out.print(logo.replaceAll("xx","\\\\"));
        printMessage(message);
        int changeIndex = 1;
        try {
            for (int index = 0; index < optionsList.size(); index++) {
                printMessage(ANSI_BLUE + "(" + (index+changeIndex) + ") " + optionsList.get(index) + ANSI_BLUE);
            }
        }catch (IndexOutOfBoundsException e) {
            printError("Nothing to print");
        }
    }

    public void printSubmenu(List<String> optionsList, String message) {
        printMessage(message);
        int changeIndex = 1;
        try {
            for (int index = 0; index < optionsList.size(); index++) {
                printMessage(ANSI_BLUE + "(" + (index+changeIndex) + ") " + optionsList.get(index) + ANSI_BLUE);
            }
        }catch (IndexOutOfBoundsException e) {
            printError("Nothing to print");
        }
    }

    public void printMessage(String message) {
        System.out.println(ANSI_BLUE + message + ANSI_BLUE);
    }

    public void printError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RED);
    }

    public void clear(){
        System.out.print("\033[H\033[2J");
    }

    public String getTextInput(String message){
        System.out.println(message);
        String input = new Scanner(System.in).nextLine();
        clear();
        return input;
    }

    public int getNumericInput(String message) {
        int option = 0;

        printMessage(message + ":");
        try {
            option = scan.nextInt();
        } catch (InputMismatchException e) {
            scan.next();
        }
        return option;
    }

    public void pressEnter() {
        Scanner scan = new Scanner(System.in);
        printMessage("Press ENTER...");
        String pressing = scan.nextLine();
    }

    public void printAnsiFile(String filename){
        try {
            String filepath = "src/main/resources/" + filename;
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
