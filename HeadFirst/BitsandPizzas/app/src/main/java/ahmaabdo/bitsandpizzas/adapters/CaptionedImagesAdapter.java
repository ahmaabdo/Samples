package ahmaabdo.bitsandpizzas.adapters;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ahmaabdo.bitsandpizzas.R;

/**
 * Created by Ahmad on Feb 9, 2018.
 */

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    public interface Listener {
        void onClick(int position);
    }

    //Variables used to hold the pizza data
    private String[] captions;
    private int[] imgIds;
    private Listener listener;

    //Passing the data to the adapter using it's constructor
    public CaptionedImagesAdapter(String[] captions, int[] imgIds) {
        this.captions = captions;
        this.imgIds = imgIds;
    }

    /**
     * Tells the adapter how to construct the view holder
     * This method gets called when the recycler view needs to create a view holder
     *
     * @param parent:   is the recycler view itself
     * @param viewType: used to display different kinds of view for different items in the list
     */
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Gets the LayoutInflater object and use it to turn the layout into a card view
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);

        return new ViewHolder(cardView);
    }

    /**
     * Add the data to the card view
     * This called whenever the recycler view needs to display data in a view holder
     * The recycler view called this method when it wants to use a view holder for a new piece of data
     *
     * @param holder:   view holder the data needs to be bound to.
     * @param position: the position in the data set of the data that needs to be bound
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        //Populate the CardView’s ImageView and TextView with data
        ImageView imageView = cardView.findViewById(R.id.info_image);
        //Display the image in the imageView
        Drawable drawable =
                ContextCompat.getDrawable(cardView.getContext(), imgIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = cardView.findViewById(R.id.info_text);
        //Display the captions in the TextView
        textView.setText(captions[position]);
        //Add the listener to the card view
        cardView.setOnClickListener(new View.OnClickListener() {
            //When the card view is clicked, call the listener onClick method
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick(position);
            }
        });
    }

    //The number of data items
    @Override
    public int getItemCount() {
        return captions.length;
    }

    //Activities and fragments will use this method to register as listener
    public void setListener(Listener listener) {
        this.listener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        //Each ViewHolder will display a CardView
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
