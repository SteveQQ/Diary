package com.steveq.diary.model;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String mUserName;
    private String mPassword;
    public static Set<User> USERS = new HashSet<>();

    public User(String username, String password){
        mUserName = username;
        mPassword = password;
        USERS.add(this);
        Log.d("SET", USERS.iterator().next().toString());
        Log.d("SET", USERS.size()+"");
    }

    public String getPassword() {
        return mPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    @Override
    public String toString(){
        return mUserName;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mUserName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object object){
        User user = (User)object;
        return this.mUserName.equals(user.getUserName());
    }
}
