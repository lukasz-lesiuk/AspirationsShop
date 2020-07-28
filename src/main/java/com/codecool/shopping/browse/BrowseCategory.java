package com.codecool.shopping.browse;

public enum BrowseCategory {
    Potions_Base_Game("Potions: Base Game"),
    Potions_Get_famous("Potions: Get Famous"),
    Potions_Realm_Of_Magic("Potions: Realm of Magic"),
    Traits_Base_Game("Traits: Base Game"),
    Traits_Outdoor_Retreat("Traits: Outdoor Retreat"),
    Traits_Seasons("Traits: Seasons"),
    Miscellaneous_Seasons("Miscellaneous: Seasons"),
    Miscellaneous_Island_Living("Miscellaneous: Island Living");

    private String name;

    BrowseCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}