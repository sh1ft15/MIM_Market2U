package com.example.yeong.market2u.MIM_Controller;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.StackView;

import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.MIM_Model.UserModel;

public final class MIMController {
    private static volatile MIMController instance;
    private UserModel user = UserModel.getInstance();
    private ProductModel product = ProductModel.getInstance();

    private ProgressDialog mProgressDialog;

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

    public void signInProcess(EditText mEmailField, EditText mPasswordField, Context context) {
        validateForm(mEmailField, mPasswordField);

        showProgressDialog(context);

        user.signInValidationInAuthentication
                (mEmailField.getText().toString(), mPasswordField.getText().toString(), context);
    }

    public void signUpProcess(EditText mEmailField, EditText mPasswordField,
                              EditText mFirstName, EditText mLastName, Context context) {
        validateForm(mEmailField, mPasswordField, mFirstName, mLastName);

        showProgressDialog(context);

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

    private void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void addProductProcess(EditText mProductName, EditText mProductDescription,
                                  EditText mProductPrice, EditText mProductRemainingQuantity,
                                  ImageView mProductImage, Uri mImageUri, Context context) {
        validateForm(mProductName, mProductDescription, mProductPrice, mProductRemainingQuantity,
                mProductImage, context);

        showProgressDialog(context);

        product.addNewProduct(mProductName.getText().toString(),
                mProductDescription.getText().toString(),
                Integer.parseInt(mProductRemainingQuantity.getText().toString()),
                Double.parseDouble(mProductPrice.getText().toString()),
                mImageUri, user.getUserKey());
    }

    public void previewProductProcess() {

    }

    public void retrieveProductProcess(Context context) {
        showProgressDialog(context);
        product.retrieveProduct("-KjlJPlMTkN-1b0xKfn0", context);
    }
}