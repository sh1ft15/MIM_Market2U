package com.example.yeong.market2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yeong.market2u.MIM_Authentication.SignInActivity;
import com.example.yeong.market2u.MIM_OrderProduct.CartList;
import com.example.yeong.market2u.MIM_SearchProduct.MainFrameActivity;
import com.example.yeong.market2u.MIM_SearchProduct.ProductList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button product_list_btn = (Button) findViewById(R.id.product_list_btn);
        Button cart_list_btn = (Button) findViewById(R.id.cart_list_btn);
        Button btnProductMenu = (Button) findViewById(R.id.btnProductMenu);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);

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

        btnProductMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainFrameActivity.class);
                startActivity(i);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }
}
