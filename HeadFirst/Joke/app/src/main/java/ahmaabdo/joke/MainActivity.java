package ahmaabdo.joke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ahmaabdo.joke.services.DelayedMessageService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        startService(new Intent(this, DelayedMessageService.class)
                .putExtra(DelayedMessageService.EXTRA_MSG, "nothing!"));
    }
}
