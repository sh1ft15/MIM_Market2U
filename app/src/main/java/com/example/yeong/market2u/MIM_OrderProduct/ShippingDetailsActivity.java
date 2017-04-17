package com.example.yeong.market2u.MIM_OrderProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.yeong.market2u.R;

public class ShippingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_details);
        addItemInSpinnerProvince();
        // Initial Bill Address Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.billAddressContainer,new BillAddressFragment()).commit();
    }

    // To initiate the spinner of province
    private void addItemInSpinnerProvince()
    {
        Spinner spinnerProvince = (Spinner) findViewById(R.id.dlRecipientProvince);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.spinnerProvince,android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapter);
    }
}
