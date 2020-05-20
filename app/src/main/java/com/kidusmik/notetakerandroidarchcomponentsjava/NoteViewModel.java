package com.kidusmik.notetakerandroidarchcomponentsjava;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.kidusmik.notetakerandroidarchcomponentsjava.database.Note;
import com.kidusmik.notetakerandroidarchcomponentsjava.database.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository mRepository;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
//        mAllNotes = mRepository.getAllNotes();
    }
    public LiveData<List<Note>> getAllNotes(){
       return mRepository.getAllNotes();
    }

    public void insert(Note note){
        mRepository.insert(note);
    }

    public void deleteAllNotes(){
        mRepository.deleteAllNotes();
    }

    public void deleteNote(Note note){
        mRepository.deleteNote(note);
    }

    public Note getSelectedNote(int noteId){
        return mRepository.getSelectedNote(noteId);
    }
}
