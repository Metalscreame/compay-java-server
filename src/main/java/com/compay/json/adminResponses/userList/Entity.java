package com.compay.json.adminResponses.userList;

public class Entity {
    public int id;
    public String email;
    public String name;
    public String lastName;
    public String role;

    public Entity(int id, String email, String name, String lastName, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }
}
