package ahmaabdo.bitsandpizzas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahmaabdo.bitsandpizzas.Pizza;
import ahmaabdo.bitsandpizzas.PizzaDetailActivity;
import ahmaabdo.bitsandpizzas.R;
import ahmaabdo.bitsandpizzas.adapters.CaptionedImagesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaFragment extends Fragment {

    public PizzaFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecyclerView pizzaRecycler =
                (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        //Add pizza name to an array of strings
        String[] pizzaNames = new String[Pizza.pizzas.length];
        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }
        //Add pizza images to an array of integers
        int[] pizzaImages = new int[Pizza.pizzas.length];
        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = Pizza.pizzas[i].getImgResId();
        }
        //Pass the arrays to the adapter
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        //Display the card views in a grid with two columns using a GridLayoutManager
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);
        //Implements the Listener onClick method and starts PizzaDetailActivity with the id of the pizza the user chose
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(getActivity(), PizzaDetailActivity.class)
                        .putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position));
            }
        });
        return pizzaRecycler;
    }
}
