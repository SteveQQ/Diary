package com.steveq.diary.controller;

import android.app.Activity;
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
    private Button mAccount;
    private UserManager mUserManager;
    public static final int CHECK_IF_REMOVE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        //-----DECLARATIONS-----//
        mUserManager = UserManager.getInstance(null);
        mAddNote = (Button)findViewById(R.id.add_note);
        mAccount = (Button)findViewById(R.id.account);
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
        mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesActivity.this, AccountManagementActivity.class);
                startActivity(intent);
            }
        });

        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserManager.logOut(NotesActivity.this);
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
                Note note = mUserManager.showNote(position);
                Intent intent = new Intent(NotesActivity.this, NotePresentationActivity.class);
                intent.putExtra("title", note.getTitle());
                intent.putExtra("date", note.getDate());
                intent.putExtra("content", note.getContent());
                intent.putExtra("position", position);
                startActivityForResult(intent, CHECK_IF_REMOVE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CHECK_IF_REMOVE){
            if(resultCode == Activity.RESULT_OK){
                mUserManager.removeNote(data.getIntExtra("position", -1));
            }
            if(resultCode == Activity.RESULT_CANCELED){

            }
        }
    }

}
