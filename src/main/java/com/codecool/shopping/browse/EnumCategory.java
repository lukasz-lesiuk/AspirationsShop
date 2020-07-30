package com.codecool.shopping.browse;

public enum EnumCategory {

    Basic("Basic"),
    Outdoor_Retreat("Outdoor Retreat"),
    Seasons("Seasons"),
    Miscellaneous("Miscellaneous"),
    Island_Living("Island Living"),
    Realm_of_Magic("Realm of Magic"),
    Get_Famous("Get Famous"),
    Potions("Potions");

    private final String name;

    EnumCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}