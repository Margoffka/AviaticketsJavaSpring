package com.aviatickets.aviatickets.models;

public enum Sex {
    M("Мужскной"),
    F("Женский");

    private final String sexValue;

    private Sex(String sexValue) {
        this.sexValue = sexValue;
    }

    private String getSexValue() {
        return sexValue;
    }
}
