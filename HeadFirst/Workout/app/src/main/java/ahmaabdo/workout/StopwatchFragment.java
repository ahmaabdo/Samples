package ahmaabdo.workout;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {
    //Number of seconds displayed on the stopwatch
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running, wasRunning;
    final static Handler handler = new Handler();

    public StopwatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        //Attach the listener to each of the buttons
        Button startButton = view.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = view.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        runTimer(view);
        //Restore values from onSaveInstanceState
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Store the values of the variables in the bundle before the activity is destroyed
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onPause() {
        super.onPause();
        //If the fragment paused, record whether the stopwatch was running and stop it.
        wasRunning = false;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        //If the stopwatch was running before it was paused, Set it back running again
        if (wasRunning) running = true;
    }


    private void runTimer(View view) {
        final TextView timeView = view.findViewById(R.id.time_view);
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds / 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) seconds++;
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                running = true;
                break;
            case R.id.stop_button:
                running = false;
                break;
            case R.id.reset_button:
                running = false;
                seconds = 0;
                break;
        }
    }
}
