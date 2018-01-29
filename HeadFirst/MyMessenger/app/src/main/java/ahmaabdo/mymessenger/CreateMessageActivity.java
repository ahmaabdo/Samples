package ahmaabdo.mymessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends AppCompatActivity {
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        mEditTextMessage = findViewById(R.id.edit_text_msg);
    }

    //Call onSendMessage when Send button clicked
    public void onSendMessage2(View view) {
        //Create a new Intent with an extra name called myMsg
        Intent i = new Intent(this, ReceiveMessageActivity.class);
        //Get the text from edit text field and convert it to String
        //And pass it with the intent
        i.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, mEditTextMessage.getText().toString());
        //Start the intent
        startActivity(i);
    }

    //Call onSendMessage when Send button clicked
    //This method will create a chooser with a custom title to send sms.
    public void onSendMessage(View view) {
        //Create a new Intent with Action Send
        Intent i = new Intent(Intent.ACTION_SEND);
        //set the MIME type to text plain and passes the edit text field
        i.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, mEditTextMessage.getText().toString());
        //Display a chooser dialog with a custom title -> Choose by your self..
        Intent chosenIntent = Intent.createChooser(i, "Choose by your self..");
        //Start the chosenIntent
        startActivity(chosenIntent);
    }
}
