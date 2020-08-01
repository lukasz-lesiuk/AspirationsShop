package com.codecool.access;

import com.codecool.view.BasicView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegistrationView extends BasicView {
    @Override
    public void clear(){
        System.out.print("\033[H\033[2J");
        printAnsiFile("ANSI_registration");
    }
}
