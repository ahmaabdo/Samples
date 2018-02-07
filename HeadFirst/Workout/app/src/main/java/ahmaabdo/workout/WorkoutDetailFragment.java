package ahmaabdo.workout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {
    //ID for the workout the user chooses. will used to set the value of the fragments view with the workout id
    private long workoutId;

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Only add the fragment if the activity isn't begin recreated after having been destroyed
        if (savedInstanceState == null) {
            StopwatchFragment stopwatch = new StopwatchFragment();
            //Begin the fragment transaction
            getChildFragmentManager().beginTransaction()
                    //Add the stopwatch.
                    .add(R.id.stopwatch_container, stopwatch)
                    //Add to the back stack
                    .addToBackStack(null)
                    //Set the fragment transition to fade in and out
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    //commit the transaction
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Store the current id at bundle to restore it on activity recreate
        outState.putLong("workoutId", workoutId);
    }

    @Override
    public void onStart() {
        super.onStart();
        //getView() method gets the fragment root view
        View view = getView();
        //Using getView() to get reference to the workout title and description views
        if (view != null) {
            TextView title = view.findViewById(R.id.textTitle);
            TextView description = view.findViewById(R.id.textDescription);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }

    //Setter method for the workout id that the activity will use it to set the value of the workout id
    public void setWorkoutId(long id) {
        this.workoutId = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutId");
        }
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }


}
