package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetail_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public TextView prodNameTxt = null;
    public TextView prodBrandTxt = null;
    public TextView prodExpireDateTxt = null;
    public TextView prodDesripTxt = null;
    public ProductModel pm = null;
    public ImageView back_btn = null;

    public TextView unitsTxt = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public ProductDetail_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductDetail_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetail_Fragment newInstance(String param1, String param2) {
        ProductDetail_Fragment fragment = new ProductDetail_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStop() {
        super.onStop();
        pm = null;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        Bundle proBuncle = new Bundle();
        int index = proBuncle.getInt("prodIndex");

        pm = ProdLists.getScanProdList().get(index);
        prodNameTxt = (TextView) view.findViewById(R.id.pd_prodNameTxt);
        prodBrandTxt = (TextView) view.findViewById(R.id.pd_proBrandTxt);
        prodDesripTxt = (TextView) view.findViewById(R.id.pd_prodDescrip);
        prodExpireDateTxt = (TextView) view.findViewById(R.id.pd_ExpireDateTxt);
        unitsTxt = (TextView) view.findViewById(R.id.pd_unitsTxt);

        // set the data to its correspondent textField
        prodNameTxt.setText(ProdBundle.getProdDetails().getmName());
        prodBrandTxt.setText(ProdBundle.getProdDetails().getmBrand());
        prodExpireDateTxt.setText(ProdBundle.getProdDetails().getmExpiryDate());
        unitsTxt.setText(String.valueOf(ProdBundle.getProdDetails().getProdUnits()));
        prodDesripTxt.setText(ProdBundle.getProdDetails().getmDescription());
        back_btn = (ImageView) view.findViewById(R.id.backBtn);

        // go back method
        back_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Fragment fragment = new Product_Fragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_profile, fragment, fragment.getTag()).commit();
                //getActivity().finish();
            }
        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
