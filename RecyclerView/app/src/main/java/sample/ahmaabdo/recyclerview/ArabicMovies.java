package sample.ahmaabdo.recyclerview;

/**
 * Created by Ahmad on Feb 17, 2018.
 */

public class ArabicMovies {
    private String movieName, movieRate, movieStory;
    private int posterImg;

    public String getMovieName() {
        return movieName;
    }

    public String getMovieRate() {
        return movieRate;
    }

    public String getMovieStory() {
        return movieStory;
    }

    public int getPosterImg() {
        return posterImg;
    }

    public ArabicMovies(String movieName, String movieRate, String movieStory, int posterImg) {
        this.movieName = movieName;
        this.movieRate = movieRate;
        this.movieStory = movieStory;
        this.posterImg = posterImg;
    }
}
