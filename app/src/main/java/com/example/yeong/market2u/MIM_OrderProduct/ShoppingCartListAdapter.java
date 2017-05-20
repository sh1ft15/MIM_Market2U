package com.example.yeong.market2u.MIM_OrderProduct;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

public class ShoppingCartListAdapter extends ArrayAdapter<ShoppingCartModel> {
    public ShoppingCartListAdapter(Activity context, ArrayList<ShoppingCartModel> shoppingCart) {
        super(context, 0, shoppingCart);
    }

    private static String truncate(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len) + "...";
        } else {
            return str;
        }
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_shopping_cart, parent, false);
        }

        ShoppingCartModel shoppingCart = getItem(position);

        // listItemView.setTag(shoppingCart.getShoppingCartID()); // id needed for deletion process

        TextView txtCartItemName = (TextView) listItemView.findViewById(R.id.shopping_cart_item_name);
        TextView txtCartItemPrice = (TextView) listItemView.findViewById(R.id.shopping_cart_item_price);
        TextView txtCartItemQuantity = (TextView) listItemView.findViewById(R.id.shopping_cart_item_quantity);
        ImageButton btnRemoveItem = (ImageButton) listItemView.findViewById(R.id.btnRemoveItem);

        String price = String.format("%.2f", shoppingCart.getProductPrice());
        String quantity = Integer.toString(shoppingCart.getProductOrderedQuantity());

        String productName = shoppingCart.getProductName();
        productName = truncate(productName, 10);

        txtCartItemName.setText(productName);
        txtCartItemPrice.setText("RM " + price);
        txtCartItemQuantity.setText("Quantity : " +quantity);

        btnRemoveItem.setTag(shoppingCart.getShoppingCartID());
        btnRemoveItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Toast.makeText(getContext(), v.getTag().toString() , Toast.LENGTH_SHORT).show();
                MIMController.getInstance().removeShoppingCartItem(v.getContext(), v.getTag().toString());
            }
        });

        return listItemView;
    }
}


//Glide.with(cart.shoppingCartContext).load(shoppingCart.getProductImageUrl()).into(imgCartItemImage);
// ImageView imgCartItemImage = (ImageView) listItemView.findViewById(R.id.shopping_cart_item_image);
// ShoppingCartActivity cart = new ShoppingCartActivity();