package com.codecool.dao;

public enum ColumnPublic {
    ID(0), FIRST_NAME(1), LAST_NAME(2), PHONE_NUMBER(3), EMAIL(4), CITY(5), STREET(6);

    protected final int position;

    ColumnPublic(int position) {
        this.position = position;
    }
}
