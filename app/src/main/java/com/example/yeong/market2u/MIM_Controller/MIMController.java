package com.example.yeong.market2u.MIM_Controller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Model.OrderModel;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.MIM_Model.UserModel;

import java.util.ArrayList;

public final class MIMController {
    private static volatile MIMController instance;
    private static ArrayList<ShoppingCartModel> cart;
    private static ArrayList<ProductModel> products;
    private static OrderModel orderFromDB;
    private UserModel user = UserModel.getInstance();
    private ProductModel product = ProductModel.getInstance();
    private ShoppingCartModel shoppingCart = ShoppingCartModel.getInstance();
    private OrderModel order = OrderModel.getInstance();
    private ProgressDialog mProgressDialog;
    private Toast toast;
    private SharedPreferences prefs;
    private String current_user_id;


    private MIMController() {

    }

    public static MIMController getInstance() {
        if (instance == null) {
            synchronized (MIMController.class) {
                if (instance == null) {
                    instance = new MIMController();
                }
            }
        }
        return instance;
    }

    public static void navigateTo(Context origin, Class destination) {
        origin.startActivity(new Intent(origin, destination));
    }


    public static void navigateTo(Context origin, Class destination,
                                  String extraName, Object[] extraObjectArray) {
        origin.startActivity(new Intent(origin, destination)
                .putExtra(extraName, extraObjectArray));
    }

    public static void navigateTo(Context origin, Class destination,
                                  String extraName, String extraValue) {
        origin.startActivity(new Intent(origin, destination)
                .putExtra(extraName, extraValue));
    }

    public static void navigateTo(Context origin, Class destination,
                                  String extraName, Boolean cartHasItem) {

        origin.startActivity(new Intent(origin, destination)
                .putExtra(extraName, cartHasItem));
    }

    public void setCurrentUser(String id, Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("current_user", id).commit();
        this.current_user_id = id;
    }

    public String getCurrentUser(){
        current_user_id = prefs.getString("current_user","");
        return current_user_id;
    }

    public static ArrayList<ShoppingCartModel> valuePasser() {
        return cart;
    }

    public static void valuePasser(ArrayList<ShoppingCartModel> arrayList) {
        cart = arrayList;
    }

    public static ArrayList<ProductModel> get_products() {
        return products;
    }

    public static void set_products(ArrayList<ProductModel> arrayList) {
        products = arrayList;
    }

    public static OrderModel valuePasserOrder() {
        return orderFromDB;
    }

    public static void valuePasserOrder(OrderModel orderDB) {
        orderFromDB = orderDB;
    }

    public void signInProcess(EditText mEmailField, EditText mPasswordField, Context context) {
        validateForm(mEmailField, mPasswordField);

        showProgressDialog(context, true);
        user.signInValidationInAuthentication
                (mEmailField.getText().toString(), mPasswordField.getText().toString(), context);
        showProgressDialog(context, false);
        // toast.makeText(context, "Successfully logged in!", Toast.LENGTH_SHORT).show();
    }

    public void signUpProcess(EditText mEmailField, EditText mPasswordField,
                              EditText mFirstName, EditText mLastName, Context context) {
        validateForm(mEmailField, mPasswordField, mFirstName, mLastName);

        showProgressDialog(context, true);

        user.createUserAccountInAuthentication(mEmailField.getText().toString(),
                mPasswordField.getText().toString(), mFirstName.getText().toString(),
                mLastName.getText().toString(), context);

    }

