package ahmaabdo.workout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {
    private Listener listener;

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create a string array with length of the workouts array length
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            //Create String array of the workout names
            names[i] = Workout.workouts[i].getName();
        }
        //Create an array adapter with the context from the layout inflater
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1, names);
        //Bind the array adapter to the list view
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (listener != null) listener.itemClicked(id);
    }

}
