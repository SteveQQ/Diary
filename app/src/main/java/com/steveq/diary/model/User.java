package com.steveq.diary.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String mUserName;
    private String mPassword;
    private ArrayList<Note> mNoteList;

    public User(String username, String password){
        mUserName = username;
        mPassword = password;
        mNoteList = new ArrayList<>();
    }

    public String getPassword() {
        return mPassword;
    }
    public void setPassword(String newPassword){
        mPassword = newPassword;
    }
    public ArrayList<Note> getNoteList() {
        return mNoteList;
    }

    public void addNote(Note note){
        mNoteList.add(note);
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
