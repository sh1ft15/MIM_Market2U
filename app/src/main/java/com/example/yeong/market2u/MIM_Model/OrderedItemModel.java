package com.example.yeong.market2u.MIM_Model;

/**
 * Created by yeong on 26/4/2017.
 */

public class OrderedItemModel {

    private String productID;
    private String orderID;
    private String productName;
    private double productPrice;
    private int productOrderedQuantity;
    private String productImageUrl;
    private String orderedItemStatus;
    private String userID;

    public OrderedItemModel() {

    }

    public OrderedItemModel(String productID, String orderID, String productName,
                            double productPrice, int productOrderedQuantity,
                            String productImageUrl, String orderedItemStatus) {
        this.productID = productID;
        this.orderID = orderID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productOrderedQuantity = productOrderedQuantity;
        this.productImageUrl = productImageUrl;
        this.orderedItemStatus = orderedItemStatus;
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
}
