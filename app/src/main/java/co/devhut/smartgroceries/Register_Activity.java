package co.devhut.smartgroceries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;


public class Register_Activity extends AppCompatActivity {

    public static final String TAG = Register_Activity.class.getSimpleName();
    final int REQUEST_CODE = 123;
    final String REQUESTAG = "Cancel All";
    EditText eText_name;
    EditText eText_password;
    EditText eText_email;
    String name, password, email;
    private RequestQueue mRequestQueue;
    private StringRequest StringRequest;
    private ProgressBar load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eText_name = (EditText) findViewById(R.id.txtReg_name);
        eText_email = (EditText) findViewById(R.id.txtReg_email);
        eText_password = (EditText) findViewById(R.id.txtReg_password);
        Button createAccount_btn = (Button) findViewById(R.id.regSignIn_btn);


        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, OptionsDrawer_Activity.class));
            return;
        }

        createAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    RegUser();
                } catch (Exception e) {
                    Log.e("SmartGroceries", "ERROR ON REGISTER NEW USER: " + e.toString());
                }
            }
        });
    }


    public void RegUser() {

        name = eText_name.getText().toString();
        password = eText_password.getText().toString();
        email = eText_email.getText().toString();

        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();


        //check if fields have values
        if (TextUtils.isEmpty(name)) {
            eText_name.setError("Please Enter Name");
            eText_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            eText_password.setError("Please Enter Password");
            eText_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            eText_email.setError("Please Enter an Email");
            eText_email.requestFocus();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);//converting response to json object

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        JSONObject userJson = obj.getJSONObject("user");

                        UserModel user = new UserModel(userJson.getInt("id"),
                                userJson.getString("name"),
                                userJson.getString("email"));
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        finish();
                        startActivity(new Intent(getApplicationContext(), Second_activity.class));
                        Log.d("SmartGroceries", "Success JSON Resold:" + response.toString());

                    } else {
                        Log.e("SmartGroceries", "ERROR:" + response.toString());
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                params.put("name", name);           //
                params.put("password", password);       //  values to be sent on the post request
                params.put("email", email);       //  values to be sent on the post request

                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



























}
