package co.devhut.smartgroceries;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//volley api
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Second_activity extends AppCompatActivity {

    public static final String TAG = Second_activity.class.getSimpleName();
    final int REQUEST_CODE = 123;
    final String REQUESTAG = "Cancel All";
    //private StringRequest StringRequest;
    private ProgressBar load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        TextView registerLink = (TextView) findViewById(R.id.newAccountLink);
        Button login_btn = (Button) findViewById(R.id.btn_login);

        load = (ProgressBar) findViewById(R.id.pb_loadProfile);
        load.setVisibility(View.GONE);
        //load list with best products

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, OptionsDrawer_Activity.class));
            return;
        }


        //TODO: register button
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // register intent in the first position of the intent

                startActivity(new Intent(getApplicationContext(), Register_Activity.class));
                //Toast.makeText(getApplicationContext(), "Register Working", Toast.LENGTH_SHORT).show();
            }
        });


        //TODO: login button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });


    }

    //TODO: add onResume here
    @Override
    protected void onResume() {
        super.onResume();
        //progress bar will load for 2 seconds
        load.setVisibility(View.GONE);
    }


    // method to process the login
    private void Login() {

        TextView txt_username = (TextView) findViewById(R.id.txt_username);
        TextView txt_password = (TextView) findViewById(R.id.txt_password);




        //load.setVisibility(View.VISIBLE);
        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();
        //RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        // getting the error because i was nor instantiating the new request queue

        final String username = txt_username.getText().toString();
        final String password = txt_password.getText().toString();

        //check if fields have values
        if (TextUtils.isEmpty(username)) {
            txt_username.setError("Please Enter Username");
            txt_username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            txt_password.setError("Please Enter Password");
            txt_password.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URLs.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        JSONObject userJson = obj.getJSONObject("user");
                        Toast.makeText(getApplicationContext(), userJson.toString(), Toast.LENGTH_SHORT).show();

                        UserModel user = new UserModel(userJson.getInt("id"),
                                userJson.getString("name"),
                                userJson.getString("email"),
                                userJson.getString("dateJoined"));

                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBarLoad();
                                startActivity(new Intent(getApplicationContext(), OptionsDrawer_Activity.class));
                                finish();
                            }
                        }, 1000);


                    } else /*if(obj.getBoolean("error"))*/ {
                        load.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                        Log.e("SmartGroceries", "ERROR JSON Resold:" + response);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("SmartGroceries", "onErrorResponse ERROR:" + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", username);       //
                params.put("password", password);   //  values to be sent on the post request

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void progressBarLoad() {
        load.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);
        load.setVisibility(View.GONE);
    }











}