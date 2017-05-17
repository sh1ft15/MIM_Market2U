package com.example.yeong.market2u.MIM_SearchProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.MIM_Model.Product_Model;
import com.example.yeong.market2u.MainActivity;
import com.example.yeong.market2u.R;
import com.example.yeong.market2u.Testing;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    private MIMController controller = MIMController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle("Product List");

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
                Toast toast = null;

                query = searchInput.getText().toString().trim();

//                if(query.isEmpty() || query.length() == 0 || query.equals("") || query == null) {
//                    toast.makeText(ProductList.this, "Please enter the search input", Toast.LENGTH_LONG).show();
//                }else {
//                    MIMController.getInstance().searchProductProcess(ProductList.this, query);
//                }

                MIMController.getInstance().searchProductProcess(ProductList.this, query);



            }
        });



        ProductListAdapter adapter = new ProductListAdapter(this, product_lists);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.product_list_empty));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), view.getTag().toString(), Toast.LENGTH_SHORT).show();
                MIMController.getInstance().retrieveProductProcess(ProductList.this, view.getTag().toString());
            }
        });


    }

    @Override
    public void onBackPressed(){

        String current_user_id = MIMController.getInstance().getCurrentUser(ProductList.this);

        if(current_user_id == null || current_user_id == ""){
            controller.navigateTo(this, MainActivity.class);
        }else{
            controller.navigateTo(this, Testing.class);
        }

    }
}


