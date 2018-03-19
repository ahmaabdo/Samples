package example.ahmaabdo.moviedb.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import example.ahmaabdo.moviedb.R;
import example.ahmaabdo.moviedb.adapters.MoviesAdapter;
import example.ahmaabdo.moviedb.models.Movie;
import example.ahmaabdo.moviedb.models.MoviesResponse;
import example.ahmaabdo.moviedb.rest.ApiClient;
import example.ahmaabdo.moviedb.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * https://www.androidhive.info/2016/05/android-working-with-retrofit-http-library
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    //API KEY
    private static final String API_KEY = "6345d8bcbc1a5c86e8c091736835f218";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Populate recycler view
        final RecyclerView recyclerView = findViewById(R.id.rv_movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Populate retrofit object to my Api interface
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        //Calling getTopRatedMovies with api key and catch it's value at call
        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                //Getting results from Movie model from response
                List<Movie> movies = response.body().getResults();
                //setting adapter
                recyclerView.setAdapter(new MoviesAdapter(
                        movies,
                        R.layout.list_item_movie,
                        getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}