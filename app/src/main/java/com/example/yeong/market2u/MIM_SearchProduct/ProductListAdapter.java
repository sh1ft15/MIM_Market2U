package com.example.yeong.market2u.MIM_SearchProduct;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

/**
 * Created by Banana on 4/18/2017.
 */

public class ProductListAdapter extends ArrayAdapter<ProductModel>{

    public ProductListAdapter(Activity context, ArrayList<ProductModel> product_models) {

        super(context, 0, product_models);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_list_content, parent, false);
        }

        ProductModel product_model = getItem(position);

        listItemView.setTag(product_model.getProductID());

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_tag);
        String name = product_model.getProductName();
        name = truncate(name, 5);

        nameTextView.setText(name);

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.price_tag);
        String price = Double.toString(product_model.getProductPrice());
        if(price == null ||price.isEmpty()){
            price = "0.00";
        }
        priceTextView.setText("RM " + price);

        TextView quantityTextView = (TextView) listItemView.findViewById(R.id.quantity_tag);
        quantityTextView.setText(Integer.toString(product_model.getProductRemainingQuantity()));


        return listItemView;
    }

    private static String truncate(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len) + "...";
        } else {
            return str;
        }
    }


}
