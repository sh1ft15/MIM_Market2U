package com.example.yeong.market2u.MIM_ManagePayment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.OrderModel;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.R;
import com.example.yeong.market2u.MIM_OrderProduct.ShoppingCartListAdapter;

import java.util.ArrayList;

public class PaymentConfirmationActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();
    private Context paymentConfirmationContext = PaymentConfirmationActivity.this;
    private ArrayList<ShoppingCartModel> orderedItem;
    private Double totalPrice = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        TextView txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        TextView txtRecipientName = (TextView) findViewById(R.id.recipient_name);
        TextView txtShippingAddress = (TextView) findViewById(R.id.shipping_address);
        TextView txtShippingPhoneNum = (TextView) findViewById(R.id.phone_number);
        Button btnMakePayment = (Button) findViewById(R.id.btnMakePayment);

        orderedItem = MIMController.valuePasser();
        OrderModel order = MIMController.valuePasserOrder();

        for (int x = 0; x < orderedItem.size(); x++) {
            totalPrice += orderedItem.get(x).getProductPrice();
        }

        txtRecipientName.setText(order.getDeliveryName());
        txtShippingAddress.setText(order.getDeliveryAddress());
        txtShippingPhoneNum.setText(order.getDeliveryPhoneNum());
        txtTotalPrice.setText(Double.toString(totalPrice));

        ShoppingCartListAdapter adapter = new ShoppingCartListAdapter(this, orderedItem);
        ListView listView = (ListView) findViewById(R.id.payment_list_view);
        listView.setAdapter(adapter);


        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
