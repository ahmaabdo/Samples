package ahmaabdo.realm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ahmaabdo.realm.R;
import ahmaabdo.realm.activities.AddNoteActivity;
import ahmaabdo.realm.models.Note;

/**
 * Created by Ahmad on Mar 15, 2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private List<Note> notesList;
    private Context context;
    public static final String EXTRA_ID = "id";

    public NoteAdapter(Context context, List<Note> notesList) {
        this.notesList = notesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = notesList.get(position);
        holder.title.setText(note.getNoteTitle());
        holder.content.setText(note.getNoteContent());

        holder.noteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddNoteActivity.class)
                        .putExtra(EXTRA_ID, note.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        CardView noteCard;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.note_title_text);
            content = view.findViewById(R.id.note_content_text);
            noteCard = view.findViewById(R.id.note_card_view);
        }
    }


}
