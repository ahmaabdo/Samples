package example.ahmaabdo.notes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import example.ahmaabdo.notes.models.Note;

/**
 * Created by Ahmad on Mar 23, 2018.
 * This class holds the database related methods to perform the CRUD operations.
 */

public class NoteDatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "notes_db";


    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Will be called only once when the app is installed.
    // In this method, we execute the create table sql statements to create necessary tables.
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Note.CREATE_TABLE);
    }

    /**
     * Called when an update is released.
     * DATABASE_VERSION must be modified in order to execute this method.
     * Should take care of database migrations here without loosing the older data if necessary.
     *
     * @param db         our application database
     * @param oldVersion the old version of DATABASE_VERSION
     * @param newVersion the new version of DATABASE_VERSION
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * @param note is the note we need to save it to the db
     * @return newly inserted row id
     */
    public long insertNote(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        //Used to define the column name and its data to be stored.
        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Note.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(Note.TABLE_NAME, null, values);

        // Every time the database connection has to be closed once done with database access.
        // Calling db.close() closes the connection.
        db.close();

        //Once the note is inserted, the `id` of newly inserted note will be returned.
        return id;
    }

    /**
     * Takes already existed note `id` and fetches the note object.
     *
     * @param id is the id of the current note
     * @return the note we selected
     */
    public Note getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    /**
     * @return all the notes in descending order by timestamp.
     */
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
                Note.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    /**
     * @return the count of notes stored in database.
     */
    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * Updating data requires writable access. Below the note is updated by its `id`.
     *
     * @return the updated data to the db
     */
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    /**
     * Deleting data requires writable access. Below method deletes a note by finding its `id`.
     */
    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}