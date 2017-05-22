package com.example.yeong.market2u.MIM_Model;

import android.content.Context;
import android.util.Log;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_ManagePayment.PaymentConfirmationActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private static final String TAG = "OrderModel";
    public static volatile OrderModel instance;
    private DatabaseReference mDatabaseOrder = FirebaseDatabase.getInstance().getReference("Order");
    private DatabaseReference mDatabaseOrderedItem = FirebaseDatabase.getInstance().getReference("Ordered Item");

    private String orderID;
    private List<String> orderedItemID = new ArrayList<>();
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryPhoneNum;
    private String billingAddress;
    private String billingName;
    private String billingPhoneNum;
    private String userID;
    private OrderModel orderFromDatabase;
    private OrderModel order;
    private ProductModel product = new ProductModel();

    private OrderModel() {

    }

    private OrderModel(String deliveryName, String deliveryAddress,
                       String deliveryPhoneNum, List<String> orderedItemID, String userID) {
        this.deliveryName = deliveryName;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPhoneNum = deliveryPhoneNum;
        this.orderedItemID = orderedItemID;
        this.userID = userID;
    }

    public static OrderModel getInstance() {
        if (instance == null) {
            synchronized (OrderModel.class) {
                if (instance == null) {
                    instance = new OrderModel();
                }
            }
        }
        return instance;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPhoneNum() {
        return deliveryPhoneNum;
    }

    public void setDeliveryPhoneNum(String deliveryPhoneNum) {
        this.deliveryPhoneNum = deliveryPhoneNum;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingPhoneNum() {
        return billingPhoneNum;
    }

    public void setBillingPhoneNum(String billingPhoneNum) {
        this.billingPhoneNum = billingPhoneNum;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<String> getOrderedItemID() {
        return orderedItemID;
    }

    public void setOrderedItemID(List<String> orderedItemID) {
        this.orderedItemID = orderedItemID;
    }

    public void makeOrder(ArrayList<ShoppingCartModel> cart, String deliveryName,
                          String deliveryAddress, String deliveryPhoneNum, String userID, Context context) {
        setOrderID(mDatabaseOrder.push().getKey());

        for (int x = 0; x < cart.size(); x++) {
            String orderItemID = mDatabaseOrderedItem.push().getKey();
            orderedItemID.add(orderItemID);

            OrderedItemModel orderedItem = new OrderedItemModel(cart.get(x).getProductID(),
                    getOrderID(), cart.get(x).getProductName(), cart.get(x).getProductPrice(),
                    cart.get(x).getProductOrderedQuantity(), cart.get(x).getProductImageUrl(),
                    "Pending", userID);
            // this line wont work
            product.updateStock(cart.get(x).getProductID(), cart.get(x).getProductOrderedQuantity());
            mDatabaseOrderedItem.child(orderItemID).setValue(orderedItem);
        }

        OrderModel order = new OrderModel(deliveryName, deliveryAddress, deliveryPhoneNum,
                orderedItemID, userID);

        mDatabaseOrder.child(getOrderID()).setValue(order);



        showOrder(getOrderID(), context);
    }
    /*
    public void makeOrder(ArrayList<ShoppingCartModel> cart, String deliveryName, String billingName,
                          String deliveryAddress, String billingAddress, String deliveryPhoneNum,
                          String billingPhoneNum, String userID){
        setOrderID(mDatabaseOrder.push().getKey());

        for (int x = 0; x < cart.size(); x++){
            String orderItemID = mDatabaseOrderedItem.push().getKey();
            orderedItemID.add(orderItemID);

            OrderedItemModel orderedItem = new OrderedItemModel(cart.get(x).getProductID(),
                    getOrderID(), cart.get(x).getProductName(), cart.get(x).getProductPrice(),
                    cart.get(x).getProductOrderedQuantity(), cart.get(x).getProductImageUrl(),
                    "Pending");

            mDatabaseOrderedItem.child(orderItemID).setValue(orderedItem);
        }

        OrderModel order = new OrderModel(ServerValue.TIMESTAMP.toString(), deliveryName,
                billingName, deliveryAddress, billingAddress, deliveryPhoneNum, billingPhoneNum,
                orderedItemID, userID);

        mDatabaseOrder.child(getOrderID()).setValue(order);
    }
    */

    public void showOrder(final String orderID, final Context context) {
        Query getOrderQuery = mDatabaseOrder.orderByKey().equalTo(orderID);

        getOrderQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    orderFromDatabase = postSnapshot.getValue(OrderModel.class);

                    if (postSnapshot.getKey().equals(orderID)) {
                        order = orderFromDatabase;
                    }
                }

                MIMController.valuePasserOrder(order);

                MIMController.navigateTo(context, PaymentConfirmationActivity.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }
}
