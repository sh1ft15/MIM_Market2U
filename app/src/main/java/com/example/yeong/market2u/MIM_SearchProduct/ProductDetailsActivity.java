package com.example.yeong.market2u.MIM_SearchProduct;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_OrderProduct.ShoppingCartActivity;
import com.example.yeong.market2u.MainActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setTitle("Product Details");

        mProductImage = (ImageView) findViewById(R.id.product_image);
        mProductName = (TextView) findViewById(R.id.product_name);
        mProductPrice = (TextView) findViewById(R.id.product_price);
        mProductDescription = (TextView) findViewById(R.id.product_info);
        mProductQuantity = (TextView) findViewById(R.id.product_remaining_quantity);
        mQuantityOrdered = (NumberPicker) findViewById(R.id.ordered_quantity);
        btnAddToCart = (Button) findViewById(R.id.add_to_cart);

        // remove focus from  input
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Load the product details from Firebase
        Intent intent = getIntent();
        final Object[] pDetails = (Object[]) intent.getSerializableExtra("productDetails");
        mProductName.setText(pDetails[1].toString());
        mProductDescription.setText(pDetails[2].toString());
        mProductPrice.setText("RM " + String.format("%.2f", Double.parseDouble(pDetails[3].toString())));
        mProductQuantity.setText("Quantity : " + pDetails[4].toString());

        if(pDetails[5] != null){
            Glide.with(this).load(pDetails[5].toString()).into(mProductImage);
        }else{
            mProductImage.setImageResource(R.drawable.loading);
        }




        mQuantityOrdered.setMinValue(1);
        mQuantityOrdered.setMaxValue(Integer.parseInt(pDetails[4].toString()));

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addToCartProcess(pDetails, mQuantityOrdered.getValue(), productDetailsContext);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast.makeText(ProductList.this, "TESTSTST", Toast.LENGTH_LONG).show();
                MIMController.getInstance().searchProductProcess(ProductDetailsActivity.this, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                // TODO: Add search function
                return true;
            case R.id.menu_cart:
                controller.showShoppingCartProcess(productDetailsContext);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed (){
        controller.navigateTo(productDetailsContext, ProductList.class);
    }
}
