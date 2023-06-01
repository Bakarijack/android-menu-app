package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.waiterapp.R;
import com.example.waiterapp.adapters.CartItemsAdapter;
import com.example.waiterapp.databaseclasses.CartDatabaseHelper;
import com.example.waiterapp.dataclasses.CartItems;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsList extends AppCompatActivity {

    private ImageButton menuLaunchButton;
    private RecyclerView orderListRecyclerView;

    private CartDatabaseHelper cartDatabaseHelper;
    private List<CartItems> cartItemsList;
    private CartItemsAdapter cartItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items_list);

        menuLaunchButton = (ImageButton) findViewById(R.id.menuLaunchButton);
        orderListRecyclerView = (RecyclerView) findViewById(R.id.orderListRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderItemsList.this);
        orderListRecyclerView.setLayoutManager(linearLayoutManager);

        cartItemsList = new ArrayList<>();
        cartItemsAdapter = new CartItemsAdapter(OrderItemsList.this,cartItemsList);
        orderListRecyclerView.setAdapter(cartItemsAdapter);


        cartDatabaseHelper = new CartDatabaseHelper(this);

        if (cartDatabaseHelper.isCartContainAnyItem()){
            displayCartItems();
        }

        menuLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBackToManuAction();
            }
        });
    }

    public void handleBackToManuAction(){
        Intent menuLaunch = new Intent(OrderItemsList.this, Menu.class);
        startActivity(menuLaunch);
    }

    public void displayCartItems(){
        cartItemsList.clear();
        Cursor cursor = cartDatabaseHelper.getAllCartItems();

        if (cursor.getCount() == 0){
            return;
        }else {
            while (cursor.moveToNext()){
                cartItemsList.add(new CartItems(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4)
                ));
            }
        }
        cartItemsAdapter.notifyDataSetChanged();
    }
}