package com.steveq.diary.controller;

import android.app.ListActivity;
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

public class NotesActivity extends AppCompatActivity {

    ListView mNotesList;
    Button mAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        mAddNote = (Button)findViewById(R.id.add_note);

        mAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNotesList = (ListView)findViewById(R.id.notes);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<Note>(
                                        this,
                                        android.R.layout.simple_list_item_1,
                                        User.USERS.get(username).getNoteList());
        mNotesList.setAdapter(noteAdapter);
    }
}
