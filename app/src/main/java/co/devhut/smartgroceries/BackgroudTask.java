package co.devhut.smartgroceries;

import android.content.Context;
import android.hardware.camera2.TotalCaptureResult;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by jrmromao on 25/10/2017.
 */

public class BackgroudTask extends AsyncTask<String, Void, String> {


    Context _ctx;


    public BackgroudTask(Context _ctx) {
        this._ctx = _ctx;
    }

    @Override
    protected String  doInBackground(String... params) {

        final String REG_URL = "http://192.168.0.3:8081/AndroidApp/register.php";
        String method = params[0];

        if (method.equals("register")){

            String name     = params[1];
            String password = params[2];
            String email    = params[3];

            try {
                URL url = new URL(REG_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();

                BufferedWriter  bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getErrorStream();
                IS.close();

                return "Registration Success";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(_ctx, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
