package ahmaabdo.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends AppCompatActivity {

    private TextView timeText;
    //Number of the seconds that displayed on the stopwatch
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;
    //Create a new handler
    final static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            //Restore the activity state by getting values from the Bundle.
            seconds = savedInstanceState.getInt("secs");
            running = savedInstanceState.getBoolean("run");
            wasRunning = savedInstanceState.getBoolean("wasRun");
        }
        timeText = findViewById(R.id.time_view);
        runTimer();
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    //Save the state of the variables in the activity's onSavedInstantState method
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Store the values with unique keys to the Bundle
        outState.putInt("secs", seconds);
        outState.putBoolean("run", running);
        outState.putBoolean("wasRun", wasRunning);
        super.onSaveInstanceState(outState);
    }

    //If the activity paused:
    // Save current running state to -> wasRunning and Stop the stopwatch.
    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    //If the activity resumed:
    // Start the stopwatch again if it was running previously
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning)
            running = true;
    }

    //Called when Start button clicked.
    public void onClickStart(View view) {
        //Start the stopwatch
        running = true;
    }

    //Called when Stop button clicked.
    public void onClickStop(View view) {
        //Stop the stopwatch
        running = false;
    }

    //Called when Reset button clicked.
    public void onClickReset(View view) {
        //Reset the stopwatch and set the seconds to 0
        running = false;
        seconds = 0;
    }

    //The runTimer method uses a Handler to increment the seconds
    // and update the text view
    private void runTimer() {
        //Call the post() method, passing in a new Runnable.
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Init hours, minutes and seconds
                int hour = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                //Format the seconds into hours, minutes, and seconds with a plain java code.
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d",
                        hour, minutes, secs);
                //Set the time text view to the formatted string -> time
                timeText.setText(time);
                //If running is true? increment the seconds variable:
                if (running) seconds++;
                //Post the code in the runnable to be run again after a delay of 1 sec.
                handler.postDelayed(this, 1000);

            }
        });
    }

}