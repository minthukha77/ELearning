package com.blissstock.mappingSite.entity;

public class AddAdmin {
    String userName;
    String mail;
    String role;

    //Constructors

    public AddAdmin() {
    }

    public AddAdmin(String userName, String mail, String role) {
        this.userName = userName;
        this.mail = mail;
        this.role = role;
        //test
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
