package com.example.yeong.market2u.MIM_Model;

import java.util.ArrayList;

/**
 * Created by yeong on 26/4/2017.
 */

public class OrderModel {

    private String orderID;
    private ArrayList<String> orderItemID;
    private String orderTimestamp;
    private String deliveryName;
    private String deliveryAddress;
    private String deliveryPhoneNum;
    private String billingAddress;
    private String billingName;
    private String billingPhoneNum;
    private String userID;

    public OrderModel(){

    }

    public void setOrderTimestamp(String orderTimestamp) {
        this.orderTimestamp = orderTimestamp;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryPhoneNum(String deliveryPhoneNum) {
        this.deliveryPhoneNum = deliveryPhoneNum;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setBillingPhoneNum(String billingPhoneNum) {
        this.billingPhoneNum = billingPhoneNum;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<String> getOrderItemID() {
        return orderItemID;
    }

    public String getOrderTimestamp() {
        return orderTimestamp;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDeliveryPhoneNum() {
        return deliveryPhoneNum;
    }

    public String getBillingName() {
        return billingName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getBillingPhoneNum() {
        return billingPhoneNum;
    }

    public String getUserID() {
        return userID;
    }

    public String getOrderID() {
        return orderID;
    }
}
