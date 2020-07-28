package com.codecool.app;

import com.codecool.view.BasicView;

import java.util.ArrayList;
import java.util.List;

public class RootView extends BasicView {
    private List<String> messages;

    public RootView(){
        messages = new ArrayList<>();
    }

    void displayMainMenu(List<String> options){
        clear();
        printOptions(options, "Welcome in our shop!");
        printMessage("(0) Exit");
        for (String message : messages) {
            printMessage(message);
        }
        clearMessages();
    }

    private void clearMessages() {
        this.messages.clear();
    }

    public void addMessage(String newMessage){
        this.messages.add(newMessage);
    }
}
