package com.kidusmik.notetakerandroidarchcomponentsjava.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kidusmik.notetakerandroidarchcomponentsjava.NotesModel;

import java.util.ArrayList;
import java.util.List;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao mNoteDao();
    private static NoteRoomDatabase INSTANCE;

    static NoteRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NoteRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "note_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final NoteDao mDao;

        PopulateDbAsync(NoteRoomDatabase db){
            mDao = db.mNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if(mDao.getAnyNote().length < 1){
                for(int i =0;i<=SampleNotes.courseTitles.length-1;i++){
                    Note note = new Note(SampleNotes.courseTitles[i], SampleNotes.noteTitles[i], SampleNotes.noteContents[i]);
                    mDao.insert(note);
                }
            }
            return null;
        }
    }
}