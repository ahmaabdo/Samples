package ahmaabdo.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ahmaabdo.myapplication.provider.StarbuzzDatabaseHelper;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKID = "drinkId";
    private CheckBox favoriteBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);
        //Populate the drink name, description, and photo
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        ImageView photo = findViewById(R.id.photo);
        favoriteBox = findViewById(R.id.favorite);

        //This gives us a Drink object containing all the information we need to update the view attributes in the activity:
        //Drink drink = Drink.drinks[drinkId];

        //Create a cursor
        SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(this);
        try {
            //Get a reference to the db
            SQLiteDatabase db = starbuzzDbHelper.getReadableDatabase();
            //Create a cursor that gets the NAME, DESCRIPTION, and IMAGE_RESOURCE_ID data from the DRINK table where _id matches drinkId.
            Cursor cursor = db.query(
                    "DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null);
            //Move to the first record in the cursor
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                //If the favorite column has a value of 1, this indicates a true value
                boolean isFavorite = (cursor.getInt(3) == 1);
                //Populate the views with the values from the database
                name.setText(nameText);
                description.setText(descriptionText);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
                favoriteBox.setChecked(isFavorite);
            }
            //Close the cursor and the db
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    //Update the database when the checkbox is clicked
    public void onFavoriteClicked(View view) {
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);
        new UpdateDrinkTask().execute(drinkId);
    }

    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", favoriteBox.isChecked());

        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkId = drinks[0];
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
            try {
                SQLiteDatabase db = starbuzzDbHelper.getWritableDatabase();
                db.update("DRINK", drinkValues,
                        "_id = ?",
                        new String[]{Integer.toString(drinkId)});
                db.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast.makeText(DrinkActivity.this,
                        "Database unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
