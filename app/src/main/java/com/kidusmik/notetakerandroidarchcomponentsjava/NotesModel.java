package com.kidusmik.notetakerandroidarchcomponentsjava;

public class NotesModel {

    public String courseTitle;
    public String noteTitle;
    public String noteContent;

    public NotesModel(){}

    public NotesModel(String courseTitle, String noteTitle, String noteContent) {
        this.courseTitle = courseTitle;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
