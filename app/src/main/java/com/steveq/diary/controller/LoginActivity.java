package com.steveq.diary.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.steveq.diary.R;
import com.steveq.diary.model.UserManager;
public class LoginActivity extends AppCompatActivity {

    private EditText mUserNameArea;
    private EditText mPasswordArea;
    private Button mLogIn;
    private Button mNewUser;
    private UserManager mUserManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserManager = UserManager.getInstance(this);

        mUserNameArea = (EditText)findViewById(R.id.username);
        mPasswordArea = (EditText)findViewById(R.id.password);
        mNewUser = (Button)findViewById(R.id.new_user);
        mLogIn = (Button)findViewById(R.id.log_in);


        mNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                mUserManager.createNewUser(username, password, LoginActivity.this);

            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUserNameArea.getText().toString();
                String password = mPasswordArea.getText().toString();
                mUserManager.logIn(username, password, LoginActivity.this);
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
