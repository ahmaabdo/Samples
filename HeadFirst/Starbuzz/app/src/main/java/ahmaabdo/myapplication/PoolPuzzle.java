/*This is the Pool Puzzle at Page 275(317/930)*/
package ahmaabdo.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PoolPuzzle extends AppCompatActivity {

    private String[] colors = {"Red", "Green", "Blue", "White", "Black"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool_puzzle);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, colors);
        Spinner spinner = findViewById(R.id.spinner);
        //Setting the spinner items to the array adapter with colors items
        spinner.setAdapter(arrayAdapter);

    }
}
