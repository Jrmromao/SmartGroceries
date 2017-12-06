package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
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

import static co.devhut.smartgroceries.ProdLists.getScanProdList;
import static co.devhut.smartgroceries.ProdLists.setScanProdList;


public class Product_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ListView pList;
    public ProdListAdapter_Scanned adapter = null;
    // san product button
    private Button scanBtn;
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
        pList = (ListView) view.findViewById(R.id.product_List_view);


        //Toast.makeText(getActivity(), " onCreateView Scan Prod list Size:
        // "+ProdLists.getScanProdList().size(), Toast.LENGTH_SHORT).show();


        pList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProductModel pmlist = new ProductModel();
                pmlist = ProdLists.getScanProdList().get(position);
                ProdBundle.setProdDetails(pmlist);

                Fragment fragment = new ProductDetail_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_profile, fragment, fragment.getTag()).commit();


            }
        });



        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Scan_Activity.class));
            }
        });


        return view;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getActivity(), " resume Scan Prod list Size: " + ProdLists.getScanProdList().size(), Toast.LENGTH_SHORT).show();
        adapter = new ProdListAdapter_Scanned(getContext(), ProdLists.getScanProdList());

        adapter.notifyDataSetInvalidated();
        pList.setAdapter(adapter);

    }
}