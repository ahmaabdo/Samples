package example.ahmaabdo.notes.models;

/**
 * Created by Ahmad on Mar 23, 2018.
 * In this class we define the SQLite table name,
 * column names and create table SQL query along with getter / setter methods.
 */

public class Note {
    public static final String TABLE_NAME = "notes";

    //Column `id` is defined as Primary Key and Auto Increment which means each note will be uniquely identified by its id.
    public static final String COLUMN_ID = "id";
    //Column `note` stores the actual note text.
    public static final String COLUMN_NOTE = "note";
    //Column `timestamp` stores the date and time of the note that is created.
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String note;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Note() {
    }

    public Note(int id, String note, String timestamp) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
