package com.example.yeong.market2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button product_list_btn = (Button) findViewById(R.id.product_list_btn);
        Button cart_list_btn = (Button) findViewById(R.id.cart_list_btn);

        product_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProductList.class);
                startActivity(i);
            }
        });

        cart_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CartList.class);
                startActivity(i);
            }
        });
    }
}
