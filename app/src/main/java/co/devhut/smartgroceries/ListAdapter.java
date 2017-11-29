package co.devhut.smartgroceries;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.devhut.smartgroceries.ProductModel;
import co.devhut.smartgroceries.R;

/**
 * Created by jrmromao on 16/11/2017.
 * adapter class for the listView in ]
 * the show products fragment
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    // private Activity activity;
    private ArrayList<ProductModel> pList;

    private ProductModel pm;

    public ListAdapter(Context context, ProductModel pm, ArrayList<ProductModel> pList) {
        this.mContext = context;
        this.pm = pm;
        // this.activity = activity;
        this.pList = pList;
    }

    public ListAdapter(FragmentActivity activity, ProductModel productModel, ArrayList<ProductModel> prodList) {
    }

    @Override
    public int getCount() {
        return pList.size();
    }

    @Override
    public ProductModel getItem(int position) {
        return pList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_line, parent, false);
            final ViewHolder holder = new ViewHolder();

            holder.brand = (TextView) convertView.findViewById(R.id.pl_brand_txt);
            holder.name = (TextView) convertView.findViewById(R.id.pl_name_txt);
            holder.price = (TextView) convertView.findViewById(R.id.pl_price_txt);
            holder.params = (LinearLayout.LayoutParams) holder.brand.getLayoutParams();
            convertView.setTag(holder);
        }
        final ProductModel productModel = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        String pBrand = productModel.getmBrand();
        holder.brand.setText(pBrand);

        String pName = productModel.getmName();
        holder.name.setText(pName);


        String pPrice = String.valueOf(productModel.getmPrice());
        holder.price.setText(pPrice);

        return null;
    }

    //inner class
    static class ViewHolder {

        TextView brand;
        TextView name;
        TextView price;

        LinearLayout.LayoutParams params;

    }
}
