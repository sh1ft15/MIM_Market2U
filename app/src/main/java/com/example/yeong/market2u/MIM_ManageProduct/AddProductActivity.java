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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yeong.market2u.MIM_Controller.MIMController;
import com.example.yeong.market2u.R;

import java.io.IOException;

public class AddProductActivity extends AppCompatActivity {
    private static final int RC_PHOTO_PICKER = 2;
    private MIMController controller = MIMController.getInstance();
    private Context addProductContext = AddProductActivity.this;
    private EditText mProductName;
    private EditText mProductDescription;
    private EditText mProductPrice;
    private EditText mProductRemainingQuantity;
    private Button btnAddProduct;
    private Button btnPreviewProduct;
    private Button btnAddProductPhoto;
    private ImageView imageProduct;
    private Uri mImageUri;
    private boolean photo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setTitle("Add Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProductName = (EditText) findViewById(R.id.txtProductName);
        mProductDescription = (EditText) findViewById(R.id.txtProductDescription);
        mProductPrice = (EditText) findViewById(R.id.txtProductPrice);
        mProductRemainingQuantity = (EditText) findViewById(R.id.txtProductRemainingQuantity);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);
        // btnPreviewProduct = (Button) findViewById(R.id.btnPreviewProduct);
        btnAddProductPhoto = (Button) findViewById(R.id.btnAddProductPhoto);
        imageProduct = (ImageView) findViewById(R.id.imageProduct);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addProductProcess(mProductName, mProductDescription, mProductPrice,
                        mProductRemainingQuantity, imageProduct, mImageUri, addProductContext);
                Toast.makeText(getApplicationContext(), "Product Added.", Toast.LENGTH_SHORT).show();
            }
        });

//        btnPreviewProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(added == true){
//                    controller.previewProductProcess();
//                }else{
//                    Toast.makeText(getApplicationContext(), "Product Not Yet Added.", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });

        btnAddProductPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"),
                        RC_PHOTO_PICKER);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
            mImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);
                imageProduct.setImageBitmap(bitmap);
                photo = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
