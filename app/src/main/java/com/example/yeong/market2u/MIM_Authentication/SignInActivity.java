package com.example.yeong.market2u.MIM_Authentication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

public class SignInActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();
    private Context signInContext = SignInActivity.this;

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignInButton;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Sign In");

        // Views
        mEmailField = (EditText) findViewById(R.id.txtEmail);
        mPasswordField = (EditText) findViewById(R.id.txtPassword);
        mSignInButton = (Button) findViewById(R.id.btnSignIn);
        mSignUpButton = (Button) findViewById(R.id.btnSignUp);

        // Click listeners
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.signInProcess(mEmailField, mPasswordField, signInContext);
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigateTo(signInContext, SignUpActivity.class);
            }
        });

//        mSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

/*
        @Override
        public void onStart() {
            super.onStart();

            // Check auth on Activity start by UserModel through Controller
            if (mAuth.getCurrentUser() != null) {
                //onAuthSuccess(mAuth.getCurrentUser());
            }
        }
*/
}
