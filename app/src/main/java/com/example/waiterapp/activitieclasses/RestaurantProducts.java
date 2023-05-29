package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.waiterapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RestaurantProducts extends AppCompatActivity {
    private FloatingActionButton productAddFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_products);

        productAddFab = (FloatingActionButton) findViewById(R.id.productAddFab);

        productAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddProduct();
            }
        });
    }

    public void launchAddProduct(){
        Intent intent = new Intent(RestaurantProducts.this, AddProduct.class);
        startActivity(intent);
    }
}