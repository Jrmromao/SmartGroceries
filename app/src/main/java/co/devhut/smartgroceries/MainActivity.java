package co.devhut.smartgroceries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView registerLink = (TextView) findViewById(R.id.newAccountLink);
        Button login_btn = (Button) findViewById(R.id.btn_login);
        TextView txt_username = (TextView) findViewById(R.id.txt_username);
        TextView txt_password = (TextView) findViewById(R.id.txt_password);
        final ProgressBar load = (ProgressBar) findViewById(R.id.pb_loadProfile);

       load.setVisibility(View.GONE);

        //
        //  create an array of intends object
        //
        final Intent[] intent = new Intent[3];
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              // register intent in the first position of the inten
              intent[0] =  new Intent(getApplicationContext(), Register_Activity.class);
                startActivity(intent[0]);


                Toast.makeText(getApplicationContext(), "Register Working", Toast.LENGTH_SHORT).show();


               }
            } );

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Explicit Intent
                intent[1] = new Intent(getApplicationContext(), Main2Activity.class);
                intent[2] = new Intent(getApplicationContext(), Scan_Activity.class);

                try{

                    startActivity(intent[1]);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }


              //  loadProgressBar(load);
            }
        } );

    }




        public void loadProgressBar(ProgressBar load){

            load.setVisibility(View.VISIBLE);

            load.setMax(5);


    }




}
