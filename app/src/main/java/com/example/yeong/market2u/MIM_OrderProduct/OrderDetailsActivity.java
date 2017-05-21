package com.example.yeong.market2u.MIM_OrderProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        setTitle("My Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.list_order_details);

        if (MIMController.getOrderedItemList().isEmpty()) {
            TextView txtMessage = (TextView) findViewById(R.id.order_message);
            txtMessage.setText("You did not make any order yet.");
            txtMessage.setVisibility(View.VISIBLE);

        } else {
            OrderDetailsListAdapter orderedItemList
                    = new OrderDetailsListAdapter(this, MIMController.getOrderedItemList());
            listView.setAdapter(orderedItemList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
