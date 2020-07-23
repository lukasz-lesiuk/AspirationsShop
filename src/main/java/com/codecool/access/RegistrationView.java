package com.codecool.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegistrationView {
    void clear(){
        System.out.print("\033[H\033[2J");
        printAnsiFile("ANSI_registration");
    }

    void printAnsiFile(String filename){
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

    void println(String text){
        System.out.println(text);
    }

    void print(String text){
        System.out.print(text);
    }
}
