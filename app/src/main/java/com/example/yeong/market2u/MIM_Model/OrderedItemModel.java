package com.example.yeong.market2u.MIM_Model;

import android.content.Context;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_OrderProduct.OrderDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by yeong on 26/4/2017.
 */

public class OrderedItemModel {

    public static volatile OrderedItemModel instance;
    private String orderedItemID;
    private String productID;
    private String orderID;
    private String productName;
    private double productPrice;
    private int productOrderedQuantity;
    private String productImageUrl;
    private String orderedItemStatus;
    private String userID;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Ordered Item");
    private ArrayList<OrderedItemModel> orderedItemList = new ArrayList<OrderedItemModel>();

    public OrderedItemModel() {

    }

    public OrderedItemModel(String productID, String orderID, String productName,
                            double productPrice, int productOrderedQuantity,
                            String productImageUrl, String orderedItemStatus, String userID) {
        this.productID = productID;
        this.orderID = orderID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOrderedQuantity = productOrderedQuantity;
        this.productImageUrl = productImageUrl;
        this.orderedItemStatus = orderedItemStatus;
        this.userID = userID;
    }

    public static OrderedItemModel getInstance() {
        if (instance == null) {
            synchronized (OrderModel.class) {
                if (instance == null) {
                    instance = new OrderedItemModel();
                }
            }
        }
        return instance;
    }

    public String getOrderedItemID() {
        return orderedItemID;
    }

    public void setOrderedItemID(String orderedItemID) {
        this.orderedItemID = orderedItemID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductOrderedQuantity() {
        return productOrderedQuantity;
    }

    public void setProductOrderedQuantity(int productOrderedQuantity) {
        this.productOrderedQuantity = productOrderedQuantity;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getOrderedItemStatus() {
        return orderedItemStatus;
    }

    public void setOrderedItemStatus(String orderedItemStatus) {
        this.orderedItemStatus = orderedItemStatus;
    }

    public String getProductID() {
        return productID;
    }

    public String getUserID() {
        return userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void showAllProductOrdered(final String userID, final Context context) {
        Query query = mDatabase.orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    OrderedItemModel orderedItem = postSnapshot.getValue(OrderedItemModel.class);

                    if (orderedItem.getUserID().equals(userID)) {
                        orderedItemList.add(new OrderedItemModel(orderedItem.getProductID(),
                                orderedItem.getOrderID(), orderedItem.getProductName(),
                                orderedItem.getProductPrice(), orderedItem.getProductOrderedQuantity(),
                                orderedItem.getProductImageUrl(), orderedItem.getOrderedItemStatus(),
                                userID));
                    }
                }
                MIMController.setOrderedItemList(orderedItemList);
                MIMController.navigateTo(context, OrderDetailsActivity.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
