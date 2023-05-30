package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.waiterapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RestaurantProducts extends AppCompatActivity {
    private FloatingActionButton productAddFab;
    private ImageButton menuButtonfromProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_products);

        productAddFab = (FloatingActionButton) findViewById(R.id.productAddFab);
        menuButtonfromProducts = (ImageButton) findViewById(R.id.menuButtonfromProducts);

        productAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddProduct();
            }
        });


        menuButtonfromProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lauchMenuFromProduct();
            }
        });
    }

    public void lauchMenuFromProduct(){
        Intent intent = new Intent(RestaurantProducts.this, StaffHomePage.class);
        startActivity(intent);
        finish();
    }

    public void launchAddProduct(){
        Intent intent = new Intent(RestaurantProducts.this, AddProduct.class);
        startActivity(intent);
    }
}