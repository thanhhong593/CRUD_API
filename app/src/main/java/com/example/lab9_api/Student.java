package com.example.lab9_api;

public class Student {
     private String Id;
    private String name;
    private String class1;
    private String email;

    public Student(String id, String name, String class1, String email) {
        Id = id;
        this.name = name;
        this.class1 = class1;
        this.email = email;
    }

    public Student() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return   "Id='" + Id + '\'' + ", name='" + name + '\'' + ", class1='" + class1 + '\'' + ", email='" + email ;
    }
}
