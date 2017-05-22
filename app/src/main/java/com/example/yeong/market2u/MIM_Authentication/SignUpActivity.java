package com.example.yeong.market2u.MIM_Authentication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_SearchProduct.ProductMenuActivity;
import com.example.yeong.market2u.R;

public class SignUpActivity extends AppCompatActivity {

    private MIMController controller = MIMController.getInstance();
    private Context signUpContext = SignUpActivity.this;

    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mFirstName;
    private EditText mLastName;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.product_menu, menu);

        // hide search & cart
        MenuItem search = menu.findItem(R.id.menu_search);
        MenuItem cart = menu.findItem(R.id.menu_cart);
        search.setVisible(false);
        cart.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                controller.navigateTo(SignUpActivity.this, SignInActivity.class);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
