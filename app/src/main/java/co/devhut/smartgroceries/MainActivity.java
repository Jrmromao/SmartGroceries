package co.devhut.smartgroceries;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {


    final int REQUEST_CODE = 123;
    String WS_URL = "http://192.168.0.3:8081/WebService/webresources/groceries/login";
    public boolean flag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registerLink = (TextView) findViewById(R.id.newAccountLink);
        Button login_btn = (Button) findViewById(R.id.btn_login);

        final ProgressBar load = (ProgressBar) findViewById(R.id.pb_loadProfile);

       load.setVisibility(View.GONE);

        //
        //  create an array of intends object
        //
        final Intent[] intent = new Intent[3];
       
       
       //
       //TODO: register button
       // 
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              // register intent in the first position of the inten
              intent[0] =  new Intent(getApplicationContext(), Register_Activity.class);
                startActivity(intent[0]);


                //Toast.makeText(getApplicationContext(), "Register Working", Toast.LENGTH_SHORT).show();


               }
            } );


        //
        //TODO: login button
        //
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                //Explicit Intent
//                intent[1] = new Intent(getApplicationContext(), Main2Activity.class);
//                intent[2] = new Intent(getApplicationContext(), Scan_Activity.class);

                try{

                  //  startActivity(intent[1]);

                    Login();

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }


              //  loadProgressBar(load);
            }
        } );

    }

    //TODO: add onResume here
    @Override
    protected void onResume(){
        super.onResume();

        Log.d("SmartGroceries","onResume Method");
    }



    //TODO: add onResume here

    private void Login(){

        TextView txt_username = (TextView) findViewById(R.id.txt_username);
        TextView txt_password = (TextView) findViewById(R.id.txt_password);


        String username =  txt_username.getText().toString();
        String password =  txt_password.getText().toString();




        RequestParams params = new RequestParams();

        params.put("name", username);
        params.put("password", password);

        letsDoSomeNetworking(params);


    }





    private void letsDoSomeNetworking(final RequestParams params){



        AsyncHttpClient clinte = new AsyncHttpClient();


        clinte.get(WS_URL, params, new JsonHttpResponseHandler()  {

//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                Log.d("SmartGroceries", "onSuccess "+statusCode+" params:" + params.toString());
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.d("SmartGroceries", "onFailure "+statusCode+" params:" + params.toString());

         //   }

            @Override
            public void onSuccess(int statusCode, Header[] header, JSONObject response){
                Log.d("SmartGroceries", "onSuccess "+statusCode+" params:" + params.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] header, Throwable e,  JSONObject response){
                flag = true;
                Log.e("SmartGroceries", "onFailure "+statusCode+" params:" + params.toString());
//                Log.e("SmartGroceries", "Fail " + params.toString());
//                Log.e("SmartGroceries", "Status Code "+statusCode);
//                Toast.makeText(getApplicationContext(),"Request Failed", Toast.LENGTH_SHORT).show();



            }



        });





    }




        //
        //TODO: progress bar
        //
        public void loadProgressBar(ProgressBar load){

            load.setVisibility(View.VISIBLE);

            load.setMax(5);


    }




}
