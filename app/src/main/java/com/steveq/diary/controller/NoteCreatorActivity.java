package com.steveq.diary.controller;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.steveq.diary.R;

import java.util.Calendar;

public class NoteCreatorActivity extends AppCompatActivity {

    TextView mTimeArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creator);

        mTimeArea = (TextView)findViewById(R.id.curTime);

        final Calendar c = Calendar.getInstance();
        final Handler timer = new Handler();
        timer.post(new Runnable() {
            @Override
            public void run() {
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minutes = c.get(Calendar.MINUTE);

                String time = String.format("%d-%d-%d, %d:%d",day, month, year, hour, minutes);

                mTimeArea.setText(time);

                timer.postDelayed(this, 60000);

            }
        });
    }
}
