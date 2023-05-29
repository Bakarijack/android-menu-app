package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.waiterapp.R;

public class Menu extends AppCompatActivity {
    private ImageButton menuButton,cartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuButton = (ImageButton) findViewById(R.id.menuButton);
        cartButton = (ImageButton) findViewById(R.id.cartButton);

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