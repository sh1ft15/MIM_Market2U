package com.example.yeong.market2u.MIM_Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.Testing;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by yeong on 26/4/2017.
 */

public final class UserModel {
    private static volatile UserModel instance;

    private String userKey;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String defaultShippingAddress;
    private Boolean sellerStatus;
    private String storeName;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private UserModel() {

    }

    private UserModel(String emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.defaultShippingAddress = null;
        this.sellerStatus = false;
        this.storeName = null;
    }

    public static UserModel getInstance() {
        if (instance == null) {
            synchronized (MIMController.class) {
                if (instance == null) {
                    instance = new UserModel();
                }
            }
        }
        return instance;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String UserKey) {
        this.userKey = UserKey;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public void setDefaultShippingAddress(String defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }

    public Boolean getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(Boolean sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    // Create a user in Firebase Authentication using email and password
    public void createUserAccountInAuthentication
    (final String emailAddress, final String password,
     final String firstName, final String lastName, final Context context) {
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            setUserKey(task.getResult().getUser().getUid());
                            //mAuth.getCurrentUser().sendEmailVerification();
                            createNewUserInDatabase(getUserKey(),emailAddress, firstName, lastName);
                        } else {
                            setUserKey(null);
                        }

                        MIMController.navigateTo(context, Testing.class, "userKey", getUserKey());
                    }
                });
    }

    // Create a new user node in Firebase Database
    private void createNewUserInDatabase(String userKey, String emailAddress,
                                         String firstName, String lastName) {
        UserModel user = new UserModel(emailAddress, firstName, lastName);

        mDatabase.child("user").child(userKey).setValue(user);
    }

    // Sign in authentication process in Firebase Authentication
    public void signInValidationInAuthentication(String emailAddress, String password,
                                                 final Context context) {
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("SignIn_Process", "Status: " + task.isSuccessful());

                        if (task.isSuccessful()) {
                            setUserKey(task.getResult().getUser().getUid());
                        } else {
                            setUserKey(null);
                        }

                        MIMController.navigateTo(context, Testing.class, "userKey", getUserKey());
                    }
                });
    }
}