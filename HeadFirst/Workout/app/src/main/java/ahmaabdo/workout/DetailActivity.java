package ahmaabdo.workout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        //Get the id from the intent, and pass it to the fragment via setWorkoutId method.
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        WorkoutDetailFragment fragment =
                (WorkoutDetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.detail_fragment);
        fragment.setWorkoutId(workoutId);
    }
}
