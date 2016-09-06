package com.steveq.diary.model;

import java.util.Date;

public class Note {
    private String mTitle;
    private String mContent;
    private String mDate;

    public Note(String title, String content, String date){
        mTitle = title;
        mContent = content;
        mDate = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Title='" + mTitle + '\'' +
                ", Date=" + mDate +
                '}';
    }
}
