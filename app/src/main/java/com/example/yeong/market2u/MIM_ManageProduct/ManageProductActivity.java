package com.example.yeong.market2u.MIM_ManageProduct;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.MIM_Model.ProductModel;
import com.example.yeong.market2u.R;

import java.io.IOException;

public class ManageProductActivity extends AppCompatActivity {
    private static final int RC_PHOTO_PICKER = 2;
    private EditText productName;
    private EditText productPrice;
    private EditText productRemainingQuantity;
    private EditText productDescription;
    private ImageView productImage;
    private Button btnChangeImage;
    private Button btnDeleteProduct;
    private Button btnConfirmChanges;
    private Uri productImageUri;
    private MIMController controller = MIMController.getInstance();
    private Context ManageProductContext = ManageProductActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        setTitle("Manage Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productName = (EditText) findViewById(R.id.edit_product_name);
        productPrice = (EditText) findViewById(R.id.edit_product_price);
        productRemainingQuantity = (EditText) findViewById(R.id.edit_product_remaining_quantity);
        productDescription = (EditText) findViewById(R.id.edit_product_description);
        productImage = (ImageView) findViewById(R.id.edit_product_image);
        btnChangeImage = (Button) findViewById(R.id.btnEditProductImage);
        btnDeleteProduct = (Button) findViewById(R.id.btnDeleteProduct);
        btnConfirmChanges = (Button) findViewById(R.id.btnConfirmChanges);

        final ProductModel product = MIMController.getProductToPass();
        productName.setText(product.getProductName());
        productPrice.setText(String.format("%.2f", product.getProductPrice()));
        productRemainingQuantity.setText(Integer.toString(product.getProductRemainingQuantity()));
        productDescription.setText(product.getProductDescription());
        Glide.with(this).load(product.getProductImageUrl()).into(productImage);

        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"),
                        RC_PHOTO_PICKER);
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.deleteProductProcess(product.getProductID(), ManageProductContext);
            }
        });

        btnConfirmChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.updateProductProcess(product.getProductID(), productName.getText().toString(),
                        productDescription.getText().toString(),
                        Integer.parseInt(productRemainingQuantity.getText().toString()),
                        Double.parseDouble(productPrice.getText().toString()), productImageUri,
                        product.getProductImageUrl(), ManageProductContext);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            productImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), productImageUri);

                productImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


