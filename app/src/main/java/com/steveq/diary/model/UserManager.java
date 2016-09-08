package com.steveq.diary.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steveq.diary.controller.LoginActivity;
import com.steveq.diary.controller.NotesActivity;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {

    private Context mContext;
    private static Set<String> mUserNames;
    private SharedPreferences mSharedPreferences;
    private Gson gson;

    public UserManager(Context ctx){
        mContext = ctx;
        mUserNames = new HashSet<>();
        mSharedPreferences = ctx.getSharedPreferences("myUsers", Activity.MODE_PRIVATE);
        gson = new Gson();
    }

    //******GETTERS SETTERS******//
    public Set<String> getUserNames() {
        return mUserNames;
    }

    public void setUserName(HashSet<String> input) {
        if(input != null) {
            mUserNames.addAll(input);
        }

    }
    //******GETTERS SETTERS******//

    //******USERS SERVICES*****//
    public boolean createNewUser(String username, String password){
        if(credentialValidation(username, password) && !mUserNames.contains(username)){
            String jsonUser = gson.toJson(new User(username, password));

            SharedPreferences.Editor mSharedPreferencesEditor = mSharedPreferences.edit();
            mSharedPreferencesEditor.putString(username, jsonUser);
            mSharedPreferencesEditor.commit();
            registerUser(username);
            mSharedPreferencesEditor.putStringSet("usersSet", mUserNames);
            mSharedPreferencesEditor.commit();

            Toast.makeText(mContext, "User added", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(mContext, "User already exists", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean logIn(String username, String password){
        if(credentialValidation(username, password) && mUserNames.contains(username)){
            String jsonUser = mSharedPreferences.getString(username, "");
            User user = gson.fromJson(jsonUser, User.class);
            if(passwordMatches(user, password)){
                Intent intent = new Intent(mContext, NotesActivity.class);
                intent.putExtra("username", username);
                mContext.startActivity(intent);
                return true;
            } else {
                Toast.makeText(mContext, "Wrong Password", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(mContext, "No such user", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean removeUser(String username, String password){
        if(credentialValidation(username, password) && mUserNames.contains(username)){
            String jsonUser = mSharedPreferences.getString(username, "");
            User user = gson.fromJson(jsonUser, User.class);
            if(passwordMatches(user, password)){
                SharedPreferences.Editor mSharedPreferencesEditor = mSharedPreferences.edit();
                mSharedPreferencesEditor.remove(username);
                mSharedPreferencesEditor.commit();
                mUserNames.remove(username);
                Toast.makeText(mContext, "User Removed", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(mContext, "Wrong Password", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(mContext, "Wrong Credentials", Toast.LENGTH_LONG).show();
            return false;
        }
    }
    //******USERS SERVICES*****//

    //******HELPER METHODS*****//
    public void initializeUsersList(){
        setUserName((HashSet<String>) mSharedPreferences.getStringSet("usersSet", null));
    }

    private boolean registerUser(String user){
        mUserNames.add(user);
        return true;
    }

    private boolean passwordMatches(User user, String password){
        return user.getPassword().equals(password);
    }


    private boolean validate(String input){
        Pattern pattern = Pattern.compile("[a-zA-Z\\d]*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean credentialValidation(String username, String password){
        if(!validate(username) || username.equals("")) {
            Toast.makeText(mContext, "Insert valid User Name", Toast.LENGTH_LONG).show();
        } else if(!validate(password) || password.equals("")){
            Toast.makeText(mContext, "Insert valid Password", Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }
    //******HELPER METHODS*****//
}
