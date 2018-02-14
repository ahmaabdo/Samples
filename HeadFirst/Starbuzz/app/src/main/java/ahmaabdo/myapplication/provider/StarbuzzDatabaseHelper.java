package ahmaabdo.myapplication.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ahmaabdo.myapplication.R;

/**
 * Created by Ahmad on Feb 11, 2018.
 */

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

    //The database name and version number
    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;


    //Calling default constructor and passing it the db name and version
    public StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            //Create the DRINK table:
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME TEXT, " +
                    "DESCRIPTION TEXT, " +
                    "IMAGE_RESOURCE_ID INTEGER) ;");
            //Insert each drink in a separate row
            insertDrinks(db, "Latte", "A couple of espresso shots with steamed milk",
                    R.drawable.latte);
            insertDrinks(db, "Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino);
            insertDrinks(db, "Filter", "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter);
        }
        if (oldVersion < 2) {
            //Adding a numeric FAVORITE column to the DRINK table
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }

    }

    private void testDbMethods(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("NAME", "Ahwa sada");
        //This method will update the values in the db where NAME = Latte or Cappuccino to Ahwa sada
        db.update("DRINK", values, "NAME = ? OR NAME = ?", new String[]{"Latte", "Cappuccino"});
        //This method will delete the entire row from the db where NAME = Latte
        db.delete("DRINK", "NAME = ?", new String[]{"Latte"});
    }

    /**
     * A method inserts a separate row into the db
     *
     * @param db:          is the database we want to add records to.
     * @param name:        is the name of the coffee
     * @param description: is the description of the coffee
     * @param imgResId:    is the Image resource id
     */

    private static void insertDrinks(SQLiteDatabase db,
                                     String name,
                                     String description,
                                     int imgResId) {
        //Construct a ContentValues object with the data.
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("DESCRIPTION", description);
        values.put("IMAGE_RESOURCE_ID", imgResId);
        //Insert the data into the db
        db.insert("DRINK", null, values);
    }
}
