package co.devhut.smartgroceries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static co.devhut.smartgroceries.R.id.button_createAccount;

public class Register_Activity extends AppCompatActivity {

    EditText eText_name;
    EditText eText_password;
    EditText eText_email;

    String name, password, email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eText_name = (EditText) findViewById(R.id.editText_name);
        eText_email = (EditText) findViewById(R.id.editText_email);
        eText_password = (EditText) findViewById(R.id.editText_password);
        Button createAccount_btn = (Button) findViewById(R.id.button_createAccount);


        createAccount_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegUser(v);
            }
        });


    }


    public void RegUser(View view){

        name = eText_name.getText().toString();
        password = eText_password.getText().toString();
        email = eText_email.getText().toString();



        finish();



    }



}
