package sample.ahmaabdo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmad on Feb 17, 2018.
 */

public class ArabicMoviesAdapter extends RecyclerView.Adapter<ArabicMoviesAdapter.ArabicMovieHolder> {

    List<ArabicMovies> moviesList;

    public ArabicMoviesAdapter(List<ArabicMovies> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public ArabicMovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.arabic_movie_row, parent, false);

        return new ArabicMovieHolder(row);
    }

    //Called every time when Recycler view recreate it's list items
    @Override
    public void onBindViewHolder(ArabicMovieHolder holder, int position) {
        ArabicMovies movie = moviesList.get(position);

        holder.movieTitle.setText(movie.getMovieName());
        holder.movieDesc.setText(movie.getMovieStory());
        holder.movieRate.setText(movie.getMovieRate());

        holder.poster.setImageResource(movie.getPosterImg());

    }


    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class ArabicMovieHolder extends RecyclerView.ViewHolder {
        //Populate movie items with the view
        TextView movieTitle, movieRate, movieDesc;
        ImageView poster;

        public ArabicMovieHolder(View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movietitleTV);
            movieRate = itemView.findViewById(R.id.movierateTV);
            movieDesc = itemView.findViewById(R.id.moviedescTV);
            poster = itemView.findViewById(R.id.movieposteIMG);
        }
    }
}
