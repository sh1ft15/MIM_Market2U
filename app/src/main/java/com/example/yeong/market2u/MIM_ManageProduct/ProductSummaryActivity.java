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
import com.example.yeong.market2u.R;

public class ProductSummaryActivity extends AppCompatActivity {
    private Button btnAddProduct;
    private ListView listView;
    private Context ProductSummaryContext = ProductSummaryActivity.this;
    private MIMController controller = MIMController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_summary);
        setTitle("Product Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddProduct = (Button) findViewById(R.id.add_new_product);
        listView = (ListView) findViewById(R.id.list_product_summary);

        ManageProductListAdapter productSummaryList
                = new ManageProductListAdapter(this, MIMController.get_products());
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
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
