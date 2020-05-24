package com.kidusmik.notetakerandroidarchcomponentsjava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kidusmik.notetakerandroidarchcomponentsjava.database.Note;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteListFragment extends Fragment {

    public NoteListFragment() {
        // Required empty public constructor
    }


    private NoteViewModel mNoteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

//        getLifecycle().addObserver(new NoteListObserver(getContext(), this, view, this, this));

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        Fragment noteListFragment = NoteListFragment.this;

        Fragment fragment = getParentFragment();

        NavController navController = MainActivity.mNavHostFragment.getNavController();

        final RecyclerView recyclerView = view.findViewById(R.id.rv_note_list);
        final NoteListRecyclerAdapter adapter = new NoteListRecyclerAdapter(getContext(), fragment, NavHostFragment.findNavController(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mNoteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_noteListFragment_to_editNoteFragment));

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Note myNote = adapter.getNoteAtPosition(position);
                        Toast.makeText(getContext(), "Deleting " +
                                myNote.getNoteTitle(), Toast.LENGTH_LONG).show();

                        // Delete the word
                        mNoteViewModel.deleteNote(myNote);
//                        adapter.notifyDataSetChanged();
                    }
                });

        helper.attachToRecyclerView(recyclerView);

        return view;
    }
}
