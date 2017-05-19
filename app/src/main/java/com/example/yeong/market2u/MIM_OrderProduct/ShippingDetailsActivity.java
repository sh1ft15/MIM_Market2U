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
    /*
    private TextView txtBillName;
    private TextView txtBillAddress;
    private TextView txtBillPostcode;
    private TextView txtBillCity;
    private TextView txtBillPhoneNum;
    private Spinner dlBillProvince;
    private CheckBox chkBillToAnotherAddress;
    */
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_details);
        setTitle("Shipping Details");

        addItemInSpinnerProvince();
        // Initial Bill Address Fragment
        //getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.billAddressContainer, new BillAddressFragment()).commit();

        txtRecipientName = (TextView) findViewById(R.id.txtRecipientName);
        txtShipAddress = (TextView) findViewById(R.id.txtRecipientAddress);
        txtShipPostcode = (TextView) findViewById(R.id.txtRecipientPostcode);
        txtShipCity = (TextView) findViewById(R.id.txtRecipientCity);
        dlShipProvince = (Spinner) findViewById(R.id.dlRecipientProvince);
        txtShipPhoneNum = (TextView) findViewById(R.id.txtRecipientMobileNum);
        /*
        txtBillName = (TextView) findViewById(R.id.txtBillName);
        txtBillAddress = (TextView) findViewById(R.id.txtBillAddress);
        txtBillCity = (TextView) findViewById(R.id.txtBillCity);
        txtBillPostcode = (TextView) findViewById(R.id.txtBillPostcode);
        dlBillProvince = (Spinner) findViewById(R.id.dlBillProvince);
        txtBillPhoneNum = (TextView) findViewById(R.id.txtBillMobileNum);
        */
        btnContinue = (Button) findViewById(R.id.btnContinue);
        //chkBillToAnotherAddress = (CheckBox) findViewById(R.id.chkBillToAnotherAddress);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipientName = txtRecipientName.getText().toString();
                //String billName = txtBillName.getText().toString();
                String shipPhoneNum = txtShipPhoneNum.getText().toString();
                //String billPhoneNum = txtBillPhoneNum.getText().toString();

                String shipAddress = txtShipAddress.getText().toString() + ", "
                        + txtShipPostcode.getText().toString() + " " + txtShipCity.getText().toString()
                        + ", " + dlShipProvince.getSelectedItem().toString();
                /*
                String billAddress;

                if(chkBillToAnotherAddress.isChecked())
                {
                    billAddress = txtBillAddress.getText().toString() + ", "
                            + txtBillPostcode.getText().toString()+", "
                            +txtBillCity.getText().toString() + ", " + dlBillProvince.getSelectedItem().toString();
                }
                else
                {
                    billAddress = shipAddress;
                }
                */
                //controller.makeOrderProcess(recipientName, billName, shipAddress, billAddress,
                //        shipPhoneNum, billPhoneNum, shippingDetailsContext);

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
