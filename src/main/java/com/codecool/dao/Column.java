package com.codecool.dao;

public enum Column {
    ID(0), FIRST_NAME(1), LAST_NAME(2), PHONE_NO(3), EMAIL(4), CITY(5), STREET(6), HASH(7);
    protected int position;
    Column(int position) {
        this.position = position;
    }
}
