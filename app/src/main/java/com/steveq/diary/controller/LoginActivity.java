package com.steveq.diary.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.steveq.diary.R;
import com.steveq.diary.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button mNewUser;
    private User mUser;
    private EditText mUserNameArea;
    private EditText mPasswordArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUser = new User();

        mUserNameArea = (EditText)findViewById(R.id.username);
        mPasswordArea = (EditText)findViewById(R.id.password);

        mNewUser = (Button)findViewById(R.id.new_user);
        mNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credentialValidation();
            }
        });
    }

    private boolean credentialValidation(){
        String username = mUserNameArea.getText().toString();
        String password = mPasswordArea.getText().toString();
        if(!mUser.validate(username) || username.equals("")) {
            Toast.makeText(LoginActivity.this, "Insert valid User Name", Toast.LENGTH_LONG).show();
        } else if(!mUser.validate(password) || password.equals("")){
            Toast.makeText(LoginActivity.this, "Insert valid Password", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, "Credentials correct", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
