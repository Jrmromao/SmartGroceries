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

import java.util.List;

/**
 * Created by jrmromao on 25/11/2017.
 * adapter for the best product lisView
 */

public class ProdListAdapter extends ArrayAdapter<ProductModel> {


    public ProdListAdapter(@NonNull Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ProdListAdapter(Context context, int resource, List<ProductModel> products) {
        super(context, resource, products);
    }

    @SuppressLint({"InflateParams", "Assert", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v != null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.product_line, null);
        }

        ProductModel p = getItem(position);

        if (p == null) {

            assert v != null;
            TextView tt1 = (TextView) v.findViewById(R.id.pl_brand_txt);
            TextView tt2 = (TextView) v.findViewById(R.id.pl_name_txt);
            TextView tt3 = (TextView) v.findViewById(R.id.pl_price_txt);


            if (tt1 != null) {
                assert false;
                tt1.setText(p.getmBrande());
            }
            if (tt2 != null) {
                assert false;
                tt2.setText(p.getmName());
            }
            if (tt3 != null) {
                assert false;
                tt3.setText(p.getmPrice() + "");
            }

        }
        return v;

    }
}
