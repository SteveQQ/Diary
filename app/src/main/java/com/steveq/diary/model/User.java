package com.steveq.diary.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String mUserName;
    private String mPassword;
    private static Set<User> USERS;

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mUserName.hashCode();
        result = 31 * result + mPassword.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object){
        User user = (User)object;
        return this.mUserName.equals(user.getUserName());
    }
}
