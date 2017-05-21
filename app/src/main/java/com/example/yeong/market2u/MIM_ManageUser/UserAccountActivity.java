package com.example.yeong.market2u.MIM_ManageUser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

public class UserAccountActivity extends AppCompatActivity {
    private TextView txtUsername;
    private TextView txtFullName;
    private TextView txtSellerStatus;
    private Button btnActivateSeller;
    private MIMController controller = MIMController.getInstance();
    private Context userAccountContext = UserAccountActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        setTitle("My account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUsername = (TextView) findViewById(R.id.user_username);
        txtFullName = (TextView) findViewById(R.id.user_full_name);
        txtSellerStatus = (TextView) findViewById(R.id.user_seller_status);
        btnActivateSeller = (Button) findViewById(R.id.user_become_seller);

        Object[] userDetails = (Object[]) getIntent().getSerializableExtra("userDetails");
        txtUsername.setText(userDetails[1].toString());
        txtFullName.setText(userDetails[2].toString() + " " + userDetails[3].toString());
        txtSellerStatus.setText(userDetails[4].toString());

        if (userDetails[4].toString().equals("Inactive")) {
            btnActivateSeller.setVisibility(View.VISIBLE);
        }

        btnActivateSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.requestToBecomeSellerProcess(userAccountContext);
                finish();
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
