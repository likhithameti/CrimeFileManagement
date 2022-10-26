package com.example.crimemanagement;

public class ViewAllUsersClass {
    public String aadhaarnumber;
    public String username;
    public String phonenumber;
    public String fullname;

    public ViewAllUsersClass(){

    }
    public ViewAllUsersClass(String aadhaarnumber, String username, String phonenumber, String fullname) {
        this.aadhaarnumber = aadhaarnumber;
        this.username = username;
        this.phonenumber = phonenumber;
        this.fullname = fullname;
    }

    public String getAadhaarnumber() {
        return aadhaarnumber;
    }

    public void setAadhaarnumber(String aadhaarnumber) {
        this.aadhaarnumber = aadhaarnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
