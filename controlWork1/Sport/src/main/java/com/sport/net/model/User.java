package com.sport.net.model;

public class User {

    private int id;
    private String login ;
    private String password;
    private String key;
    private String salt;

    public User(int id, String login, String password, String key, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.key = key;
        this.salt = salt;
    }

    public User(int id, String login, String key, String salt) {
        this.id = id;
        this.login = login;
        this.key = key;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
