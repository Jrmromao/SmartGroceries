package co.devhut.smartgroceries;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class First_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        loadProdList();
        //wait 3 seconds to load the second activity, login activity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Second_activity.class));
                finish();
            }
        }, 1500);

    }


    public void loadProdList() {
        final ArrayList<ProductModel> bestProdList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_GET_BEST_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response); //converting response to json object

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Log.e("SmartGroceries", "doInBackground catch: " + response);

                                JSONObject prodJson = obj.getJSONObject("product");
                                ProductModel bp = new ProductModel(prodJson.getInt("upc_num"),
                                        prodJson.getString("name"),
                                        prodJson.getString("brand"),
                                        prodJson.getString("description"),
                                        prodJson.getString("expiry_date"),
                                        prodJson.getDouble("price"));

                                ProdLists.setBestProdList(bp);


                            }
                        } catch (JSONException e) {
                            Log.e("SmartGroceries", "doInBackground catch: " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("SmartGroceries", "onErrorResponse ERROR:" + error.toString());
                    }
                }) {


        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

}
