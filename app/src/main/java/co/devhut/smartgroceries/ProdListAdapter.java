package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrmromao on 25/11/2017.
 * adapter for the best product lisView
 */

public class ProdListAdapter extends ArrayAdapter<ProductModel> {


    public ProdListAdapter(@NonNull Context context, ArrayList<ProductModel> pList) {
        super(context, 0, pList);
    }

    public ProdListAdapter(Context context, int resource, List<ProductModel> products) {
        super(context, resource, products);
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ProductModel pm = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_line, parent, false);
        }

        TextView prodBrand = (TextView) convertView.findViewById(R.id.pl_brand_txt);
        TextView prodName = (TextView) convertView.findViewById(R.id.pl_name_txt);
        TextView prodPrice = (TextView) convertView.findViewById(R.id.pl_price_txt);

        assert pm != null;
        prodBrand.setText(pm.getmBrand());
        prodName.setText(pm.getmName());
        prodPrice.setText("" + pm.getmPrice());


        return convertView;

    }
}
