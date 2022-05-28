package com.example.lab3.User;

public class UserEntity {

    private int id;
    private String name;
    private String email;

    public UserEntity(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
