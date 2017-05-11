package com.example.yeong.market2u.MIM_Model;

import android.content.Context;

import com.example.yeong.market2u.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Banana on 4/18/2017.
 */

public class Product_Model {

    private String product_name;
    private double product_price;
    private int product_quantity;
    private Connection conn;
    private JSONObject product_json;

    public Product_Model(Context context){
        this.product_name = "N/A";
        this.product_price = 0.00;
        this.product_quantity = 0;
        this.conn = new Connection(context);
    }

    public Product_Model(String name, double price, int quantity){
        this.product_name = name;
        this.product_price = price;
        this.product_quantity = quantity;
    }


    public String getProductName() {
        return this.product_name;
    }

    public double getProductPrice() {
        return this.product_price;
    }

    public int getProductQuantity() {
        return this.product_quantity;
    }

    public ArrayList<Product_Model> all() {

        final ArrayList<Product_Model> products = new ArrayList<Product_Model>();

            conn.setListener(new Connection.Listener(){
                @Override
                public void onResult(JSONObject result) {
                    try {
                        JSONArray products_array = result.getJSONArray("product_data");
                        JSONObject obj = null;

                        for (int i = 0; i < products_array.length(); i++) {

                                obj = products_array.getJSONObject(i);
                                String name = obj.getString("name");
                                Double price = Double.parseDouble(obj.getString("price"));
                                int quantity = obj.getInt("quantity");
                                products.add(new Product_Model(name, price, quantity));


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            conn.execute("GET", "action=get_all_product");


        return products;

    }


}