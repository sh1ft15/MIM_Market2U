package com.example.yeong.market2u.MIM_ManageUser;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Model.UserModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

/**
 * Created by yeong on 21/5/2017.
 */

public class ManageSellerListAdapter extends ArrayAdapter<UserModel> {
    public ManageSellerListAdapter(Activity context, ArrayList<UserModel> user) {
        super(context, 0, user);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_all_user, parent, false);
        }

        final UserModel user = getItem(position);

        TextView txtEmailAddress = (TextView) listItemView.findViewById(R.id.allUser_email);
        TextView txtFullName = (TextView) listItemView.findViewById(R.id.allUser_full_name);
        TextView txtSellerStatus = (TextView) listItemView.findViewById(R.id.allUser_seller_status);

        txtEmailAddress.setText(user.getEmailAddress());
        txtFullName.setText(user.getFirstName() + " " + user.getLastName());
        txtSellerStatus.setText("Seller Status: " + user.getSellerStatus());

        return listItemView;
    }
}
