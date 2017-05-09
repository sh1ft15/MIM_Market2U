package com.example.yeong.market2u.MIM_Model;

/**
 * Created by yeong on 26/4/2017.
 */

public class ShoppingCartModel {

    private String userID;
    private String productID;
    private String productName;
    private double productPrice;
    private int productOrderedQuantity;
    private String productImageUrl;

    public ShoppingCartModel(){

    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductOrderedQuantity(int productOrderedQuantity) {
        this.productOrderedQuantity = productOrderedQuantity;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductOrderedQuantity() {
        return productOrderedQuantity;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public String getUserID() {
        return userID;
    }
}
