package com.codecool.access;

import com.codecool.view.basicView;

public class RegistrationView extends basicView {
    @Override
    public void clear(){
        System.out.print("\033[H\033[2J");
        printAnsiFile("ANSI_registration");
    }
}
