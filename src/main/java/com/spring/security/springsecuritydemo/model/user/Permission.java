package com.spring.security.springsecuritydemo.model.user;

/**
 * Enum which indicates rights of our site's users
 * @autor Dmitriy Savenkov
 * @version 1.0
 */


public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_CHANGE("developers:change");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
