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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import ahmaabdo.myapplication.provider.StarbuzzDatabaseHelper;

public class TopLevelActivity extends AppCompatActivity {
    private ListView listOptions, listFavorite;
    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private CursorAdapter favoriteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        listFavorite = findViewById(R.id.list_favorites);
        listOptions = findViewById(R.id.list_options);

        setupOptionListView();
        setupFavoriteListView();
    }

    private void setupOptionListView() {
        listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    startActivity(new Intent(TopLevelActivity.this, DrinkCategoryActivity.class));
            }
        });
    }

    private void setupFavoriteListView() {
        try {
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDbHelper.getReadableDatabase();
            favoritesCursor = db.query(
                    "DRINK",
                    new String[]{"_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);

            favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor, new String[]{"NAME"},
                    new int[]{android.R.id.text1}, 0);

            listFavorite.setAdapter(favoriteAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(TopLevelActivity.this, DrinkActivity.class)
                        .putExtra(DrinkActivity.EXTRA_DRINKID, (int) id));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor newCursor = db.query(
                "DRINK", new String[]{"_id", "NAME"},
                "FAVORITE = 1", null, null, null, null);
        CursorAdapter adapter = (CursorAdapter) listFavorite.getAdapter();
        adapter.changeCursor(newCursor);
        favoritesCursor = newCursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        favoritesCursor.close();
    }
}
