package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrmromao on 25/11/2017.
 * this adapter class is the one used
 * to present the units scanned
 */

public class ProdListAdapter_Scanned extends ArrayAdapter<ProductModel> {


    ProdListAdapter_Scanned(@NonNull Context context, ArrayList<ProductModel> pList) {
        super(context, 0, pList);
    }

    public ProdListAdapter_Scanned(Context context, int resource, List<ProductModel> products) {
        super(context, resource, products);
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ProductModel pm = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_product_scanned, parent, false);
        }

        TextView prodBrand = (TextView) convertView.findViewById(R.id.lps_brand_txt);
        TextView prodName = (TextView) convertView.findViewById(R.id.lps_name_txt);
        TextView prodPrice = (TextView) convertView.findViewById(R.id.lps_price_txt);
        TextView prodUnit = (TextView) convertView.findViewById(R.id.lps_prodUnit);

        assert pm != null;
        prodBrand.setText(pm.getmBrand());
        prodName.setText(pm.getmName());
        prodPrice.setText("" + pm.getmPrice());
        prodUnit.setText("" + pm.getProdCount());


        return convertView;

    }
}
