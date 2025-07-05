package com.example.servingwebcontent.models;

/**
 * Simple user entity holding id, username and role.
 */
public class User {
    private int id;
    private String username;
    private Role role;

    public User(int id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}