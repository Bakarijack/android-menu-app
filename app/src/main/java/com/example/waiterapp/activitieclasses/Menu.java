package com.example.waiterapp.activitieclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waiterapp.R;
import com.example.waiterapp.adapters.CustomerProductAdapter;
import com.example.waiterapp.adapters.CustomerSeasonProductAdapter;
import com.example.waiterapp.dataclasses.ProductData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    private ImageButton menuButton,cartButton;
    private RecyclerView customerSeasonRecyclerView,customerMainRecyclerView;
    private TextView viewT;


    private List<ProductData> productDataList;
    private CustomerProductAdapter customerProductAdapter;
    private CustomerSeasonProductAdapter customerSeasonProductAdapter;

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuButton = (ImageButton) findViewById(R.id.menuButton);
        cartButton = (ImageButton) findViewById(R.id.cartButton);
        customerSeasonRecyclerView = (RecyclerView) findViewById(R.id.customerSeasonRecyclerView);
        customerMainRecyclerView = (RecyclerView) findViewById(R.id.customerMainRecyclerView);
        viewT = (TextView) findViewById(R.id.viewT);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Menu.this,3);
        customerMainRecyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Menu.this,LinearLayoutManager.HORIZONTAL,true);
        customerSeasonRecyclerView.setLayoutManager(linearLayoutManager);


        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        productDataList = new ArrayList<>();
        customerProductAdapter = new CustomerProductAdapter(Menu.this,productDataList);
        customerMainRecyclerView.setAdapter(customerProductAdapter);

        customerSeasonProductAdapter = new CustomerSeasonProductAdapter(Menu.this,productDataList);
        customerSeasonRecyclerView.setAdapter(customerSeasonProductAdapter);

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

                customerProductAdapter.notifyDataSetChanged();
                customerSeasonProductAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMenuAction();
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCartAction();
            }
        });
    }

    public void handleCartAction(){
        Intent orderListLaunch = new Intent(Menu.this, OrderItemsList.class);
        startActivity(orderListLaunch);
    }

    public void handleMenuAction(){
        PopupMenu popupMenu = new PopupMenu(Menu.this,menuButton);

        popupMenu.getMenuInflater().inflate(R.menu.menu_items,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.staffLogin:
                        launchStaffLogin();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public void launchStaffLogin(){
        Intent launchLoginActivity = new Intent(this, StaffLogin.class);
        startActivity(launchLoginActivity);
    }

}