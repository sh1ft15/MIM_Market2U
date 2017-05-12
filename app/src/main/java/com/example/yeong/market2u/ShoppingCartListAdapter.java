package com.example.yeong.market2u;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.MIM_OrderProduct.ShoppingCartActivity;

import java.util.ArrayList;

public class ShoppingCartListAdapter extends ArrayAdapter<ShoppingCartModel> {
    public ShoppingCartListAdapter(Activity context, ArrayList<ShoppingCartModel> shoppingCart) {
        super(context, 0, shoppingCart);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shopping_cart, parent, false);
        }

        ShoppingCartModel shoppingCart = getItem(position);
        TextView txtCartItemName = (TextView) listItemView.findViewById(R.id.shopping_cart_item_name);
        TextView txtCartItemPrice = (TextView) listItemView.findViewById(R.id.shopping_cart_item_price);
        TextView txtCartItemQuantity = (TextView) listItemView.findViewById(R.id.shopping_cart_item_quantity);
        //ImageView imgCartItemImage = (ImageView) listItemView.findViewById(R.id.shopping_cart_item_image);
        //ShoppingCartActivity cart = new ShoppingCartActivity();
        String price = Double.toString(shoppingCart.getProductPrice());
        String quantity = Integer.toString(shoppingCart.getProductOrderedQuantity());

        txtCartItemName.setText(shoppingCart.getProductName());
        txtCartItemPrice.setText(price);
        txtCartItemQuantity.setText(quantity);
        //Glide.with(cart.shoppingCartContext).load(shoppingCart.getProductImageUrl()).into(imgCartItemImage);

        return listItemView;
    }
}
