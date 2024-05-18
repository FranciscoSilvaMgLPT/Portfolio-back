package com.example.db.model;

import lombok.Getter;

@Getter
public enum AnimalType {
    YOU("You"),
    SHREK("Shrek"),
    HUMAN_SHREK("Human Shrek"),
    FIONA("Princess Fiona"),
    OGRO_FIONA("Fiona"),
    DONKEY("Donkey"),
    DRAGON("Dragon"),
    FAIRY_GODMOTHER("Fairy Godmother"),
    RAT("Rat"),
    HORSE("Horse"),
    GINGERBREAD_COOKIE("Gingerbread Cookie"),
    PINOCCHIO("Pinocchio"),
    LORD_FARQUAAD("Lord Farquaad"),
    PRINCE_CHARMING("Prince Charming"),
    PUSS_IN_BOOTS("Puss in Boots");

    private final String displayName;

    AnimalType(String displayName) {
        this.displayName = displayName;
    }

}
