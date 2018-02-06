package ahmaabdo.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        //pass the id of the workout to detail activity EXTRA_WORKOUT_ID
        intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
        startActivity(intent);
    }
}
