package com.kidusmik.notetakerandroidarchcomponentsjava;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kidusmik.notetakerandroidarchcomponentsjava.database.Note;

import java.util.List;

public class NoteListObserver implements LifecycleObserver {

    public Context mContext;
    public NoteViewModel mNoteViewModel;
    public ViewModelStoreOwner mStoreOwner;
    public View view;
    public LifecycleOwner mOwner;
    public Fragment mFragment;

    public NoteListObserver(Context context, ViewModelStoreOwner storeOwner, View view, LifecycleOwner owner, Fragment fragment) {
        mContext = context;
        mStoreOwner = storeOwner;
        this.view = view;
        mOwner = owner;
        mFragment = fragment;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
    }
}