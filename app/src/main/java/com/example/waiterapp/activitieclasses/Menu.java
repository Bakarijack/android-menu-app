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
import com.example.waiterapp.adapters.CustomerQuickFilterProductAdapter;
import com.example.waiterapp.adapters.CustomerSeasonProductAdapter;
import com.example.waiterapp.dataclasses.CartItems;
import com.example.waiterapp.dataclasses.CategoryData;
import com.example.waiterapp.dataclasses.ProductData;
import com.example.waiterapp.helpers.SharedList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Menu extends AppCompatActivity {
    private ImageButton menuButton,cartButton;
    private RecyclerView customerSeasonRecyclerView,customerMainRecyclerView,quickFilterRecyclerView;
    private TextView viewT;


    private List<CategoryData> categoryDataList;
    private List<String> rawDataList;
    private List<ProductData> productDataList;
    private CustomerProductAdapter customerProductAdapter;
    private CustomerSeasonProductAdapter customerSeasonProductAdapter;
    private CustomerQuickFilterProductAdapter customerQuickFilterProductAdapter;

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
        quickFilterRecyclerView = (RecyclerView) findViewById(R.id.quickFilterRecyclerView);
        viewT = (TextView) findViewById(R.id.viewT);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Menu.this,3);
        customerMainRecyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Menu.this,LinearLayoutManager.HORIZONTAL,true);
        customerSeasonRecyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(Menu.this, LinearLayoutManager.HORIZONTAL,true);
        quickFilterRecyclerView.setLayoutManager(linearLayoutManager1);


        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        categoryDataList = new ArrayList<>();
        rawDataList = new ArrayList<>();
        customerQuickFilterProductAdapter = new CustomerQuickFilterProductAdapter(Menu.this,categoryDataList);
        quickFilterRecyclerView.setAdapter(customerQuickFilterProductAdapter);

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
                    rawDataList.add(productData.getProductCategory());
                }

                Set<String> unique = new HashSet<>(rawDataList);

                for (String item: unique){
                    categoryDataList.add(new CategoryData(item));
                }


                customerProductAdapter.notifyDataSetChanged();
                customerSeasonProductAdapter.notifyDataSetChanged();
                customerQuickFilterProductAdapter.notifyDataSetChanged();
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