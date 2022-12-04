package com.aviatickets.aviatickets.models;

public enum Role {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private final String roleValue;

    private Role(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleValue() {
        return roleValue;
    }
}