    private boolean validateForm(EditText mEmailField, EditText mPasswordField) {
        boolean result = true;
        if (TextUtils.isEmpty(mEmailField.getText().toString())) {
            mEmailField.setError("Required");
            result = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        return result;
    }

    private boolean validateForm(EditText mEmailField, EditText mPasswordField,
                                 EditText mFirstName, EditText mLastName) {
        boolean result = true;
        if (TextUtils.isEmpty(mEmailField.getText().toString())) {
            mEmailField.setError("Required");
            result = false;
        } else {
            mEmailField.setError(null);
        }

        if (TextUtils.isEmpty(mPasswordField.getText().toString())) {
            mPasswordField.setError("Required");
            result = false;
        } else {
            mPasswordField.setError(null);
        }

        if (TextUtils.isEmpty(mFirstName.getText().toString())) {
            mFirstName.setError("Required");
            result = false;
        } else {
            mFirstName.setError(null);
        }

        if (TextUtils.isEmpty(mLastName.getText().toString())) {
            mLastName.setError("Required");
            result = false;
        } else {
            mLastName.setError(null);
        }

        return result;
    }

    private boolean validateForm(EditText mProductName, EditText mProductDescription,
                                 EditText mProductPrice, EditText mProductRemainingQuantity,
                                 ImageView mProductImage, Context context) {
        boolean result = true;
        if (TextUtils.isEmpty(mProductName.getText().toString())) {
            mProductName.setError("Required");
            result = false;
        } else {
            mProductName.setError(null);
        }

        if (TextUtils.isEmpty(mProductDescription.getText().toString())) {
            mProductDescription.setError("Required");
            result = false;
        } else {
            mProductDescription.setError(null);
        }

        if (TextUtils.isEmpty(mProductPrice.getText().toString())) {
            mProductPrice.setError("Required");
            result = false;
        } else {
            mProductPrice.setError(null);
        }

        if (TextUtils.isEmpty(mProductRemainingQuantity.getText().toString())) {
            mProductRemainingQuantity.setError("Required");
            result = false;
        } else {
            mProductRemainingQuantity.setError(null);
        }

        // It's not working
        // Why? XD
        if (mProductImage.getDrawable() == null) {
            new AlertDialog.Builder(context).setTitle("Attention")
                    .setMessage("Product image is required.")
                    .setNeutralButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

            result = false;
        }

        return result;
    }

    private void showProgressDialog(Context context, boolean status) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        if(status == true){
            mProgressDialog.show();
        }else{
            mProgressDialog.dismiss();
        }

    }

    public void addProductProcess(EditText mProductName, EditText mProductDescription,
                                  EditText mProductPrice, EditText mProductRemainingQuantity,
                                  ImageView mProductImage, Uri mImageUri, Context context) {
        validateForm(mProductName, mProductDescription, mProductPrice, mProductRemainingQuantity,
                mProductImage, context);

        showProgressDialog(context, true);

        product.addNewProduct(mProductName.getText().toString(),
                mProductDescription.getText().toString(),
                Integer.parseInt(mProductRemainingQuantity.getText().toString()),
                Double.parseDouble(mProductPrice.getText().toString()),
                mImageUri, user.getUserKey());

        toast.makeText(context, "Product Added!", Toast.LENGTH_SHORT).show();
    }

    // TODO: Haven't start this method
    public void previewProductProcess() {

    }

    public void retrieveProductProcess(Context context, String productID) {
        showProgressDialog(context, true);
        // TODO: Properly set the productID
        product.retrieveProduct(productID, context);
    }

    public void retrieveAllProductProcess(Context context) {
        showProgressDialog(context, true);
        product.all(context);
    }

    public void searchProductProcess(Context context, String value){
        showProgressDialog(context, true);
        product.searchProduct(context, value);
    }

    public void addToCartProcess(Object[] productDetails, int productOrderedQuantity, Context context) {
        showProgressDialog(context, true);

        if(current_user_id == null || current_user_id == ""){
            current_user_id = "eg8ixXm5SuSLj6tsNFfBJnSEcfB3";
        }else{
            current_user_id = getCurrentUser();
        }

        // TODO: Properly get the userKey using Firebase method
        shoppingCart.addToCart(productDetails, productOrderedQuantity, current_user_id);

    }

    public void showShoppingCartProcess(Context context) {
        showProgressDialog(context, true);

        if(current_user_id == null || current_user_id == ""){
            current_user_id = "eg8ixXm5SuSLj6tsNFfBJnSEcfB3";
        }else{
            current_user_id = getCurrentUser();
        }

        shoppingCart.showShoppingCart(current_user_id, context);
    }


    public void removeShoppingCartItem(Context context, String itemKey){

        showProgressDialog(context, true);
        shoppingCart.deleteShoppingCartItem(context, itemKey);

        showProgressDialog(context, false);// dismiss
        toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();

    }

    public void makeOrderProcess(String recipientName, String shipAddress, String shipPhoneNum,
                                 Context context) {
        showProgressDialog(context, true);

        if(current_user_id == null || current_user_id == ""){
            current_user_id = "eg8ixXm5SuSLj6tsNFfBJnSEcfB3";
        }else{
            current_user_id = getCurrentUser();
        }

        order.makeOrder(MIMController.valuePasser(), recipientName, shipAddress, shipPhoneNum,
                current_user_id, context);
    }


}
