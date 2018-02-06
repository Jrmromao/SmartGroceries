package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class Product_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ListView pList;
    public TextView totalTextView;
    public ProdListAdapter_Scanned adapter = null;
    // san product button
    private Button scanBtn;
    private Button checkoutBtn;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private double totalPrice;
    private double totalProdPrice;



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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_, container, false);
        scanBtn = (Button) view.findViewById(R.id.FP_scanBtn);
        pList = (ListView) view.findViewById(R.id.product_List_view);
        totalTextView = (TextView) view.findViewById(R.id.total_txt);
        checkoutBtn = (Button) view.findViewById(R.id.FP_checkoutBtn);


        pList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProductModel pmlist = ProdLists.getScanProdList().get(position);
                ProdBundle.setProdDetails(pmlist);
                Bundle proBundle = new Bundle();
                proBundle.putInt("prodIndex", position);

                Fragment fragment = new ProductDetail_Fragment();
                fragment.setArguments(proBundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_profile, fragment, fragment.getTag()).commit();
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {


                if (totalPrice == 0.00) {
                    Toast.makeText(getActivity(), "Basket Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle totalBill = new Bundle();
                    totalBill.putDouble("totalBill", totalPrice);
                    Fragment f = new Checkout_Fragment();
                    f.setArguments(totalBill);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_profile, f, f.getTag()).commit();


                }
            }
        });


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Scan_Activity.class));

            }
        });

        for (ProductModel p : ProdLists.getScanProdList())
            totalPrice = p.getmPrice() * p.getProdUnits();

        totalTextView.setText("€" + String.format(String.valueOf(totalPrice), 0.00));

        return view;
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    public void onResume() {
        super.onResume();

        Toast.makeText(getActivity(), " resume Scan Prod list Size: " + ProdLists.getScanProdList().size(), Toast.LENGTH_SHORT).show();
        adapter = new ProdListAdapter_Scanned(getContext(), ProdLists.getScanProdList());

        adapter.notifyDataSetInvalidated();
        pList.setAdapter(adapter);
        // totalTextView.setText("€" + String.format(String.valueOf(totalPrice), 0.00));
    }

}