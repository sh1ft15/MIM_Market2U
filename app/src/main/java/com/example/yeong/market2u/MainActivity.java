package com.example.yeong.market2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Authentication.SignInActivity;
import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_OrderProduct.CartList;
import com.example.yeong.market2u.MIM_SearchProduct.MainFrameActivity;
import com.example.yeong.market2u.MIM_SearchProduct.ProductDetailsActivity;
import com.example.yeong.market2u.MIM_SearchProduct.ProductList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button product_list_btn = (Button) findViewById(R.id.product_list_btn);
        // Button btnProductMenu = (Button) findViewById(R.id.btnProductMenu);
        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        TextView current_user = (TextView) findViewById(R.id.current_user_id);

        if( MIMController.getInstance().getCurrentUser(MainActivity.this) != null){
            // MIMController.navigateTo(MainActivity.this, Testing.class);
            current_user.setText("id = " + MIMController.getInstance().getCurrentUser(MainActivity.this));
        };


        if(getIntent().hasExtra("status")){
            Toast.makeText(getApplicationContext(), getIntent().getStringExtra("status"), Toast.LENGTH_SHORT).show();
        }
        product_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MIMController.getInstance().retrieveAllProductProcess(MainActivity.this);
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
