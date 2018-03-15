package ahmaabdo.realm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ahmaabdo.realm.R;
import ahmaabdo.realm.models.Note;
import io.realm.Realm;
import io.realm.RealmResults;

import static ahmaabdo.realm.adapters.NoteAdapter.EXTRA_ID;

public class AddNoteActivity extends AppCompatActivity {
    private Realm realm;
    private EditText noteTitle, noteContent;
    //true if this activity started from the adapter, otherwise false
    boolean fromAdapter = false;
    private long id;
    private RealmResults<Note> results;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //init realm
        realm = Realm.getDefaultInstance();
        //Populate items
        deleteButton = findViewById(R.id.delete_button);
        noteTitle = findViewById(R.id.note_title);
        noteContent = findViewById(R.id.note_content);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            fromAdapter = true;
            //Set the delete button to visible whenever the activity started from the adapter, otherwise hidden by default
            deleteButton.setVisibility(View.VISIBLE);
            id = intent.getLongExtra(EXTRA_ID, 0);
            results = realm.where(Note.class).equalTo(EXTRA_ID, id).findAll();

            for (Note note : results) {
                noteTitle.setText(note.getNoteTitle());
                noteContent.setText(note.getNoteContent());
            }
        }

    }

    //Method called when save button clicked
    public void saveNote(View view) {
        //Checking if current activity coming from
        if (fromAdapter) {
            for (Note note : results) {
                //open new connection to realm
                realm.beginTransaction();
                //Populate items
                note.setNoteTitle(noteTitle.getText().toString());
                note.setNoteContent(noteContent.getText().toString());
                //close connection whenever done
                realm.commitTransaction();
            }
        } else {
            //Create new object from model
            Note note = new Note();
            //Create new Id, title and content.
            note.setId(getNextKey());
            note.setNoteTitle(noteTitle.getText().toString());
            note.setNoteContent(noteContent.getText().toString());

            //open new connection to realm
            realm.beginTransaction();
            //Save the note to realm
            realm.copyToRealm(note);
            //close connection whenever done
            realm.commitTransaction();
        }
        finish();
    }

    //Getting new id from realm as long
    public long getNextKey() {
        try {
            return realm.where(Note.class).max("id").longValue() + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Method called when Delete button clicked
    public void deleteNote(View view) {
        realm.beginTransaction();
        //Method to delete current item from realm
        results.deleteAllFromRealm();
        realm.commitTransaction();
        finish();
    }
}
