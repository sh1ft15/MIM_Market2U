package com.example.yeong.market2u.MIM_ManageUser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_ManageProduct.ManageProductListAdapter;
import com.example.yeong.market2u.R;

import java.net.ConnectException;

public class ManageSellerActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();
    private Context manageSellerContext = ManageSellerActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_seller);
        setTitle("Manage Seller");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.list_manage_seller);
        Button btnApproveSeller = (Button) findViewById(R.id.button_approve_seller);

        ManageSellerListAdapter userList = new ManageSellerListAdapter
                (this, MIMController.getUserList());
        listView.setAdapter(userList);

        btnApproveSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.getListOfSellerToApprove(manageSellerContext);
            }
        });

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
