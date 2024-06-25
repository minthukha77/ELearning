package com.blissstock.mappingSite.validation.constrains;

import lombok.Getter;

@Getter
public abstract class PasswordData {
    private String password;
    private String confirmPassword;

    //Constructors

    public PasswordData() {
    }

    public PasswordData(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
