package example.ahmaabdo.gmail.api;

import java.util.List;

import example.ahmaabdo.gmail.models.Message;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ahmad on Mar 21, 2018.
 * This class contains the rest api endpoints and the type of response it's expecting. In this
 * case we have only one endpoint e.g 5ab1040e2e0000e60ae8b908
 * from http://www.mocky.io/v2/5ab1040e2e0000e60ae8b908
 */

public interface ApiInterface {

    @GET("5ab1040e2e0000e60ae8b908")
    Call<List<Message>> getInbox();
}
