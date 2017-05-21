package com.example.yeong.market2u.MIM_ManageUser;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.UserModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

/**
 * Created by yeong on 21/5/2017.
 */

public class ApproveSellerListAdapter extends ArrayAdapter<UserModel> {
    private MIMController controller = MIMController.getInstance();

    public ApproveSellerListAdapter(Activity context, ArrayList<UserModel> user) {
        super(context, 0, user);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_seller_to_approve, parent, false);
        }

        final UserModel user = getItem(position);

        TextView txtEmailAddress = (TextView) listItemView.findViewById(R.id.full_name);
        TextView txtFullName = (TextView) listItemView.findViewById(R.id.email_address);
        TextView txtSellerStatus = (TextView) listItemView.findViewById(R.id.seller_status);
        Button btnApprove = (Button) listItemView.findViewById(R.id.button_approve);

        txtEmailAddress.setText(user.getEmailAddress());
        txtFullName.setText(user.getFirstName() + " " + user.getLastName());
        txtSellerStatus.setText("Seller Status: " + user.getSellerStatus());

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.approveSellerProcess(user.getUserKey(), getContext());
            }
        });

        return listItemView;
    }
}
