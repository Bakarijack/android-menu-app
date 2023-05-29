package com.example.waiterapp.activitieclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.waiterapp.R;
import com.example.waiterapp.fragments.staffhome.HomeFragment;
import com.example.waiterapp.fragments.staffhome.ListsFragment;
import com.example.waiterapp.fragments.staffhome.RestaurantFragment;
import com.example.waiterapp.fragments.staffhome.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StaffHomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private ImageButton staffHomeButton;
    private BottomNavigationView homeNavigation;
    private HomeFragment homeFragment = new HomeFragment();
    private RestaurantFragment restaurantFragment = new RestaurantFragment();
    private ListsFragment listsFragment = new ListsFragment();
    private TransactionFragment transactionFragment = new TransactionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home_page);

        staffHomeButton = (ImageButton) findViewById(R.id.staffHomeButton);
        homeNavigation = (BottomNavigationView) findViewById(R.id.homeNavigation);

        homeNavigation.setOnNavigationItemSelectedListener(this);
        homeNavigation.setSelectedItemId(R.id.home);
        getSupportFragmentManager().beginTransaction().replace( R.id.container, homeFragment).commit();

        staffHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleHomeNavAction();
            }
        });
    }


    public void handleHomeNavAction(){
        PopupMenu popupMenu = new PopupMenu(StaffHomePage.this,staffHomeButton);

        popupMenu.getMenuInflater().inflate(R.menu.staff_home_top_nav,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.orders:
                        Toast.makeText(StaffHomePage.this, "Orders clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.foods:
                        Toast.makeText(StaffHomePage.this, "Foods clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tables:
                        Toast.makeText(StaffHomePage.this, "Tables clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(StaffHomePage.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace( R.id.container, homeFragment).commit();
                return true;

            case R.id.restaurant:
                getSupportFragmentManager().beginTransaction().replace( R.id.container,restaurantFragment).commit();
                return true;
            case R.id.list:
                getSupportFragmentManager().beginTransaction().replace( R.id.container,listsFragment).commit();
                return true;
            case R.id.money:
                getSupportFragmentManager().beginTransaction().replace( R.id.container,transactionFragment).commit();
                return true;
        }
        return false;
    }


}