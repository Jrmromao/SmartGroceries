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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Product_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ArrayList<ProductModel> prodList;
    // san product button
    private Button scanBtn;
    private TextView txt_barcode;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Product_Fragment() {
        // Required empty public constructor
    }


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


        View view = inflater.inflate(R.layout.fragment_product_, container, false);
        scanBtn = (Button) view.findViewById(R.id.FP_scanBtn);

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
        //txt_barcode.setText(BarCode.getBarcode());
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
            //   prodList = new ArrayList<>();
            ProductModel pm1 = new ProductModel();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    URLs.URL_GET_PRODUCT, new Response.Listener<String>() {

                boolean flag = false;
                int count = 1;

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response); //converting response to json object
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            JSONObject prodJson = obj.getJSONObject("product");


                            for (ProductModel p : prodList) {

                                if (p.getmUPC_num() == prodJson.getInt("upc_num")) {
                                    flag = true;
                                    p.setProdCount(count++);
                                    Toast.makeText(getActivity(), "List: " + p.getProdCount(), Toast.LENGTH_SHORT).show();
                                } else
                                    flag = false;
                            }

                            if (!flag) {
                                ProductModel pm = new ProductModel(prodJson.getInt("upc_num"),
                                        prodJson.getString("name"),
                                        prodJson.getString("brand"),
                                        prodJson.getString("description"),
                                        prodJson.getString("expiry_date"),
                                        prodJson.getDouble("price"), count);
                                prodList.add(pm);

                                ProdLists.setScanProdList(prodList);

                            }
                            Log.d("SmartGroceries", "Success JSON Resold:" + response);
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
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("upc_num", BarCode.getBarcode()); //


                    return params;
                }
            };

            VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
            return null;
        }


    }
}