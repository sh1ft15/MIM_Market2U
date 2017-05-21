package com.example.yeong.market2u.MIM_Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_ManageUser.ApproveSellerActivity;
import com.example.yeong.market2u.MIM_ManageUser.ManageSellerActivity;
import com.example.yeong.market2u.MIM_ManageUser.UserAccountActivity;
import com.example.yeong.market2u.MainActivity;
import com.example.yeong.market2u.Testing;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by yeong on 26/4/2017.
 */

public final class UserModel {
    private static volatile UserModel instance;

    private String userKey;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String sellerStatus;
    private Object[] userDetails = new Object[5];
    private ArrayList<UserModel> user_list = new ArrayList<UserModel>();

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");

    private UserModel() {

    }

    private UserModel(String emailAddress, String firstName, String lastName) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sellerStatus = "Inactive";
    }

    private UserModel(String userKey, String emailAddress, String firstName, String lastName,
                      String sellerStatus) {
        this.userKey = userKey;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sellerStatus = sellerStatus;
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

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    // Create a user in Firebase Authentication using email and password
    public void createUserAccountInAuthentication(final String emailAddress, final String password,
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

        mDatabase.child(userKey).setValue(user);
    }

    // Sign in authentication process in Firebase Authentication
    public void signInValidationInAuthentication(String emailAddress, String password,
                                                 final Context context) {
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        Log.d("SignIn_Process", "Status: " + task.isSuccessful());

                        if (task.isSuccessful()) {

                            userDetails[0] = task.getResult().getUser().getUid();
                            userDetails[1] = task.getResult().getUser().getEmail();

                            if (task.getResult().getUser().getEmail().equals("admin@market2u.com")) {
                                getAllUser(context);
                            } else {
                                MIMController.getInstance().setCurrentUser(userDetails[0].toString(), context);
                                MIMController.navigateTo(context, Testing.class, "userDetails", userDetails);
                            }

                        } else {
                            setUserKey(null);
                            MIMController.navigateTo(context, MainActivity.class, "status", "Incorrect login information!");
                        }
                    }
        });
    }

    public void retrieveUser(final String userKey, final Context context) {
        Query query = mDatabase.orderByKey();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    if (postSnapshot.getKey().equals(userKey)) {
                        UserModel userFromDatabase = postSnapshot.getValue(UserModel.class);

                        setUserKey(userKey);
                        setEmailAddress(userFromDatabase.emailAddress);
                        setFirstName(userFromDatabase.firstName);
                        setLastName(userFromDatabase.lastName);
                        setSellerStatus(userFromDatabase.sellerStatus);

                        userDetails[0] = getUserKey();
                        userDetails[1] = getEmailAddress();
                        userDetails[2] = getFirstName();
                        userDetails[3] = getLastName();
                        userDetails[4] = getSellerStatus();
                    }
                }
                MIMController.navigateTo(context, UserAccountActivity.class, "userDetails", userDetails);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void getAllUser(final Context context) {
        Query query = mDatabase.orderByKey();

        user_list.clear();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserModel user = postSnapshot.getValue(UserModel.class);

                    user_list.add(new UserModel(postSnapshot.getKey(), user.getEmailAddress(),
                            user.getFirstName(), user.getLastName(), user.getSellerStatus()));
                }
                MIMController.setUserList(user_list);
                MIMController.navigateTo(context, ManageSellerActivity.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void requestToBecomeSeller(String userKey, Context context) {
        mDatabase.child(userKey).child("sellerStatus").setValue("Pending");
        retrieveUser(userKey, context);
    }

    public void getSellerToApprove(final Context context) {
        Query query = mDatabase.orderByKey();

        user_list.clear();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserModel user = postSnapshot.getValue(UserModel.class);

                    if (user.getSellerStatus().equals("Pending")) {
                        user_list.add(new UserModel(postSnapshot.getKey(), user.getEmailAddress(),
                                user.getFirstName(), user.getLastName(), user.getSellerStatus()));
                    }
                }

                MIMController.setUserList(user_list);
                MIMController.navigateTo(context, ApproveSellerActivity.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void approveSeller(String userKey, Context context) {
        mDatabase.child(userKey).child("sellerStatus").setValue("Active");
        getSellerToApprove(context);
    }
}
