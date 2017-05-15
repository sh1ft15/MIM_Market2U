package com.example.yeong.market2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Authentication.SignInActivity;
import com.example.yeong.market2u.MIM_ManageProduct.AddProductActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        TextView id = (TextView)findViewById(R.id.textToDisplay);
        TextView email = (TextView)findViewById(R.id.textEmail);

        // id.setText(getIntent().getStringExtra("userKey"));

         final Object[] uDetails = (Object[]) getIntent().getSerializableExtra("userDetails");


        id.setText(" ID : " + uDetails[0].toString() );
        email.setText(" EMAIL : " + uDetails[1].toString());

        Button btnSignOut = (Button)findViewById(R.id.btnSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent backToSignInIntent = new Intent(Testing.this,SignInActivity.class);
                startActivity(backToSignInIntent);
            }
        });

        Button btnAddProduct = (Button) findViewById(R.id.btnGoToAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Testing.this, AddProductActivity.class));
            }
        });
    }
}
