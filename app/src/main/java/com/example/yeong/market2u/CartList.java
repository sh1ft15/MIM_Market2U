package com.example.yeong.market2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CartList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        setTitle("Item in Cart");

        // remove focus from search input
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        String[] testArray = {"Item 1", "Item 2", "Item 3", "Item 4"};

        ListView list_view = (ListView) findViewById(R.id.product_list_view);
        EditText search_input = (EditText) findViewById(R.id.search_cart_input);
        Button search_btn = (Button) findViewById(R.id.search_cart_btn);
        Button next_btn = (Button) findViewById(R.id.cart_next_btn);
        Button back_btn = (Button) findViewById(R.id.cart_back_btn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, testArray);
        list_view.setAdapter(adapter);

        // BACK
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartList.this, ProductList.class);
                startActivity(i);
            }
        });

        // NEXT
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartList.this, Checkout.class);
                startActivity(i);
            }
        });

    }
}
