package co.devhut.smartgroceries;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

public class Scan_Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUESR_CAMERA = 1;
    private ZXingScannerView mScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUESR_CAMERA);

    }

    private boolean checkPermission()    {
        return (ContextCompat.checkSelfPermission(Scan_Activity.this, Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED);
    }


    public void onRequestPermissionsResult(final int requestCode, String permission[], int grantResults[]){

        switch (requestCode){
            case REQUESR_CAMERA :
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
               else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        if(shouldShowRequestPermissionRationale(CAMERA)){

                            displayAlertMessage("You need to have access for both permission",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{CAMERA}, REQUESR_CAMERA);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
        }
        break;
    }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission())
            {
                if(mScannerView == null){
                    mScannerView = new ZXingScannerView(this);
                    setContentView(mScannerView);
                }
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            }
            else{
                requestPermission();
            }
        }


    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mScannerView.stopCamera();
    }

    public void displayAlertMessage(String message , DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", (DialogInterface.OnClickListener) listener)
                .setNegativeButton("Cancel", (DialogInterface.OnClickListener) listener)
                .create()
                .show();
    }


    @Override
    public void handleResult(final Result result) {

        // set the barcode result  DO NOT DELETE THIS LINE
        /**/
        BarCode.setBarcodeNum(result.getText());/**/
        /**********************************************/

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("scan result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                new GetProductTask().execute();
            }
        });

        builder.setNeutralButton("Skip", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // if the user presses skip, reload the scan activity to scan another barcode
                mScannerView.resumeCameraPreview(Scan_Activity.this);
            }
        });

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

        //build the alert message to be shown
        builder.setMessage(BarCode.getBarcode());
        AlertDialog alert = builder.create();
        alert.show();
    }

//
//    public void getScannedProd(final String upc_code){
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                URLs.URL_GET_PRODUCT, new Response.Listener<String>() {
//
//            boolean flag = false;
//            int count = 1;
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject obj = new JSONObject(response); //converting response to json object
//                    //if no error in response
//                    if (!obj.getBoolean("error")) {
//                        JSONObject prodJson = obj.getJSONObject("product");
//
//                        ProductModel pm = new ProductModel(prodJson.getInt("upc_num"),
//                                prodJson.getString("name"),
//                                prodJson.getString("brand"),
//                                prodJson.getString("description"),
//                                prodJson.getString("expiry_date"),
//                                prodJson.getDouble("price"), count);
//
//                        ProdLists.setScanProdList(pm);
//                        Log.d("Result", "Success JSON Resold:" + response);
//                    }
//
//                } catch (JSONException e) {
//                    Log.e("SmartGroceries", "doInBackground catch: " + e.toString());
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Log.e("SmartGroceries", "onErrorResponse ERROR:" + error.toString());
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("upc_num", upc_code); //
//
//                return params;
//            }
//        };
//
//        VolleySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
//    }


    @SuppressLint("StaticFieldLeak")
    public class GetProductTask extends AsyncTask<String, String, String> {
        GetProductTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    URLs.URL_GET_PRODUCT, new Response.Listener<String>() {
                boolean flag = false;
                int count = 1;
                int indexOf = 0;

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response); //converting response to json object
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            JSONObject prodJson = obj.getJSONObject("product");

                            if (ProdLists.getScanProdList().isEmpty()) {

                                ProductModel pm = new ProductModel(prodJson.getInt("upc_num"),
                                        prodJson.getString("name"),
                                        prodJson.getString("brand"),
                                        prodJson.getString("description"),
                                        prodJson.getString("expiry_date"),
                                        prodJson.getDouble("price"), count);
                                ProdLists.setScanProdList(pm);
                            } else {
                                for (ProductModel pm : ProdLists.getScanProdList()) {
                                    if (pm.getmUPC_num() == prodJson.getInt("upc_num")) {
                                        indexOf = ProdLists.getScanProdList().indexOf(pm);

                                        ProductModel updatePM = new ProductModel(prodJson.getInt("upc_num"),
                                                prodJson.getString("name"),
                                                prodJson.getString("brand"),
                                                prodJson.getString("description"),
                                                prodJson.getString("expiry_date"),
                                                prodJson.getDouble("price"), count++);
                                        ProdLists.getScanProdList().set(indexOf, updatePM);

                                        Log.e("Loop", "Count " + pm.getProdUnits());
                                    } else {
                                        flag = true;
                                        Log.e("Loop", "flag is equal to: " + flag);
                                    }
                                }
                                if (flag) {
                                    ProductModel pm = new ProductModel(prodJson.getInt("upc_num"),
                                            prodJson.getString("name"),
                                            prodJson.getString("brand"),
                                            prodJson.getString("description"),
                                            prodJson.getString("expiry_date"),
                                            prodJson.getDouble("price"), count);
                                    ProdLists.setScanProdList(pm);
                                    flag = false;
                                }
                            }
                        }
                        Log.d("Result", "Success JSON Resold:" + response);
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
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("upc_num", BarCode.getBarcode()); //


                    return params;
                }
            };

            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            return null;
        }

    }

}
