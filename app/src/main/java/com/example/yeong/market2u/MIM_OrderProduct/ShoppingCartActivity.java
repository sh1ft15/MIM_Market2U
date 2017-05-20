package com.example.yeong.market2u.MIM_OrderProduct;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.MIM_SearchProduct.ProductList;
import com.example.yeong.market2u.MainActivity;
import com.example.yeong.market2u.R;
import com.example.yeong.market2u.Testing;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements Serializable {
    private Context shoppingCartContext = ShoppingCartActivity.this;
    private MIMController controller = MIMController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setTitle("Shopping Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

                String current_user_id = MIMController.getInstance().getCurrentUser(shoppingCartContext);

                if(current_user_id == null || current_user_id == "" || current_user_id == "guest"){
                    controller.navigateTo(shoppingCartContext, MainActivity.class, "status", "Please Login to Make an Order");
                }else{
                    controller.navigateTo(shoppingCartContext, ShippingDetailsActivity.class);
                }

            }
        });

        if(getIntent().hasExtra("status")){
            Toast.makeText(getApplicationContext(), getIntent().getStringExtra("status"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                controller.navigateTo(this, ProductList.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        controller.navigateTo(this, ProductList.class);
    }
}
