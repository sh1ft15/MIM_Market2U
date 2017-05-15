package com.example.yeong.market2u.MIM_Model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_SearchProduct.ProductDetailsActivity;
import com.example.yeong.market2u.MIM_SearchProduct.ProductList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Map;

public final class ProductModel {
    private static final String TAG = "Product Model";
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
    private Object[] productDetails = new Object[6];
    private ArrayList<ProductModel> product_lists = new ArrayList<ProductModel>();

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

    // Added By Din (Just for my convenient)
    public ProductModel(String productId, String productName, double productPrice, int productRemainingQuantity ) {
        this.productID = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRemainingQuantity = productRemainingQuantity;
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

    public void retrieveProduct(final String productID, final Context context) {
        Query query = mDatabase.orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    if (postSnapshot.getKey().equals(productID)) {
                        ProductModel productFromDatabase = postSnapshot.getValue(ProductModel.class);

                        setProductID(productID);
                        setProductName(productFromDatabase.productName);
                        setProductDescription(productFromDatabase.productDescription);
                        setProductRemainingQuantity(productFromDatabase.productRemainingQuantity);
                        setProductPrice(productFromDatabase.productPrice);
                        setProductImageUrl(productFromDatabase.productImageUrl);

                        productDetails[0] = getProductID();
                        productDetails[1] = getProductName();
                        productDetails[2] = getProductDescription();
                        productDetails[3] = getProductPrice();
                        productDetails[4] = getProductRemainingQuantity();
                        productDetails[5] = getProductImageUrl();
                    }
                }

                MIMController.navigateTo(context, ProductDetailsActivity.class, "productDetails", productDetails);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    // Added by Din
    public void all(final Context context) {

        Query query = mDatabase.orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    ProductModel product = postSnapshot.getValue(ProductModel.class);

                    product_lists.add(new ProductModel(
                                    postSnapshot.getKey(),
                                    product.getProductName(),
                                    product.getProductPrice(),
                                    product.getProductRemainingQuantity())
                    );

                }

                MIMController.set_products(product_lists);
                MIMController.navigateTo(context, ProductList.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });


    }


}
