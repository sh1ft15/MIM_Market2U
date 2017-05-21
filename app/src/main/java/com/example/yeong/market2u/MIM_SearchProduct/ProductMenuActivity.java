package com.example.yeong.market2u.MIM_SearchProduct;

import android.app.SearchManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MainActivity;
import com.example.yeong.market2u.R;
import com.example.yeong.market2u.Testing;

public class ProductMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context productMenuContext = ProductMenuActivity.this;
    private MIMController controller = MIMController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_menu);

        // For navigation drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView userID = (TextView) header.findViewById(R.id.userIdentity);
        userID.setText("ID : " + controller.getCurrentUser());


        ImageView imgClothesCollection = (ImageView) findViewById(R.id.imgClothesCollection);
        ImageView imgDressCollection = (ImageView) findViewById(R.id.imgDressesCollection);

        imgClothesCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.searchProductProcess(productMenuContext, "Cloth");
            }
        });

        imgDressCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.searchProductProcess(productMenuContext, "Dress");
            }
        });
    }

    // Done
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast.makeText(ProductList.this, "TESTSTST", Toast.LENGTH_LONG).show();
                controller.searchProductProcess(ProductMenuActivity.this, query);
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
                return true;
            case R.id.menu_cart:
                controller.showShoppingCartProcess(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        int id = item.getItemId();
//        // This is for setting button
//        if (id == R.id.menu_search) {
//            MenuItem cartIcon = (MenuItem) findViewById(R.id.menu_cart);
//            cartIcon.setVisible(false);
//        }
//        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            MIMController.navigateTo(productMenuContext, ProductMenuActivity.class);

        } else if (id == R.id.nav_account) {
            controller.getUserDetails(productMenuContext);

        } else if (id == R.id.nav_orders) {
            controller.showAllProductOrdered(productMenuContext);

        } else if (id == R.id.nav_cart) {
            controller.showShoppingCartProcess(ProductMenuActivity.this);

        } else if (id == R.id.nav_product) {
            controller.retrieveAllProductProcess(ProductMenuActivity.this);

        } else if (id == R.id.nav_my_product) {
            controller.getProductSummaryProcess(ProductMenuActivity.this);

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.sign_out) {
            controller.signOutProcess(productMenuContext);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
