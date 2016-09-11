package com.steveq.diary.model;


public class Note {
    private String mTitle;
    private String mContent;
    private String mDate;

    public Note(String title, String content, String date){
        mTitle = title;
        mContent = content;
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public String getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return mTitle + mDate;
    }
}
