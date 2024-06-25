package com.blissstock.mappingSite.service;

public interface SecurityService {
    public String findLoggedInEmail();
    public void autologin(String email, String password);

}
