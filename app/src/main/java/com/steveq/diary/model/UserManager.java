package com.steveq.diary.model;

import java.util.Map;

public class UserManager {

    public boolean passwordMatches(String username, String password){
        return User.USERS.get(username).mPassword.equals(password);
    }
}
