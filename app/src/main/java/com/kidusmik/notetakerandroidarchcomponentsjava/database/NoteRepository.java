package com.kidusmik.notetakerandroidarchcomponentsjava.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NoteRepository {

    private NoteDao mNoteDao;

//    private static MutableLiveData<List<Note>> mAllNotes = new MutableLiveData<>();

    private static LiveData<List<Note>> mAllNotes;
    private static Note mSelectedNote;

    public NoteRepository(Application application){
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.mNoteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
//        new getAllNotesAsyncTask(mNoteDao).execute();
        return mAllNotes;
    }

    public void update(Note note){
        new updateAsyncTask(mNoteDao).execute(note);
    }

    public void insert(Note note){
        new insertAsyncTask(mNoteDao).execute(note);
    }

    public void deleteAllNotes(){
        new deleteAllNotesAsyncTask(mNoteDao).execute();
    }

    public Note getSelectedNote(int noteId) throws ExecutionException, InterruptedException {
        return new getSelectedNoteAsyncTask(mNoteDao).execute(noteId).get();
//        return mNoteDao.getSelectedNote(noteId);
    }

    public void deleteNote(Note note){
        new deleteNoteAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.insert(notes[0]);
            return null;
        }
    }

    private static class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao mAsyncTaskDao;

        deleteAllNotesAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;

        deleteNoteAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;

        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class getSelectedNoteAsyncTask extends AsyncTask<Integer, Void, Note> {

        private NoteDao mAsyncTaskDao;
        getSelectedNoteAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Note doInBackground(Integer... integers) {
            return mAsyncTaskDao.getSelectedNote(integers[0]);
        }

        @Override
        protected void onPostExecute(Note note) {
            super.onPostExecute(note);
            mSelectedNote = note;
        }
    }

    private static class getAllNotesAsyncTask extends AsyncTask<Void, Void, LiveData<List<Note>>> {

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
//            mAllNotes.postValue(listLiveData.getValue());
//            mAllNotes = listLiveData;
            mAllNotes = listLiveData;
        }
    }

    private class updateAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mAsyncTaskDao;
        public updateAsyncTask(NoteDao noteDao) {
            mAsyncTaskDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mAsyncTaskDao.updateNote(notes[0]);
            return null;
        }
    }
}