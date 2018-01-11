package com.compay.json.adminResponses.userList;

public class Entity {
    public int id;
    public String email;
    public String name;
    public String role;

    public Entity(int id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
