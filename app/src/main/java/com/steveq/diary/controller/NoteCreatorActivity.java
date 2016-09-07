package com.steveq.diary.controller;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.steveq.diary.R;
import com.steveq.diary.model.Note;
import com.steveq.diary.model.User;

import java.util.Calendar;

public class NoteCreatorActivity extends AppCompatActivity {

    EditText mTitle;
    TextView mTimeArea;
    EditText mContent;
    Button mFinishNote;
    private String username;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);

        mTitle = (EditText)findViewById(R.id.titl);
        mTimeArea = (TextView)findViewById(R.id.curTime);
        mContent = (EditText)findViewById(R.id.content);
        mFinishNote = (Button)findViewById(R.id.add_new_note);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        mFinishNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.USERS.get(username).addNote(new Note(mTitle.getText().toString(), mContent.getText().toString(), time));
                finish();
            }
        });

        runTimer();

    }

    private void runTimer(){

        final Handler timer = new Handler();
        timer.post(new Runnable() {
            @Override
            public void run() {
                long curTime = System.currentTimeMillis();
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DATE);
                int hour = c.get(Calendar.HOUR);
                int minutes = c.get(Calendar.MINUTE);

                time = String.format("%d-%d-%d, %d:%d",day, month, year, hour, minutes);

                mTimeArea.setText(time);

                timer.postDelayed(this, 1000);

            }
        });
    }
}
