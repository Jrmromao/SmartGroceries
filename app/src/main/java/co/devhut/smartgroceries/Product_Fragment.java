package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_, container, false);
        scanBtn = (Button) view.findViewById(R.id.FP_scanBtn);
        pList = (ListView) view.findViewById(R.id.product_List_view);
        totalTextView = (TextView) view.findViewById(R.id.total_txt);

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
                Bundle myBundle = new Bundle();

                Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();

            }
        });



        scanBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), Scan_Activity.class));
                loadProdList();
            }
        });

        for (ProductModel p : ProdLists.getScanProdList())
            totalPrice = p.getmPrice() * p.getProdUnits();

        totalTextView.setText("€" + String.format(String.valueOf(totalPrice), 0.00));

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
        totalTextView.setText("€" + String.format(String.valueOf(totalPrice), 0.00));
    }


    public void loadProdList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_GET_BEST_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response); //converting response to json object

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                JSONObject prodJson = obj.getJSONObject("product");

                                ProductModel bp = new ProductModel(); //create a new instance of the project model class
                                bp.setmUPC_num(prodJson.getInt("upc_num"));
                                bp.setmBrand(prodJson.getString("brand"));
                                bp.setmName(prodJson.getString("name"));
                                bp.setmDescription(prodJson.getString("description"));
                                bp.setmExpiryDate(prodJson.getString("expiry_date"));
                                bp.setPrice(prodJson.getDouble("price"));
                                bp.setProdUnits(1);

                                if (!ProdLists.getScanProdList().contains(bp) || ProdLists.getScanProdList().isEmpty()) {
                                    ProdLists.setScanProdList(bp);
                                    totalPrice = bp.getmPrice();
                                }

                                else {

                                    bp.setmUPC_num(prodJson.getInt("upc_num"));
                                    bp.setmBrand(prodJson.getString("brand"));
                                    bp.setmName(prodJson.getString("name"));
                                    bp.setmDescription(prodJson.getString("description"));
                                    bp.setmExpiryDate(prodJson.getString("expiry_date"));
                                    bp.setPrice(prodJson.getDouble("price"));
                                    ProductModel pm2 = ProdLists.getScanProdList().get( //
                                            ProdLists.getScanProdList().indexOf(bp));   //  handle the units being bought
                                    bp.setProdUnits(pm2.getProdUnits() + 1);            //
                                    ProdLists.getScanProdList().set(ProdLists.getScanProdList().indexOf(bp), bp); // update the array list with the number of units being bought
//                                    Toast.makeText(getActivity(), "count  " + bp.getProdCount(), Toast.LENGTH_SHORT).show();
                                }

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


        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }


}