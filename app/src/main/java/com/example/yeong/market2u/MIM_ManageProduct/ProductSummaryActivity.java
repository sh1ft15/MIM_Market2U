package com.example.yeong.market2u.MIM_ManageProduct;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.MIM_SearchProduct.ProductList;
import com.example.yeong.market2u.MainActivity;
import com.example.yeong.market2u.R;
import com.example.yeong.market2u.Testing;

import java.util.ArrayList;

public class ProductSummaryActivity extends AppCompatActivity {
    private Button btnAddProduct;
    private ListView listView;
    private MIMController controller = MIMController.getInstance();
    private Context ProductSummaryContext = ProductSummaryActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_summary);
        setTitle("Product Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddProduct = (Button) findViewById(R.id.add_new_product);
        listView = (ListView) findViewById(R.id.list_product_summary);

        ArrayList<ProductModel> product_lists = MIMController.get_products();

        if(product_lists.isEmpty()){
            product_lists = MIMController.get_products();
        }

        ManageProductListAdapter productSummaryList
                = new ManageProductListAdapter(this, product_lists);
        listView.setAdapter(productSummaryList);




        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MIMController.navigateTo(ProductSummaryContext, AddProductActivity.class);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                controller.navigateTo(this, Testing.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){

        controller.navigateTo(this, Testing.class);


    }
}
