package ahmaabdo.bitsandpizzas;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Code that runs when the FAB is clicked
    public void onClickDone(View view) {
        Snackbar.make(findViewById(R.id.coordinator),
                getResources().getString(R.string.update_order),
                Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Code to run when the user clicks on the Undo action
                        Toast.makeText(OrderActivity.this, R.string.order_undone, Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
