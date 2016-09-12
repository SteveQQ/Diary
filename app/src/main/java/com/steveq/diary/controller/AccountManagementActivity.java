package com.steveq.diary.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.steveq.diary.R;
import com.steveq.diary.model.UserManager;

public class AccountManagementActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mNewPassword;
    private Button mRemoveUser;
    private Button mChangePassword;
    private Button mChangeUsername;
    private UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        mUsername = (EditText)findViewById(R.id.username);
        mPassword = (EditText)findViewById(R.id.password);
        mNewPassword = (EditText)findViewById(R.id.newPassword);
        mRemoveUser = (Button)findViewById(R.id.remove_user);
        mChangePassword = (Button)findViewById(R.id.change_password);
        mChangeUsername = (Button)findViewById(R.id.change_username);

        mUserManager = UserManager.getInstance(null);

        mRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mPassword.getText().toString();
                mUserManager.removeUser(password,AccountManagementActivity.this);
                mPassword.setText("");
                mUserManager.logOut(AccountManagementActivity.this);
            }
        });

        mChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String newPassword = mNewPassword.getText().toString();
                mUserManager.changePassword(username, password, newPassword, AccountManagementActivity.this);
            }
        });


        mChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                mUserManager.changeUsername(username, password, AccountManagementActivity.this);
            }
        });
    }
}
