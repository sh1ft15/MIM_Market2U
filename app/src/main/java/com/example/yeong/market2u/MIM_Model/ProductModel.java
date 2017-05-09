package com.example.yeong.market2u.MIM_Model;

/**
 * Created by yeong on 26/4/2017.
 */

public class ProductModel {

    private String productID;
    private String productName;
    private String productDescription;
    private int productRemainingQuantity;
    private double productPrice;
    private String productImageUrl;

    public ProductModel(){

    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductRemainingQuantity(int productRemainingQuantity) {
        this.productRemainingQuantity = productRemainingQuantity;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductRemainingQuantity() {
        return productRemainingQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }
}
