package com.kidusmik.notetakerandroidarchcomponentsjava;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kidusmik.notetakerandroidarchcomponentsjava.database.Note;
import com.kidusmik.notetakerandroidarchcomponentsjava.database.SampleNotes;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends Fragment {

    public EditNoteFragment() {
        // Required empty public constructor
    }

    public NoteViewModel mNoteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);

        int noteId = EditNoteFragmentArgs.fromBundle(getArguments()).getNoteId();

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        final TextInputLayout tiNoteTitle = view.findViewById(R.id.ti_note_title);
        final TextInputLayout tiNoteContent = view.findViewById(R.id.ti_note_content);
        final Spinner spinnerEditNote = view.findViewById(R.id.spinner_edit_note);

        if(noteId != -1){
            Note selectedNote = mNoteViewModel.getSelectedNote(noteId);

            tiNoteTitle.getEditText().setText(selectedNote.getNoteTitle());
            tiNoteContent.getEditText().setText(selectedNote.getNoteContent());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line,
                SampleNotes.courseTitles);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditNote.setAdapter(adapter);

        Button saveNote = view.findViewById(R.id.button_save_note);
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note note = new Note(spinnerEditNote.getSelectedItem().toString(),
                        tiNoteTitle.getEditText().getText().toString(),
                        tiNoteContent.getEditText().getText().toString());
                mNoteViewModel.insert(note);
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_editNoteFragment_to_noteListFragment);
            }
        });

        return view;
    }
}
