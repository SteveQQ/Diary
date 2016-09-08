package com.steveq.diary.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.steveq.diary.R;
import com.steveq.diary.model.User;
import com.steveq.diary.model.UserManager;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText mUserNameArea;
    private EditText mPasswordArea;
    private Button mLogIn;
    private Button mNewUser;
    private Button mReomveUser;

    private UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserNameArea = (EditText)findViewById(R.id.username);
        mPasswordArea = (EditText)findViewById(R.id.password);
        mNewUser = (Button)findViewById(R.id.new_user);
        mLogIn = (Button)findViewById(R.id.log_in);
        mReomveUser = (Button)findViewById(R.id.remove_user);

        mUserManager = new UserManager(this);
        mUserManager.initializeUsersList();

        mNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                mUserManager.createNewUser(username, password);

            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                mUserManager.logIn(username, password);
                cleanEdits();
            }
        });

        mReomveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                mUserManager.removeUser(username, password);
                cleanEdits();
            }
        });

    }


    private boolean cleanEdits(){
        mUserNameArea.setText("");
        mPasswordArea.setText("");
        return true;
    }
}
