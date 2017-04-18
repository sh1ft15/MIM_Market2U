package com.example.yeong.market2u.MIM_Model;

/**
 * Created by Banana on 4/18/2017.
 */

public class ProductModel {

    private String product_name;
    private double product_price;

    public ProductModel(String name, double price){
        this.product_name = name;
        this.product_price = price;
    }

    public String getProductName() {
        return this.product_name;
    }

    public double getProductPrice() {
        return this.product_price;
    }

}
