package com.kidusmik.notetakerandroidarchcomponentsjava.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;
    private Note mSelectedNote;

    public NoteRepository(Application application){
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.mNoteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
//        new getAllNotesAsyncTask(mNoteDao).execute();
        return mAllNotes;
    }

    public void insert(Note note){
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public void deleteAllNotes(){
        new deleteAllNotesAsyncTask(mNoteDao).execute();
    }

    public Note getSelectedNote(int noteId){
        new getSelectedNoteAsyncTask(mNoteDao).execute(noteId);
        return mSelectedNote;
    }

    public void deleteNote(Note note){
        new deleteNoteAsyncTask(mNoteDao).execute(note);
    }

    private class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        public insertAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao mAsyncTaskDao;

        public deleteAllNotesAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        public deleteNoteAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.deleteNote(notes[0]);
            return null;
        }
    }

    private class getSelectedNoteAsyncTask extends AsyncTask<Integer, Void, Note> {

        private NoteDao mAsyncTaskDao;
        public getSelectedNoteAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Note doInBackground(Integer... integers) {
            return mAsyncTaskDao.getSelectedNote(integers[0]);
        }

        @Override
        protected void onPostExecute(Note note) {
            mSelectedNote = note;
        }
    }

    private class getAllNotesAsyncTask extends AsyncTask<Void, Void, LiveData<List<Note>>> {

        private NoteDao mAsyncTaskDao;
        public getAllNotesAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected LiveData<List<Note>> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllNotes();
        }

        @Override
        protected void onPostExecute(LiveData<List<Note>> listLiveData) {
            mAllNotes = listLiveData;
        }
    }
}
