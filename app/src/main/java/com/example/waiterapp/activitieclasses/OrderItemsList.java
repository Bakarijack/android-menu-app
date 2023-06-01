package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waiterapp.R;
import com.example.waiterapp.adapters.CartItemsAdapter;
import com.example.waiterapp.databaseclasses.CartDatabaseHelper;
import com.example.waiterapp.dataclasses.CartItems;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsList extends AppCompatActivity {

    private ImageButton menuLaunchButton,deleteOrdersButton;
    private RecyclerView orderListRecyclerView;

    private TextView subtotalPrice,discountAmount,totalAmount;

    private CartDatabaseHelper cartDatabaseHelper;
    private List<CartItems> cartItemsList;
    private CartItemsAdapter cartItemsAdapter;
    private List<String> priceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items_list);

        menuLaunchButton = (ImageButton) findViewById(R.id.menuLaunchButton);
        orderListRecyclerView = (RecyclerView) findViewById(R.id.orderListRecyclerView);
        deleteOrdersButton = (ImageButton) findViewById(R.id.deleteOrdersButton);

        subtotalPrice = (TextView) findViewById(R.id.subtotalPrice);
        discountAmount = (TextView) findViewById(R.id.discountAmount);
        totalAmount = (TextView) findViewById(R.id.totalAmount);

        priceList = new ArrayList<>();

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

        deleteOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOrder();
            }
        });
    }
    
    public  void deleteOrder(){
        if (cartDatabaseHelper.deleteAllCartItems()){
            Toast.makeText(this, "Cart items successfully deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(OrderItemsList.this,Menu.class);
            startActivity(intent);
            finish();
        }
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
                priceList.add(cursor.getString(2));
            }
        }
        Double total = 0.0;
        for (String item: priceList){
            total += Double.valueOf(item);
        }
        subtotalPrice.setText(String.valueOf(total));
        Double discount = Double.valueOf(discountAmount.getText().toString());
        Double totalA = total - discount;
        totalAmount.setText(String.valueOf(totalA));
        cartItemsAdapter.notifyDataSetChanged();
    }
}