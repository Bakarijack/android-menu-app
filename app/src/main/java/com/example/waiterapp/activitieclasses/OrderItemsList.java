package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.waiterapp.R;

public class OrderItemsList extends AppCompatActivity {

    private ImageButton menuLaunchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items_list);

        menuLaunchButton = (ImageButton) findViewById(R.id.menuLaunchButton);

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
}