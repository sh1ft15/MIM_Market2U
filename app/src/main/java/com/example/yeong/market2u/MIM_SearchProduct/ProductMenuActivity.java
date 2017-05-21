package com.example.yeong.market2u.MIM_SearchProduct;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

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

        ImageView imgClothesCollection = (ImageView) findViewById(R.id.imgClothesCollection);
        ImageView imgDressCollection = (ImageView) findViewById(R.id.imgDressesCollection);

        imgClothesCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controller.searchProcess("Cloth", productMenuContext);
            }
        });

        imgDressCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controller.searchProcess("Dress", productMenuContext);
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

    // Need to add Search part
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        // This is for setting button
        if (id == R.id.menu_search) {
            MenuItem cartIcon = (MenuItem) findViewById(R.id.menu_cart);
            cartIcon.setVisible(false);
        }

        return super.onOptionsItemSelected(item);
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

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
