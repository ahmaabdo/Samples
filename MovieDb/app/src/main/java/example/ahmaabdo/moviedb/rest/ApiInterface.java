package example.ahmaabdo.moviedb.rest;

import example.ahmaabdo.moviedb.models.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ahmad on Mar 19, 2018.
 * The endpoints are defined inside of an interface using special retrofit annotations to
 * encode details about the parameters and request method.
 * In addition, the return value is always a parameterized Call<T> object such
 * as Call<MovieResponse>.
 * For instance, the interface defines each endpoint in the following way.
 */

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
