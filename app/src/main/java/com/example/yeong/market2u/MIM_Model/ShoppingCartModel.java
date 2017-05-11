package com.example.yeong.market2u.MIM_Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by yeong on 26/4/2017.
 */

public class ShoppingCartModel {
    private static volatile ShoppingCartModel instance;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Shopping Cart");

    private String shoppingCartID;
    private String userID;
    private String productID;
    private String productName;
    private double productPrice;
    private int productOrderedQuantity;
    private String productImageUrl;

    private ShoppingCartModel() {

    }

    private ShoppingCartModel(String userID, String productID, String productName,
                              double productPrice, int productOrderedQuantity,
                              String productImageUrl) {
        this.userID = userID;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOrderedQuantity = productOrderedQuantity;
        this.productImageUrl = productImageUrl;
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

        ShoppingCartModel shoppingCart = new ShoppingCartModel(userID, productDetails[0].toString(),
                productDetails[1].toString(), Double.parseDouble(productDetails[3].toString()),
                productOrderedQuantity, productDetails[5].toString());

        mDatabase.child(getShoppingCartID()).setValue(shoppingCart);
    }
}
