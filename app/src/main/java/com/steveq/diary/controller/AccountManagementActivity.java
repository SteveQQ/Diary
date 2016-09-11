package com.steveq.diary.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.steveq.diary.R;
import com.steveq.diary.model.UserManager;

public class AccountManagementActivity extends AppCompatActivity {

    private EditText mPassword;
    private Button mRemoveUser;
    private Button mChangePassword;
    private UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        mPassword = (EditText)findViewById(R.id.password);
        mRemoveUser = (Button)findViewById(R.id.remove_user);
        mChangePassword = (Button)findViewById(R.id.change_password);

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
                String password = mPassword.getText().toString();
                mUserManager.changePassword(password)
            }
        });
    }
}
