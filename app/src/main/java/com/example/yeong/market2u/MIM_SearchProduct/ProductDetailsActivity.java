package com.example.yeong.market2u.MIM_SearchProduct;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

public class ProductDetailsActivity extends AppCompatActivity {
    private MIMController controller = MIMController.getInstance();

    private ImageView mProductImage;
    private TextView mProductName;
    private TextView mProductPrice;
    private TextView mProductDescription;
    private TextView mProductQuantity;
    private Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        mProductImage = (ImageView) findViewById(R.id.product_image);
        mProductName = (TextView) findViewById(R.id.product_name);
        mProductPrice = (TextView) findViewById(R.id.product_price);
        mProductDescription = (TextView) findViewById(R.id.product_info);
        mProductQuantity = (TextView) findViewById(R.id.product_remaining_quantity);
        btnAddToCart = (Button) findViewById(R.id.add_to_cart);

        // Load the product details from Firebase
        Intent intent = getIntent();
        Object[] pDetails = (Object[]) intent.getSerializableExtra("productDetails");
        mProductName.setText(pDetails[0].toString());
        mProductDescription.setText(pDetails[1].toString());
        mProductPrice.setText(pDetails[2].toString());
        mProductQuantity.setText(pDetails[3].toString());
        Glide.with(this).load(pDetails[4].toString()).into(mProductImage);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
