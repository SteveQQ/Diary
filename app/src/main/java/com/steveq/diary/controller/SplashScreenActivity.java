package com.steveq.diary.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.steveq.diary.R;
import com.steveq.diary.model.UserManager;

public class SplashScreenActivity extends AppCompatActivity {
    private UserManager mUserManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserManager = UserManager.getInstance(this);
        mUserManager.initializeUsersList();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
