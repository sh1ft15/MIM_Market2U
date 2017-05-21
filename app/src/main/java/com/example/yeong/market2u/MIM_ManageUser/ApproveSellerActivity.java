package com.example.yeong.market2u.MIM_ManageUser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

public class ApproveSellerActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();
    private Context approveSellerContext = ApproveSellerActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_seller);
        setTitle("Approve Seller");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (MIMController.getUserList().isEmpty()) {
            TextView message = (TextView) findViewById(R.id.approve_message);
            message.setText("There is no request that have to be approved.");
            message.setVisibility(View.VISIBLE);
        } else {
            ApproveSellerListAdapter sellerToApprove
                    = new ApproveSellerListAdapter(this, MIMController.getUserList());

            ListView listView = (ListView) findViewById(R.id.list_seller_to_approve);
            listView.setAdapter(sellerToApprove);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                controller.getAllUserProcess(approveSellerContext);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
