package com.sport.net.dto;

public class UserDto {

    private String login;
    private String email;
    private String key;
    private String salt;


    public UserDto(String login, String email, String key, String salt) {
        this.login = login;
        this.email = email;
        this.key = key;
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getKey() {
        return key;
    }

    public String getSalt() {
        return salt;
    }
}
