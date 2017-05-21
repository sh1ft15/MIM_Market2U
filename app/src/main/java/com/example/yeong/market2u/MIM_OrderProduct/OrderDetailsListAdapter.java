package com.example.yeong.market2u.MIM_OrderProduct;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Model.OrderedItemModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;


/**
 * Created by yeong on 22/5/2017.
 */

public class OrderDetailsListAdapter extends ArrayAdapter<OrderedItemModel> {
    public OrderDetailsListAdapter(Activity context, ArrayList<OrderedItemModel> orderedItem) {
        super(context, 0, orderedItem);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_order_details, parent, false);
        }

        final OrderedItemModel orderedItem = getItem(position);

        TextView txtOrderID = (TextView) listItemView.findViewById(R.id.order_ID);
        TextView txtProductName = (TextView) listItemView.findViewById(R.id.order_product_name);
        TextView txtProductPrice = (TextView) listItemView.findViewById(R.id.order_price);
        TextView txtOrderedQuantity = (TextView) listItemView.findViewById(R.id.order_quantity);
        TextView txtOrderStatus = (TextView) listItemView.findViewById(R.id.order_status);

        txtOrderID.setText(orderedItem.getOrderID());
        txtProductName.setText(orderedItem.getProductName());
        txtProductPrice.setText(Double.toString(orderedItem.getProductPrice()));
        txtOrderedQuantity.setText(Integer.toString(orderedItem.getProductOrderedQuantity()));
        txtOrderStatus.setText(orderedItem.getOrderedItemStatus());

        return listItemView;
    }
}
