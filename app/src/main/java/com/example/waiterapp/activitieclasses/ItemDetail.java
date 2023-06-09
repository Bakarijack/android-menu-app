package com.example.waiterapp.activitieclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.waiterapp.R;
import com.example.waiterapp.databaseclasses.CartDatabaseHelper;
import com.example.waiterapp.dataclasses.CartItems;
import com.example.waiterapp.helpers.SharedList;

import java.util.ArrayList;

public class ItemDetail extends AppCompatActivity {
    private ImageView itemImg;
    private Button subButton,addButton,orderListLaunchButton;
    private TextView itemCounter,itemName,itemDesc,itemPrice,totalItemPrice;
    private ImageButton detailBackButton;
    private ArrayList<CartItems> cartItemsArrayList;

    private CartDatabaseHelper cartDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        cartItemsArrayList = new ArrayList<>();

        cartDatabaseHelper = new CartDatabaseHelper(this);

        itemImg = (ImageView) findViewById(R.id.itemImg);
        subButton = (Button) findViewById(R.id.subButton);
        addButton = (Button) findViewById(R.id.addButton);
        orderListLaunchButton = (Button) findViewById(R.id.orderListLaunchButton);
        itemCounter = (TextView) findViewById(R.id.itemCounter);
        itemName = (TextView) findViewById(R.id.itemName);
        itemDesc = (TextView) findViewById(R.id.itemDesc);
        itemPrice = (TextView) findViewById(R.id.itemPrice);
        totalItemPrice = (TextView) findViewById(R.id.totalItemPrice);
        detailBackButton = (ImageButton) findViewById(R.id.detailBackButton);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Glide.with(this).load(bundle.getString("pImage")).into(itemImg);
            itemName.setText(bundle.getString("pName"));
            itemDesc.setText(bundle.getString("pDescription"));
            itemPrice.setText(bundle.getString("pPrice"));

        }

        if (savedInstanceState != null){
            itemCounter.setText(savedInstanceState.getString("itemCounter"));
            totalItemPrice.setText(savedInstanceState.getString("totalCost"));
        }

        orderListLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToOrderList();
            }
        });

        detailBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMenuFromProductDetail();
            }
        });

    }

    public void addItemToOrderList(){
        String itemC = itemCounter.getText().toString().trim();

        String itemTPrice = totalItemPrice.getText().toString();
        String itemN = itemName.getText().toString();

        String itemImage = "";
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            itemImage = bundle.getString("pImage");
        }


        if (Integer.valueOf(itemC) == 0){
            Toast.makeText(this, "Item not added!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cartDatabaseHelper.isCartContainAnyItem()){
            if (cartDatabaseHelper.isItemExistInCart(itemN)){
                //item exist
                if (cartDatabaseHelper.updateCartItem(itemN,itemTPrice,Integer.valueOf(itemC))){
                    Toast.makeText(this, "Item updated in the cart", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Failed to update the item in the cart", Toast.LENGTH_SHORT).show();
                }
            }else {
                //item does not exist
                if (cartDatabaseHelper.isItemInserted(itemN,itemTPrice,Integer.valueOf(itemC),itemImage)){
                    Toast.makeText(this, "item inserted in cart 1", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Failed to insert item in the cart 1", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            if (cartDatabaseHelper.isItemInserted(itemN,itemTPrice,Integer.valueOf(itemC),itemImage)){
                Toast.makeText(this, "item inserted in cart 2", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Failed to insert item in the cart 2", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("itemCounter",itemCounter.getText().toString().trim());
        outState.putString("totalCost",totalItemPrice.getText().toString().trim());
    }

    public void incrementCounter(View view){
        int counter = Integer.valueOf(itemCounter.getText().toString());
        int itemP = Integer.valueOf(itemPrice.getText().toString());
        int totalPrice = Integer.valueOf(totalItemPrice.getText().toString());
        switch (view.getId()){
            case R.id.addButton:
                counter++;
                totalPrice += itemP;
                itemCounter.setText(String.valueOf(counter));
                totalItemPrice.setText(String.valueOf(totalPrice));
                break;
            case R.id.subButton:
                if (counter != 0){
                    counter--;
                    totalPrice -= itemP;
                    itemCounter.setText(String.valueOf(counter));
                    totalItemPrice.setText(String.valueOf(totalPrice));
                    break;
                }
        }
    }

    public  void launchMenuFromProductDetail(){
        Intent intent = new Intent(ItemDetail.this, Menu.class);
        startActivity(intent);
        finish();
    }
}