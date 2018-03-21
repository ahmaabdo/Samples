package example.ahmaabdo.gmail.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmad on Mar 21, 2018.
 * This class creates the static retrofit instance
 */

public class ApiClient {

    private static final String BASE_URL = "http://www.mocky.io/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}