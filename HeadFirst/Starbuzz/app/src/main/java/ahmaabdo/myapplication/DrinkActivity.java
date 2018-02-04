package ahmaabdo.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        //Get the drink from the intent
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);
        //This gives us a Drink object containing all the information we need to update the view attributes in the activity:
        Drink drink = Drink.drinks[drinkId];
        //Populate the drink name, description, and photo
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        ImageView photo = findViewById(R.id.photo);

        //Populate the views with the drink data
        name.setText(drink.getName());
        description.setText(drink.getDescription());
        photo.setImageResource(drink.getImgRedID());
        photo.setContentDescription(drink.getName());
    }
}
