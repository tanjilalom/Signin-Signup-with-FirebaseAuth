package com.example.signupsigninloginwithfirebase;

public class signuphelper {

    String name, email, phn, pass;

    public signuphelper() {
    }

    public signuphelper(String name, String email, String phn, String pass) {
        this.name = name;
        this.email = email;
        this.phn = phn;
        this.pass = pass;
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

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
