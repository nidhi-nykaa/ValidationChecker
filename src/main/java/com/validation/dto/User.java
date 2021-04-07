package com.validation.dto;

public class User {
    int age;
    boolean isAdmin;

    public User(int age, boolean isAdmin) {
        this.age = age;
        this.isAdmin = isAdmin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
