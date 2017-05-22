package com.example.yeong.market2u.MIM_SearchProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.R;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private MIMController controller = MIMController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle("Product List");

        // Show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // remove focus from search input
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ArrayList<ProductModel> product_lists = new ArrayList<ProductModel>();

        ListView listView = (ListView) findViewById(R.id.product_list_view);
        final EditText searchInput = (EditText) findViewById(R.id.search_product_input);
        Button searchBtn = (Button) findViewById(R.id.search_product_btn);

        if(product_lists.isEmpty()){
            product_lists = MIMController.get_products();
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "";

                query = searchInput.getText().toString().trim();

                MIMController.getInstance().searchProductProcess(ProductListActivity.this, query);
            }
        });



        if (product_lists != null) {
            ProductListAdapter adapter = new ProductListAdapter(this, product_lists);
            listView.setAdapter(adapter);
        }

        listView.setEmptyView(findViewById(R.id.product_list_empty));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
                MIMController.getInstance().retrieveProductProcess(ProductListActivity.this, view.getTag().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.product_menu, menu);

        // hide search
        MenuItem search = menu.findItem(R.id.menu_search);
        search.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                return true;
            case R.id.menu_cart:
                controller.showShoppingCartProcess(this);
                return true;
            case android.R.id.home:
                controller.navigateTo(this, ProductMenuActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onBackPressed(){

        controller.navigateTo(this, ProductMenuActivity.class);

    }
}


