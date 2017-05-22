package com.example.yeong.market2u.MIM_Model;

import android.content.Context;
import android.util.Log;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_OrderProduct.ShoppingCartActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public final class ShoppingCartModel {
    private static final String TAG = "Shopping Cart Model";
    private static volatile ShoppingCartModel instance;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Shopping Cart");

    private String shoppingCartID;
    private String userID;
    private String productID;
    private String productName;
    private double productPrice;
    private int productOrderedQuantity;
    private String productImageUrl;
    private ShoppingCartModel cartFromDatabase;
    private Boolean cartHasItem;
    private ArrayList<ShoppingCartModel> cart = new ArrayList<ShoppingCartModel>();

    private ShoppingCartModel() {

    }

    private ShoppingCartModel( String userID, String productID, String productName,
                              double productPrice, int productOrderedQuantity,
                              String productImageUrl) {
        this.userID = userID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOrderedQuantity = productOrderedQuantity;
        this.productImageUrl = productImageUrl;
    }

    private ShoppingCartModel(String shoppingCartID, String productName, double productPrice, int productOrderedQuantity) {
        this.shoppingCartID = shoppingCartID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOrderedQuantity = productOrderedQuantity;

    }

    public static ShoppingCartModel getInstance() {
        if (instance == null) {
            synchronized (ShoppingCartModel.class) {
                if (instance == null) {
                    instance = new ShoppingCartModel();
                }
            }
        }
        return instance;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(String shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public String getUserID() {
        return userID;
    }

    public void addToCart(Object[] productDetails, int productOrderedQuantity, String userID) {
        setShoppingCartID(mDatabase.push().getKey());

        double productPrice = productOrderedQuantity * Double.parseDouble(productDetails[3].toString());

        String image_url = null;

        if(productDetails[5] != null){
            image_url = productDetails[5].toString();
        }

        ShoppingCartModel shoppingCart = new ShoppingCartModel(userID, productDetails[0].toString(),
                productDetails[1].toString(), productPrice,
                productOrderedQuantity, image_url);

        mDatabase.child(getShoppingCartID()).setValue(shoppingCart);

    }

    public void showShoppingCart(final String userID, final Context context) {
        Query query = mDatabase.orderByChild("userID").equalTo(userID);

        cart.clear();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cartFromDatabase = dataSnapshot.getValue(ShoppingCartModel.class);

                if (cartFromDatabase == null) {
                    cartHasItem = false;
                } else {
                    cartHasItem = true;

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        cartFromDatabase = postSnapshot.getValue(ShoppingCartModel.class);

//                        if (cartFromDatabase.getUserID().equals(userID)) {
//                        }
                        cart.add(new ShoppingCartModel(
                                postSnapshot.getKey(),
                                // userID,
                                //cartFromDatabase.getProductID(),
                                cartFromDatabase.getProductName(),
                                cartFromDatabase.getProductPrice(),
                                cartFromDatabase.getProductOrderedQuantity()));
                                //cartFromDatabase.getProductImageUrl()));
                    }
                }
                MIMController.valuePasser(cart);

                MIMController.navigateTo(context, ShoppingCartActivity.class,
                        "cartHasItem", cartHasItem);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    public void deleteShoppingCartItem(final Context context, String shoppingCartID){

        mDatabase.child(shoppingCartID).removeValue();

        // MIMController.navigateTo(context, ShoppingCartActivity.class, "status", "Item in Cart Removed!");
        MIMController.getInstance().showShoppingCartProcess(context);
    }
}
