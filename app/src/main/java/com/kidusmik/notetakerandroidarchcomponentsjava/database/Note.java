package com.kidusmik.notetakerandroidarchcomponentsjava.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull @ColumnInfo(name = "course_title")
    private String mCourseTitle;

    @NonNull @ColumnInfo(name = "note_title")
    private String mNoteTitle;

    @NonNull @ColumnInfo(name = "note_content")
    private String mNoteContent;

//    @Ignore
//    public Note(int id, @NonNull String courseTitle, @NonNull String noteTitle, @NonNull String noteContent) {
//        this.id = id;
//        mCourseTitle = courseTitle;
//        mNoteTitle = noteTitle;
//        mNoteContent = noteContent;
//    }

    public Note(@NonNull String courseTitle, @NonNull String noteTitle, @NonNull String noteContent) {
        mCourseTitle = courseTitle;
        mNoteTitle = noteTitle;
        mNoteContent = noteContent;
    }

    public Integer getId() {
        return id;
    }

    @NonNull
    public String getCourseTitle() {
        return mCourseTitle;
    }

    @NonNull
    public String getNoteTitle() {
        return mNoteTitle;
    }

    @NonNull
    public String getNoteContent() {
        return mNoteContent;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCourseTitle(@NonNull String courseTitle) {
        mCourseTitle = courseTitle;
    }

    public void setNoteTitle(@NonNull String noteTitle) {
        mNoteTitle = noteTitle;
    }

    public void setNoteContent(@NonNull String noteContent) {
        mNoteContent = noteContent;
    }
}