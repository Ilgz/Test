package com.example.player2.models;

public class User {
    private  String name,email,pass,second;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass ;
    }public String getFam() {
        return second;
    }

    public void setFam(String fam) {
        this.second = fam;
    }

    public User(String name, String email, String pass, String fam) {
        this.name = name;
        this.second=fam;

        this.email=email;
        this.pass=pass;

    }

    public  User(){

    }
}

