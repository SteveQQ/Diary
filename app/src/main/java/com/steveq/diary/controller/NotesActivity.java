package com.steveq.diary.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.steveq.diary.R;
import com.steveq.diary.model.Note;
import com.steveq.diary.model.User;
import com.steveq.diary.model.UserManager;

public class NotesActivity extends AppCompatActivity {

    private ListView mNotesList;
    private Button mAddNote;
    private Button mLogOut;
    private String username;
    private UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        //-----DECLARATIONS-----//
        mUserManager = UserManager.getInstance(null);
        mAddNote = (Button)findViewById(R.id.add_note);
        mLogOut = (Button)findViewById(R.id.log_out);
        mNotesList = (ListView)findViewById(R.id.notes);
        //-----DECLARATIONS-----//

        //-----SETTING CONTENT TO THE LIST-----//
        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<>(
                                        this,
                                        android.R.layout.simple_list_item_1, mUserManager.showNotes());
        mNotesList.setAdapter(noteAdapter);
        //-----SETTING CONTENT TO THE LIST-----//


        //-----SETTING CLICK LISTENERS-----//
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createNote = new Intent(NotesActivity.this, NoteCreatorActivity.class);
                startActivity(createNote);
            }
        });

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NotesActivity.this, NotePresentationActivity.class);
                startActivity(intent);
            }
        };

        mNotesList.setOnItemClickListener(itemClickListener);
        //-----SETTING CLICK LISTENERS-----//
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<Note>(
                this,
                android.R.layout.simple_list_item_1,
                mUserManager.showNotes());
        mNotesList.setAdapter(noteAdapter);
    }

    @Override
    public void onBackPressed(){

    }

}
