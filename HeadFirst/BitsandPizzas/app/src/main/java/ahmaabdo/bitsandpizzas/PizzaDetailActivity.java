package ahmaabdo.bitsandpizzas;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class PizzaDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PIZZA_ID = "pizzaId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);
        //Setting the toolbar as the activity's app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get the pizza that user chooses from the intent

        int pizzaId = (Integer) getIntent().getExtras().get(EXTRA_PIZZA_ID);
        //Populate the text and the image
        String pizzaName = Pizza.pizzas[pizzaId].getName();
        int pizzaImage = Pizza.pizzas[pizzaId].getImgResId();

        TextView textView = findViewById(R.id.pizza_text);
        ImageView imageView = findViewById(R.id.pizza_image);

        textView.setText(pizzaName);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pizzaImage));

    }
}
