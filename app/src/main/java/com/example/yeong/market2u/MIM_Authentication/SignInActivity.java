package com.example.yeong.market2u.MIM_Authentication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_SearchProduct.ProductMenuActivity;
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

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        if(getIntent().hasExtra("status")){
            Toast.makeText(getApplicationContext(), getIntent().getStringExtra("status"), Toast.LENGTH_SHORT).show();
        }


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
                controller.navigateTo(SignInActivity.this, ProductMenuActivity.class);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
