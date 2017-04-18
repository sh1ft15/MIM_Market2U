package com.example.yeong.market2u;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yeong.market2u.MIM_Model.ProductModel;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle("Product List");

        // remove focus from search input
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        String[] testArray = {"Product 1", "Product 2", "Product 3", "Product 4", "Product 5", "Product 6"};

        // Create an ArrayList of AndroidFlavor objects
        ArrayList<ProductModel> product_models = new ArrayList<ProductModel>();
        product_models.add(new ProductModel("Donut", 11.2));
        product_models.add(new ProductModel("Donut 2", 11.2));
        product_models.add(new ProductModel("Donut 3", 11.2));
        product_models.add(new ProductModel("Donut 4", 11.2));
        product_models.add(new ProductModel("Donut 5", 11.2));
        product_models.add(new ProductModel("Donut 6", 11.2));


        ListView listView = (ListView) findViewById(R.id.product_list_view);
        EditText searchInput = (EditText) findViewById(R.id.search_product_input);
        Button searchBtn = (Button) findViewById(R.id.search_product_btn);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_selectable_list_item, testArray);

        ProductListAdapter adapter = new ProductListAdapter(this, product_models);

        listView.setAdapter(adapter);

    }
}
