package com.steveq.diary.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.steveq.diary.R;

public class NotePresentationActivity extends AppCompatActivity {

    private TextView mTitleView;
    private TextView mDateView;
    private TextView mContentView;
    private Button mBackButton;
    private Button mRemoveButton;
    private int position;
    private AlertDialog.Builder mAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_presentation);


        //-----DECLARATIONS-----//
        mTitleView = (TextView)findViewById(R.id.titleView);
        mDateView = (TextView)findViewById(R.id.timeView);
        mContentView = (TextView)findViewById(R.id.contentView);
        mBackButton = (Button)findViewById(R.id.back);
        mRemoveButton = (Button)findViewById(R.id.remove_note);
        mAlert = new AlertDialog.Builder(NotePresentationActivity.this);
        //-----DECLARATIONS-----//

        //-----ALERT BUILD-----//
        mAlert.setTitle("REALLY?");
        mAlert.setMessage("Are you sure to delete note?");
        //-----ALERT BUILD-----//

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String content = intent.getStringExtra("content");
        position = intent.getIntExtra("position", -1);

        mTitleView.setText(title);
        mDateView.setText(date);
        mContentView.setText(content);

        //-----SETTING CLICK LISTENERS-----//
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Intent intent = new Intent();
                        intent.putExtra("position", position);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                });
                mAlert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });
                mAlert.show();
            }
        });
        //-----SETTING CLICK LISTENERS-----//
    }
}
