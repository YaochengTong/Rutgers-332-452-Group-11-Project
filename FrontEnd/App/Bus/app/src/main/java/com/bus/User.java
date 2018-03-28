package com.bus;

/**
 */

public class User {
    private String ruid;
    private String password;

    public User(String ruid) {
        this.ruid = ruid;
    }

    public User(String ruid, String password) {
        this.ruid = ruid;
        this.password = password;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
