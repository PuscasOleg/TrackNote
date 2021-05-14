package com.example.tracknote.Models;

public class User {
    private String FullName, Email, PassWord, StartAdress, EndAdress,UserId;

    public User() {

    }

    public User(String fullName, String email, String passWord) {
        FullName = fullName;
        Email = email;
        PassWord = passWord;
        StartAdress = "";
        EndAdress = "";
        UserId="";
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getStartAdress() {
        return StartAdress;
    }

    public void setStartAdress(String startAdress) {
        StartAdress = startAdress;
    }

    public String getEndAdress() {
        return EndAdress;
    }

    public void setEndAdress(String endAdress) {
        EndAdress = endAdress;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}