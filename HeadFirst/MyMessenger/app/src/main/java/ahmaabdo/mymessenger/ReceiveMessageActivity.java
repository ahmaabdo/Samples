package ahmaabdo.mymessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Ahmad on Jan 28, 2018.
 */

public class ReceiveMessageActivity extends AppCompatActivity {
    private TextView mMessage;
    //This is the name of the Extra value we're passing in the intent
    public static final String EXTRA_MESSAGE = "myMsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Get the intent
        Intent intent = getIntent();
        //Get the intent passed along with the intent that has a name of "myMsg"
        String msg = intent.getStringExtra(EXTRA_MESSAGE);
        mMessage = findViewById(R.id.message);
        //Add the text to the message text view
        mMessage.setText(msg);
    }
}
