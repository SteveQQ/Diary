package com.steveq.diary.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.steveq.diary.R;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    Context mContext;
    int mLayoutResourceId;
    ArrayList<Note> mData = null;

    public NoteAdapter(Context ctx, int layoutResourceId, ArrayList<Note> data){
        super(ctx, layoutResourceId, data);
        mContext = ctx;
        mLayoutResourceId = layoutResourceId;
        mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        NoteHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new NoteHolder();
            holder.rowTitle = (TextView)row.findViewById(R.id.row_title);
            holder.rowDate = (TextView)row.findViewById(R.id.row_date);

            row.setTag(holder);

        } else {
            holder = (NoteHolder)row.getTag();
        }

        Note note = mData.get(position);
        holder.rowTitle.setText(note.getTitle());
        holder.rowDate.setText(note.getDate());

        return row;
    }

    static class NoteHolder{
        TextView rowTitle;
        TextView rowDate;
    }

}
