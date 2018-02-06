package ahmaabdo.workout;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Listener {

    private static int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        //Get the reference of the frame layout that will contain WorkoutDetailFragment
        //This will only exist if the app running on a device with large screen
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            //Start the fragment transaction and Replace the fragment
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.fragment_container, details)
                    //Get the new and old fragment to fade in and out
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    //Add the fragment to back stack
                    .addToBackStack(null)
                    //commit the transaction
                    .commit();
            details.setWorkoutId(id);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            //pass the id of the workout to detail activity EXTRA_WORKOUT_ID
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);
        }
    }

}
