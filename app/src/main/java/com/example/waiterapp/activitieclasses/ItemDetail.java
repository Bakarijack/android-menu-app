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

public class ItemDetail extends AppCompatActivity {
    private ImageView itemImg;
    private Button subButton,addButton,orderListLaunchButton;
    private TextView itemCounter,itemName,itemDesc,itemPrice;
    private ImageButton detailBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        itemImg = (ImageView) findViewById(R.id.itemImg);
        subButton = (Button) findViewById(R.id.subButton);
        addButton = (Button) findViewById(R.id.addButton);
        orderListLaunchButton = (Button) findViewById(R.id.orderListLaunchButton);
        itemCounter = (TextView) findViewById(R.id.itemCounter);
        itemName = (TextView) findViewById(R.id.itemName);
        itemDesc = (TextView) findViewById(R.id.itemDesc);
        itemPrice = (TextView) findViewById(R.id.itemPrice);
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

        if (Integer.valueOf(itemC) == 0){
            Toast.makeText(this, "Item not added!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("itemCounter",itemCounter.getText().toString().trim());
    }

    public void incrementCounter(View view){
        int counter = Integer.valueOf(itemCounter.getText().toString());
        switch (view.getId()){
            case R.id.addButton:
                counter++;
                itemCounter.setText(String.valueOf(counter));
                break;
            case R.id.subButton:
                if (counter != 1){
                    counter--;
                    itemCounter.setText(String.valueOf(counter));
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