package com.steveq.diary.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String mUserName;
    protected String mPassword;
    private ArrayList<Note> mNoteList;
    public static Map<String, User> USERS = new HashMap<>();

    public User(String username, String password){
        mUserName = username;
        mPassword = password;
        mNoteList = new ArrayList<>();
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
        return this.mUserName.equals(mUserName);
    }
}
