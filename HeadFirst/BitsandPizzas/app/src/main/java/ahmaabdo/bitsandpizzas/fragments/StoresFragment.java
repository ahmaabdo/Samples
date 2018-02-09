package ahmaabdo.bitsandpizzas.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahmaabdo.bitsandpizzas.Pizza;
import ahmaabdo.bitsandpizzas.R;
import ahmaabdo.bitsandpizzas.adapters.CaptionedImagesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoresFragment extends Fragment {

    public StoresFragment() {
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
        String[] pizzaNames = new String[Pizza.stores.length];
        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Pizza.stores[i].getName();
        }
        //Add pizza images to an array of integers
        int[] pizzaImages = new int[Pizza.stores.length];
        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = Pizza.stores[i].getImgResId();
        }
        //Pass the arrays to the adapter
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        //Display the card views in a layout with one column using a LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pizzaRecycler.setLayoutManager(layoutManager);
        return pizzaRecycler;
    }
}
