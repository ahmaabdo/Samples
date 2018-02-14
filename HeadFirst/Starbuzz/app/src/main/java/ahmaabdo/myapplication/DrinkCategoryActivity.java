package ahmaabdo.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import ahmaabdo.myapplication.provider.StarbuzzDatabaseHelper;

public class DrinkCategoryActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        ListView listDrinks = findViewById(R.id.list_drinks);
      /*  ArrayAdapter<Drink> listArray = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drink.drinks);*/
        SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(this);
        try {
            //Get a reference of the db
            db = starbuzzDbHelper.getReadableDatabase();
            //Create the cursor
            //Calling query() method, Behind the scenes, this builds a SQL SELECT statement
            cursor = db.query(
                    "DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            //Create the cursor adapter
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(
                    this, android.R.layout.simple_list_item_1,
                    cursor, new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);

            listDrinks.setAdapter(listAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "An unknown error", Toast.LENGTH_SHORT).show();
        }

        //Create listener handles onClick
        listDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Pass the drink that user selected to DrinkActivity
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
