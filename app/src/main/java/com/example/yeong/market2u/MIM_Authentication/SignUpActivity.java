package com.example.yeong.market2u.MIM_Authentication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yeong.market2u.MIMController;
import com.example.yeong.market2u.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mFirstName;
    private EditText mLastName;
    private Button mSignUpButton;

    private MIMController controller = new MIMController();
    private Context signUpContext = SignUpActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = (EditText) findViewById(R.id.txtSignUpEmail);
        mPasswordField = (EditText) findViewById(R.id.txtSignUpPassword);
        mFirstName = (EditText) findViewById(R.id.txtFirstName);
        mLastName = (EditText) findViewById(R.id.txtLastName);
        mSignUpButton = (Button) findViewById(R.id.btnSignUp);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.signUpProcess(mEmailField, mPasswordField, mFirstName,
                        mLastName, signUpContext);
            }
        });
    }
}
