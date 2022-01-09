package com.example.model;

public class Account {
    private int AccountId;
    private String Email;
    private String Password;
    private String DisplayName;

    public Account(int accountId, String email, String password, String displayName) {
        AccountId = accountId;
        Email = email;
        Password = password;
        DisplayName = displayName;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }


}