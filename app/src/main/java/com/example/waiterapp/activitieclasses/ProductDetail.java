package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.waiterapp.R;

public class ProductDetail extends AppCompatActivity {

    private ImageButton productDetailBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productDetailBackButton = (ImageButton) findViewById(R.id.productDetailBackButton);

        productDetailBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProductsFromProductDetail();
            }
        });
    }

    public  void launchProductsFromProductDetail(){
        Intent intent = new Intent(ProductDetail.this, RestaurantProducts.class);
        startActivity(intent);
        finish();
    }
}