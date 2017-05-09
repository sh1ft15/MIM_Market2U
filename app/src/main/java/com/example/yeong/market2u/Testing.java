package com.example.yeong.market2u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Authentication.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        TextView id = (TextView)findViewById(R.id.textToDisplay);

        Intent intent = getIntent();
        String key = intent.getStringExtra("userKey");

        id.setText(key);

        Button btnSignOut = (Button)findViewById(R.id.btnSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Intent backToSignInIntent = new Intent(Testing.this,SignInActivity.class);
                startActivity(backToSignInIntent);
            }
        });
    }
}
