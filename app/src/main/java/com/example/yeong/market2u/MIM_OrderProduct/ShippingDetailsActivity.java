package com.example.yeong.market2u.MIM_OrderProduct;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

import org.w3c.dom.Text;

public class ShippingDetailsActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();
    private Context shippingDetailsContext = ShippingDetailsActivity.this;

    private TextView txtRecipientName;
    private TextView txtShipAddress;
    private TextView txtShipPostcode;
    private TextView txtShipCity;
    private TextView txtShipPhoneNum;
    private Spinner dlShipProvince;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_details);
        setTitle("Shipping Details");

        addItemInSpinnerProvince();

        txtRecipientName = (TextView) findViewById(R.id.txtRecipientName);
        txtShipAddress = (TextView) findViewById(R.id.txtRecipientAddress);
        txtShipPostcode = (TextView) findViewById(R.id.txtRecipientPostcode);
        txtShipCity = (TextView) findViewById(R.id.txtRecipientCity);
        dlShipProvince = (Spinner) findViewById(R.id.dlRecipientProvince);
        txtShipPhoneNum = (TextView) findViewById(R.id.txtRecipientMobileNum);
        btnContinue = (Button) findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipientName = txtRecipientName.getText().toString();
                String shipPhoneNum = txtShipPhoneNum.getText().toString();

                String shipAddress = txtShipAddress.getText().toString() + ", "
                        + txtShipPostcode.getText().toString() + " " + txtShipCity.getText().toString()
                        + ", " + dlShipProvince.getSelectedItem().toString();

                controller.makeOrderProcess(recipientName, shipAddress, shipPhoneNum,
                        shippingDetailsContext);
            }
        });
    }

    // To initiate the spinner of province
    private void addItemInSpinnerProvince() {
        Spinner spinnerProvince = (Spinner) findViewById(R.id.dlRecipientProvince);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.spinnerProvince, android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapter);
    }
}
