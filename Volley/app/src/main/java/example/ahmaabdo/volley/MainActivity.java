package example.ahmaabdo.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import example.ahmaabdo.volley.app.AppController;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private StringRequest mStringRequest;
    private TextView responseText;
    /*
     * Simple JSON test:
     * https://www.mocky.io
      */
    private String url = "http://www.mocky.io/v2/5aac90c12f00004f00204894";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseText = findViewById(R.id.response_text);

        //Invalidate cache
        // AppController.getInstance().getRequestQueue().getCache().invalidate("url",false);

        //Deleting cache from specific
        // AppController.getInstance().getRequestQueue().getCache().remove("url");

        //Deleting cache from all
        //AppController.getInstance().getRequestQueue().getCache().clear();


    }

    //Called when request button clicked
    public void requestBtnClicked(View view) {

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                responseText.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG, "Error :" + error.toString());
            }
        });
        AppController.getInstance().getRequestQueue().add(mStringRequest);
    }
}
