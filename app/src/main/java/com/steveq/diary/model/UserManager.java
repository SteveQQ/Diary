package com.steveq.diary.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steveq.diary.controller.LoginActivity;
import com.steveq.diary.controller.NotesActivity;

import org.jasypt.util.text.BasicTextEncryptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {

    private static UserManager instance = null;

    private Context mContext;
    private static Set<String> mUserNames;
    private SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mSharedPreferencesEditor;
    private Gson gson;
    private BasicTextEncryptor mEncryptor;
    private String currentUser;

    protected UserManager(Context ctx){
        mContext = ctx;
        mUserNames = new HashSet<>();
        mSharedPreferences = mContext.getSharedPreferences("myUsers", Activity.MODE_PRIVATE);
        mSharedPreferencesEditor = mSharedPreferences.edit();
        gson = new Gson();
        mEncryptor = new BasicTextEncryptor();
        mEncryptor.setPassword("2851#%!dsga1@$!%$");
    }

    public static UserManager getInstance(Context ctx){
        if(instance == null){
            instance = new UserManager(ctx);
        }
        return instance;
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
    public boolean changePassword(String password, String newPassword, Context ctx){
        if(validate(newPassword) && !newPassword.equals(password)){
            User user = loadUser(currentUser);
            user.setPassword(newPassword);
            saveUser(currentUser, user);
            Toast.makeText(ctx, "Password Changed", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public boolean createNewUser(String username, String password, Context ctx){
        if(credentialValidation(username, password) && !mUserNames.contains(username)){
            saveUser(username, new User(username, password));
            registerUser(username);
            Toast.makeText(ctx, "User added", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(ctx, "User already exists", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean logIn(String username, String password, Context ctx){
        if(credentialValidation(username, password) && mUserNames.contains(username)){
            if(passwordMatches(loadUser(username), password)){
                currentUser = username;
                Intent intent = new Intent(mContext, NotesActivity.class);
                mContext.startActivity(intent);
                return true;
            } else {
                Toast.makeText(ctx, "Wrong Password", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(ctx, "No such user", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean logOut(Context ctx){
        Intent intent = new Intent(ctx, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);
        return true;
    }

    public boolean removeUser(String password, Context ctx){
        if(credentialValidation(currentUser, password) && mUserNames.contains(currentUser)){
            if(passwordMatches(loadUser(currentUser), password)){
                deleteUser(currentUser);
                Toast.makeText(ctx, "User Removed", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(ctx, "Wrong Password", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(ctx, "Wrong Credentials", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void addNote(Note note){
        User user = loadUser(currentUser);
        user.addNote(note);
        saveUser(currentUser, user);
    }

    public ArrayList<Note> showNotes(){
        return loadUser(currentUser).getNoteList();
    }

    public Note showNote(int index) {
        return loadUser(currentUser).getNoteList().get(index);
    }

    public boolean removeNote(int position){
        User user = loadUser(currentUser);
        user.getNoteList().remove(position);
        saveUser(currentUser, user);
        return true;
    }
    //******USERS SERVICES*****//

    //******HELPER METHODS*****//
    public void initializeUsersList(){
        setUserName((HashSet<String>) mSharedPreferences.getStringSet("usersSet", null));
    }

    private boolean registerUser(String user){
        mUserNames.add(user);
        mSharedPreferencesEditor.putStringSet("usersSet", mUserNames);
        mSharedPreferencesEditor.commit();
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

    private User loadUser(String username){
        String jsonUser = mSharedPreferences.getString(username, "");
        return gson.fromJson(mEncryptor.decrypt(jsonUser), User.class);
    }

    private boolean saveUser(String username, User user){
        String jsonUser = gson.toJson(user);
        mSharedPreferencesEditor.putString(username, mEncryptor.encrypt(jsonUser));
        mSharedPreferencesEditor.commit();
        return true;
    }

    private boolean deleteUser(String username){
        mSharedPreferencesEditor.remove(username);
        mUserNames.remove(username);
        mSharedPreferencesEditor.putStringSet("usersSet", mUserNames);
        mSharedPreferencesEditor.commit();
        return true;
    }
    //******HELPER METHODS*****//
}
