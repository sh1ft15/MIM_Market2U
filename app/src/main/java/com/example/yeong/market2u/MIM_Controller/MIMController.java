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
import com.example.yeong.market2u.MIM_Model.OrderedItemModel;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.MIM_Model.ShoppingCartModel;
import com.example.yeong.market2u.MIM_Model.UserModel;
import com.example.yeong.market2u.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PrivateKey;
import java.util.ArrayList;

public final class MIMController {
    private static volatile MIMController instance;
    private static ArrayList<ShoppingCartModel> cart;
    private static ArrayList<ProductModel> products;
    private static ArrayList<UserModel> user_list;
    private static ArrayList<OrderedItemModel> orderedItemList;
    private static OrderModel orderFromDB;
    private static SharedPreferences prefs;
    private static ProductModel productToPass;
    private UserModel user = UserModel.getInstance();
    private ProductModel product = ProductModel.getInstance();
    private ShoppingCartModel shoppingCart = ShoppingCartModel.getInstance();
    private OrderModel order = OrderModel.getInstance();
    private OrderedItemModel orderedItem = OrderedItemModel.getInstance();
    private ProgressDialog mProgressDialog;
    private Toast toast;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String current_user_id = "guest";


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

    public static ProductModel getProductToPass() {
        return productToPass;
    }

    public static void setProductToPass(ProductModel product) {
        productToPass = product;
    }

    public static OrderModel valuePasserOrder() {
        return orderFromDB;
    }

    public static ArrayList<UserModel> getUserList() {
        return user_list;
    }

    public static void setUserList(ArrayList<UserModel> arrayList) {
        user_list = arrayList;
    }

    public static ArrayList<OrderedItemModel> getOrderedItemList() {
        return orderedItemList;
    }

    public static void setOrderedItemList(ArrayList<OrderedItemModel> arrayList) {
        orderedItemList = arrayList;
    }

    public static void valuePasserOrder(OrderModel orderDB) {
        orderFromDB = orderDB;
    }

    public void setCurrentUser(String id, Context context) {
        current_user_id = id;
    }

    public String getCurrentUser(Context context) {
        return current_user_id;
    }

    public String getCurrentUser() {
        current_user_id = mAuth.getCurrentUser().getUid();

        if (current_user_id.equals(null)) {
            // change to guest
            // guest can still browse item and add them to cart but cannot make an order
            current_user_id = "guest";
        }

        return current_user_id;
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

        showProgressDialog(context, false);


    }

    public void signOutProcess(Context context){
        FirebaseAuth.getInstance().signOut();
        current_user_id = "guest";
        navigateTo(context, MainActivity.class);
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
//        if (mProductImage.getDrawable() == null) {
//            new AlertDialog.Builder(context).setTitle("Attention")
//                    .setMessage("Product image is required.")
//                    .setNeutralButton("Back", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    }).show();
//
//            result = false;
//        }

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


        if(getCurrentUser(context) != "guest"){

            showProgressDialog(context, true);

            String user_id = user.getUserKey() == null? getCurrentUser() : user.getUserKey();

            product.addNewProduct(mProductName.getText().toString(),
                    mProductDescription.getText().toString(),
                    Integer.parseInt(mProductRemainingQuantity.getText().toString()),
                    Double.parseDouble(mProductPrice.getText().toString()),
                    mImageUri, user_id, context);


            // toast.makeText(context, "Product Added!", Toast.LENGTH_SHORT).show();
        }
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
            current_user_id = "guest"; //ub8UxTiNjQO4S3smPdQFFZYdQOJ3
        }else{
            current_user_id = getCurrentUser(context);
        }

        // TODO: Properly get the userKey using Firebase method
        shoppingCart.addToCart(productDetails, productOrderedQuantity, current_user_id);
        toast.makeText(context, "Product Added to Cart", Toast.LENGTH_SHORT).show();
    }

    public void showShoppingCartProcess(Context context) {
        showProgressDialog(context, true);

        if(current_user_id == null && current_user_id == "") {
            current_user_id = "guest";
        }else{
            current_user_id = getCurrentUser(context);
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
            current_user_id = "guest";
        }else{
            current_user_id = getCurrentUser(context);
        }

        order.makeOrder(MIMController.valuePasser(), recipientName, shipAddress, shipPhoneNum,
                current_user_id, context);
    }

    public void getProductSummaryProcess(Context context) {
        product.getProductSummary(getCurrentUser(), context);
    }

    public void updateProductProcess(String productID, String productName, String productDescription,
                                     int productRemainingQuantity, double productPrice,
                                     Uri newProductImageUri, String oldProductImageUrl, Context context) {
        product.updateProduct(productID, productName, productDescription, productRemainingQuantity,
                productPrice, newProductImageUri, oldProductImageUrl, getCurrentUser(), context);
    }

    public void deleteProductProcess(String productID, Context context) {
        product.deleteProduct(productID, context);
    }

    // TODO: Haven't implement
    public void reduceQuantityProcess(String productID, int orderedQuantity, Context context) {
        product.reduceQuantity(productID, orderedQuantity, context);
    }

    public void getUserDetails(Context context) {
        user.retrieveUser(getCurrentUser(), context);
    }

    public void requestToBecomeSellerProcess(Context context) {
        user.requestToBecomeSeller(getCurrentUser(), context);
    }

    public void getListOfSellerToApprove(Context context) {
        user.getSellerToApprove(context);
    }

    public void approveSellerProcess(String userkey, Context context) {
        user.approveSeller(userkey, context);
    }

    public void getAllUserProcess(Context context) {
        user.getAllUser(context);
    }

    public void showAllProductOrdered(Context context) {
        orderedItem.showAllProductOrdered(getCurrentUser(), context);
    }
}

