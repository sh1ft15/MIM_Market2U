package com.example.yeong.market2u.MIM_ManageProduct;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

/**
 * Created by yeong on 19/5/2017.
 */

public class ManageProductListAdapter extends ArrayAdapter<ProductModel> {
    public ManageProductListAdapter(Activity context, ArrayList<ProductModel> product) {
        super(context, 0, product);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_product_summary, parent, false);
        }

        final ProductModel product = getItem(position);

        TextView productName = (TextView) listItemView.findViewById(R.id.manage_product_name);
        TextView productPrice = (TextView) listItemView.findViewById(R.id.manage_product_price);
        TextView productQuantity = (TextView) listItemView.findViewById(R.id.manage_product_quantity);
        Button btnManageProduct = (Button) listItemView.findViewById(R.id.btnManage);

        productName.setText(product.getProductName());
        productPrice.setText("RM " + String.format("%.2f",
                Double.parseDouble(Double.toString(product.getProductPrice()))));
        productQuantity.setText("Quantity: " + Integer.toString(product.getProductRemainingQuantity()));

        btnManageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MIMController.setProductToPass(product);
                MIMController.navigateTo(getContext(), ManageProductActivity.class);
            }
        });

        return listItemView;
    }
}
