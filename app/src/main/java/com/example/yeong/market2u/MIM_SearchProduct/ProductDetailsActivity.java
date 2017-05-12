package com.example.yeong.market2u.MIM_SearchProduct;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

public class ProductDetailsActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();
    private Context productDetailsContext = ProductDetailsActivity.this;

    private ImageView mProductImage;
    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductDescription;
    private TextView mProductQuantity;
    private NumberPicker mQuantityOrdered;
    private Button btnAddToCart;
    private Button btnShoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        mProductImage = (ImageView) findViewById(R.id.product_image);
        mProductName = (TextView) findViewById(R.id.product_name);
        mProductPrice = (TextView) findViewById(R.id.product_price);
        mProductDescription = (TextView) findViewById(R.id.product_info);
        mProductQuantity = (TextView) findViewById(R.id.product_remaining_quantity);
        mQuantityOrdered = (NumberPicker) findViewById(R.id.ordered_quantity);
        btnAddToCart = (Button) findViewById(R.id.add_to_cart);
        btnShoppingCart = (Button) findViewById(R.id.shopping_cart);


        // Load the product details from Firebase
        Intent intent = getIntent();
        final Object[] pDetails = (Object[]) intent.getSerializableExtra("productDetails");
        mProductName.setText(pDetails[1].toString());
        mProductDescription.setText(pDetails[2].toString());
        mProductPrice.setText(pDetails[3].toString());
        mProductQuantity.setText("Quantity remaining: " + pDetails[4].toString());
        Glide.with(this).load(pDetails[5].toString()).into(mProductImage);

        mQuantityOrdered.setMinValue(1);
        mQuantityOrdered.setMaxValue(Integer.parseInt(pDetails[4].toString()));

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addToCartProcess(pDetails, mQuantityOrdered.getValue(), productDetailsContext);
            }
        });

        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showShoppingCartProcess(productDetailsContext);
            }
        });
    }
}
