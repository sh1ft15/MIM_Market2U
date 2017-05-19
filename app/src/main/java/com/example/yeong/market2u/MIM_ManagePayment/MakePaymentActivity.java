package com.example.yeong.market2u.MIM_ManagePayment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_SearchProduct.ProductMenuActivity;
import com.example.yeong.market2u.R;

public class MakePaymentActivity extends AppCompatActivity {
    private Context makePaymentContext = MakePaymentActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payment);
        setTitle("Payment");

        Button btnDone = (Button) findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MIMController.navigateTo(makePaymentContext, ProductMenuActivity.class);
            }
        });
    }
}
