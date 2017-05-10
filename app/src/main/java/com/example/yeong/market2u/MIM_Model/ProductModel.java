package com.example.yeong.market2u.MIM_Model;

import android.content.Context;
import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by yeong on 26/4/2017.
 */

public class ProductModel {
    private static volatile ProductModel instance;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Product");
    private StorageReference mStorage = FirebaseStorage.getInstance().getReference("Product Image");

    private String productID;
    private String productName;
    private String productDescription;
    private int productRemainingQuantity;
    private double productPrice;
    private String productImageUrl;
    private String userKey;

    public ProductModel() {

    }

    private ProductModel(String productName, String productDescription,
                         int productRemainingQuantity, double productPrice,
                         String productImageUrl, String userKey) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productRemainingQuantity = productRemainingQuantity;
        this.productImageUrl = productImageUrl;
        this.userKey = userKey;
    }

    public static ProductModel getInstance() {
        if (instance == null) {
            synchronized (ProductModel.class) {
                if (instance == null) {
                    instance = new ProductModel();
                }
            }
        }
        return instance;
    }

    public String getProductID() {
        return productID;
    }

    private void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductRemainingQuantity() {
        return productRemainingQuantity;
    }

    public void setProductRemainingQuantity(int productRemainingQuantity) {
        this.productRemainingQuantity = productRemainingQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void addNewProduct(final String productName, final String productDescription,
                              final int productRemainingQuantity, final double productPrice,
                              Uri selectedProductImageUri, final String userKey) {
        StorageReference photoRef = mStorage.child(selectedProductImageUri.getLastPathSegment());

        photoRef.putFile(selectedProductImageUri).addOnSuccessListener
                (new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests")
                        Uri url = taskSnapshot.getDownloadUrl();

                        setProductImageUrl(url.toString());

                        setProductID(mDatabase.push().getKey());

                        ProductModel product = new ProductModel(productName, productDescription,
                                productRemainingQuantity, productPrice, getProductImageUrl(), userKey);

                        mDatabase.child(getProductID()).setValue(product);
                    }
                });
    }
}
