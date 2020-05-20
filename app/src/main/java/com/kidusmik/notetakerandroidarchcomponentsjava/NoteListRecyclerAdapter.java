package com.kidusmik.notetakerandroidarchcomponentsjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.kidusmik.notetakerandroidarchcomponentsjava.database.Note;

import java.util.List;

public class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.NoteViewHolder> {

    private final LayoutInflater mInflater;
    private List<Note> mNotes;
    private Fragment mEditNoteFrag;
    Context mContext;
    NavController mNavController;


    NoteListRecyclerAdapter(Context context, Fragment fragment, NavController navController){
        mContext = context;
        mEditNoteFrag = fragment;
        mInflater = LayoutInflater.from(context);
        mNavController = navController;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_note_list, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if(mNotes!=null){
            Note current = mNotes.get(position);
            holder.mNoteId = mNotes.get(position).getId();
            holder.mTvCourseTitle.setText(current.getCourseTitle());
            holder.mTvNoteTitle.setText(current.getNoteTitle());
            holder.mTvNoteContent.setText(current.getNoteContent());
        } else{
            holder.mTvCourseTitle.setText("No Note");
            holder.mTvNoteTitle.setText("No Note");
            holder.mTvNoteContent.setText("No Note");
        }
    }

    public void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAtPosition(int position){
        return mNotes.get(position);
    }

    @Override
    public int getItemCount() {
        if(mNotes!=null)
            return mNotes.size();
        else
            return 0;
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private int mNoteId;
        private final TextView mTvCourseTitle;
        private final TextView mTvNoteTitle;
        private final TextView mTvNoteContent;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvCourseTitle = itemView.findViewById(R.id.tv_note_list_course_title);
            mTvNoteTitle = itemView.findViewById(R.id.tv_note_list_note_title);
            mTvNoteContent = itemView.findViewById(R.id.tv_note_list_note_content);

            NoteListFragmentDirections.ActionNoteListFragmentToEditNoteFragment action =
                    NoteListFragmentDirections.actionNoteListFragmentToEditNoteFragment().setNoteId(mNoteId);

//            NavHostFragment.findNavController(mEditNoteFrag).navigate(action);
//            Navigation.findNavController(itemView).navigate(action);
//            MainActivity.mNavHostFragment.getNavController().navigate(action);
            mNavController.navigate(action);

            itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_noteListFragment_to_editNoteFragment));
        }
    }
}
