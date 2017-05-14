package com.example.yeong.market2u.MIM_OrderProduct;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.OrderModel;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.R;
import com.example.yeong.market2u.ShoppingCartListAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements Serializable {
    private Context shoppingCartContext = ShoppingCartActivity.this;
    private MIMController controller = MIMController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Button btnMakeOrder = (Button) findViewById(R.id.btnOrder);

        Boolean cartHasItem = getIntent().getExtras().getBoolean("cartHasItem");

        if (cartHasItem) {
            @SuppressWarnings("unchecked")
            //ArrayList<ShoppingCartModel> shoppingCart = (ArrayList<ShoppingCartModel>)
                    //       getIntent().getSerializableExtra("shoppingCartArrayList");
                    ArrayList<ShoppingCartModel> shoppingCart = MIMController.valuePasser();

            ShoppingCartListAdapter adapter = new ShoppingCartListAdapter(this, shoppingCart);
            ListView listView = (ListView) findViewById(R.id.shopping_cart_list);
            listView.setAdapter(adapter);
        }

        btnMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MIMController.navigateTo(shoppingCartContext, ShippingDetailsActivity.class);
            }
        });
    }
}
