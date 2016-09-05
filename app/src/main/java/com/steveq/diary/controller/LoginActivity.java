package com.steveq.diary.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.steveq.diary.R;
import com.steveq.diary.model.User;
import com.steveq.diary.model.UserManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button mNewUser;
    private Button mLogIn;
    private EditText mUserNameArea;
    private EditText mPasswordArea;
    private UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserManager = new UserManager();

        mUserNameArea = (EditText)findViewById(R.id.username);
        mPasswordArea = (EditText)findViewById(R.id.password);

        mNewUser = (Button)findViewById(R.id.new_user);
        mNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                if(credentialValidation() && !User.USERS.containsKey(username)){
                    User.USERS.put(username, new User(username, password));
                } else {
                    Toast.makeText(LoginActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                }
            }
        });

        mLogIn = (Button)findViewById(R.id.log_in);
        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                if(credentialValidation() && User.USERS.containsKey(username)){
                    if(mUserManager.passwordMatches(username, password)){
                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, NotesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "No such user", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean validate(String input){
        Pattern pattern = Pattern.compile("[a-zA-Z\\d]*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean credentialValidation(){
        String username = mUserNameArea.getText().toString();
        String password = mPasswordArea.getText().toString();
        if(!validate(username) || username.equals("")) {
            Toast.makeText(LoginActivity.this, "Insert valid User Name", Toast.LENGTH_LONG).show();
        } else if(!validate(password) || password.equals("")){
            Toast.makeText(LoginActivity.this, "Insert valid Password", Toast.LENGTH_LONG).show();
        } else {
            return true;
        }
        return false;
    }
}
