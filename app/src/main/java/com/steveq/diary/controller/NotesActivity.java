package com.steveq.diary.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        mUserManager = UserManager.getInstance(null);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        mAddNote = (Button)findViewById(R.id.add_note);
        mLogOut = (Button)findViewById(R.id.log_out);

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
                createNote.putExtra("username", username);
                startActivity(createNote);
            }
        });

        mNotesList = (ListView)findViewById(R.id.notes);

        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<>(
                                        this,
                                        android.R.layout.simple_list_item_1, mUserManager.showNotes(username));
        mNotesList.setAdapter(noteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<Note>(
                this,
                android.R.layout.simple_list_item_1,
                mUserManager.showNotes(username));
        mNotesList.setAdapter(noteAdapter);
    }

    @Override
    public void onBackPressed(){

    }

}
