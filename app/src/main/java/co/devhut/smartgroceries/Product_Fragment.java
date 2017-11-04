package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Product_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // san product button
    private Button scanBtn;
    private TextView txt_barcode;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Product_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Products_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Product_Fragment newInstance(String param1, String param2) {
        Product_Fragment fragment = new Product_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Product_Fragment newInstance(String param1) {
        Product_Fragment fragment = new Product_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_, container, false);

        scanBtn = (Button) view.findViewById(R.id.FP_scanBtn);

        //  txt_barcode = (TextView) view.findViewById(R.id.textView4);


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Scan_Activity.class));
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        txt_barcode.setText(BarCode.getBarcode());
        new GetProductTask().execute();

    }


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


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_GET_PRODUCT, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);//converting response to json object

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            JSONObject prodJson = obj.getJSONObject("user");

                            ProductModel pm = new ProductModel(prodJson.getInt("upc_num"),
                                    prodJson.getString("name"),
                                    prodJson.getString("description"),
                                    prodJson.getString("expiry_date"),
                                    prodJson.getDouble("price"));

                            Log.d("SmartGroceries", "Success JSON Resold:" + response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
                    params.put("upc_num", BarCode.getBarcode());           //


                    return params;
                }
            };

            VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
            return null;
        }


    }
}
















