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
import com.steveq.diary.model.UserManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NoteCreatorActivity extends AppCompatActivity {

    EditText mTitle;
    TextView mTimeArea;
    EditText mContent;
    Button mFinishNote;
    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);

        manager = UserManager.getInstance(null);

        mTitle = (EditText)findViewById(R.id.titl);
        mTimeArea = (TextView)findViewById(R.id.curTime);
        mContent = (EditText)findViewById(R.id.content);
        mFinishNote = (Button)findViewById(R.id.add_new_note);

        mFinishNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.addNote(new Note(mTitle.getText().toString(), mContent.getText().toString(), mTimeArea.getText().toString()));
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
                String time;
                final Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy | HH:mm");

                time = sdf.format(c.getTime());
                mTimeArea.setText(time);

                timer.postDelayed(this, 1000);

            }
        });
    }
}
