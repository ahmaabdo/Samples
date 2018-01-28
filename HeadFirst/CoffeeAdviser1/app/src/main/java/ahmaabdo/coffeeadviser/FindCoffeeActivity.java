package ahmaabdo.coffeeadviser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindCoffeeActivity extends AppCompatActivity {

    private CoffeeExpert expert = new CoffeeExpert();

    private TextView brandsText;
    private Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_coffee);

        brandsText = findViewById(R.id.brands_text);
        type = findViewById(R.id.type);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Called when the user click the Find Coffee button.
    public void onClickFindCoffee(View view) {
        //This gets the selected item in the spinner, and converts it to a String.
        String coffeeType = String.valueOf(type.getSelectedItem());
        //Get list of brands
        //Use the CoffeeExpert class to get a list of brands
        List<String> brandsList = expert.getBrands(coffeeType);
        //Build a string using the values in the list
        StringBuilder brandsFormatted = new StringBuilder();
        for (String brands : brandsList) {
            //Display each brand on a new line
            brandsFormatted.append(brands).append("\n");
        }
        //Display the coffees
        brandsText.setText(brandsFormatted);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
