package com.example.yeong.market2u.MIM_SearchProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.MIM_Model.Product_Model;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle("Product List");

        // remove focus from search input
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        ArrayList<ProductModel> products = new ArrayList<ProductModel>();

        ListView listView = (ListView) findViewById(R.id.product_list_view);
        EditText searchInput = (EditText) findViewById(R.id.search_product_input);
        Button searchBtn = (Button) findViewById(R.id.search_product_btn);

        products.add(new ProductModel("Product One", 12.12, 30));
        products.add(new ProductModel("Product Two", 13.13, 40));
        products.add(new ProductModel("Product Three", 14.14, 50));

        ProductListAdapter adapter = new ProductListAdapter(this, products );
        listView.setAdapter(adapter);



    }
}


