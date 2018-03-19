package example.ahmaabdo.retrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import example.ahmaabdo.retrofit.models.Post;
import example.ahmaabdo.retrofit.models.ResultModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/*
* http://www.hendiware.com/android-webservices-%D8%A8%D8%A7%D9%84%D8%B9%D8%B1%D8%A8%D9%8A%D8%A9-retrofit/
*/

public class MainActivity extends AppCompatActivity {

    TextView textPost;

    //An interface that contains all connections needs to be processed
    //Like Method for sending data, method for receiving data etc..
    public interface mApi {
        @GET("v2/5aaca87b2f0000650020489d")
        Call<ResultModel> getPosts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textPost = findViewById(R.id.text_post);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Populate retrofit object to my Api interface
        mApi api = retrofit.create(mApi.class);

        //Calling getPosts and catch it's value at Call
        Call<ResultModel> connection = api.getPosts();
        connection.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(@NonNull Call<ResultModel> call, @NonNull Response<ResultModel> response) {
                //Getting results from Post model from response
                List<Post> posts = response.body().getPosts();
                for (int i = 0; i < posts.size(); i++) {
                    String postContent = posts.get(i).getPost_content();
                    textPost.append("\n" + postContent + "\n");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResultModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
