package com.codecool.access;

public class RegistrationController {
    RegistrationView view;

    RegistrationController() {
        this.view = new RegistrationView();
    }

    public void run(){
        view.clear();
    }
}
