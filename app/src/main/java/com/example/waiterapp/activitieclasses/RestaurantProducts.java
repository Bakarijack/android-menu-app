package com.example.waiterapp.activitieclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.waiterapp.R;
import com.example.waiterapp.adapters.ProductsAdapter;
import com.example.waiterapp.dataclasses.ProductData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantProducts extends AppCompatActivity {
    private FloatingActionButton productAddFab;
    private ImageButton menuButtonfromProducts;
    private RecyclerView productsRecyclerView;

    private List<ProductData> productDataList;
    private ProductsAdapter productsAdapter;

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_products);

        productAddFab = (FloatingActionButton) findViewById(R.id.productAddFab);
        menuButtonfromProducts = (ImageButton) findViewById(R.id.menuButtonfromProducts);
        productsRecyclerView = (RecyclerView) findViewById(R.id.productsRecyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(RestaurantProducts.this,2);
        productsRecyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantProducts.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        
        productDataList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(RestaurantProducts.this,productDataList);
        productsRecyclerView.setAdapter(productsAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants").child("Mimo Cafe").child("Products");
        dialog.show();

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productDataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    ProductData productData = itemSnapshot.getValue(ProductData.class);
                    productData.setKey(itemSnapshot.getKey());
                    productDataList.add(productData);
                }

                productsAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });


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