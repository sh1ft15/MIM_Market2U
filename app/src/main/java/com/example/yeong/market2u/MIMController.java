package com.example.yeong.market2u;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.yeong.market2u.MIM_Model.UserModel;

public class MIMController {

    private UserModel user = new UserModel();
    private ProgressDialog mProgressDialog;

    public MIMController() {

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

    private void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }


    public static void navigateTo(Context contextOrigin, Class classDestination) {
        contextOrigin.startActivity(new Intent(contextOrigin, classDestination));
    }

    public static void navigateTo(Context contextOrigin, Class classDestination,
                                  String extraString, String extraValue) {
        contextOrigin.startActivity(new Intent(contextOrigin, classDestination)
                .putExtra(extraString, extraValue));
    }
}
