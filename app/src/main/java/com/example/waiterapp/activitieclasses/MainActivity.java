package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.waiterapp.R;
import com.example.waiterapp.databaseclasses.RestaurantDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    // views ana viewGroup
    private RelativeLayout spinnerRel, loginRel,registerRel,tableNoRel;
    private Button loginButton, registerLaunchButton, registerButton,tableNoSkipButton, loginLaunchButton,menuLaunchButton;


    // Database
    private RestaurantDatabaseHelper restaurantDatabaseHelper;

    private Handler handler = new Handler();

    private Runnable showLogin = new Runnable() {
        @Override
        public void run() {
            spinnerRel.setVisibility(View.INVISIBLE);
            loginRel.setVisibility(View.VISIBLE);
        }
    };

    private Runnable showMenu =  new Runnable() {
        @Override
        public void run() {
            spinnerRel.setVisibility(View.INVISIBLE);
        }
    };


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //create a full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        spinnerRel = (RelativeLayout) findViewById(R.id.spinnerRel);
        loginRel = (RelativeLayout) findViewById(R.id.loginRel);
        registerRel = (RelativeLayout) findViewById(R.id.registerRel);
        tableNoRel = (RelativeLayout) findViewById(R.id.tableNoRel);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerLaunchButton = (Button) findViewById(R.id.registerLaunchButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        loginLaunchButton = (Button) findViewById(R.id.loginLaunchButton);
        tableNoSkipButton = (Button) findViewById(R.id.tableNoSkipButton);
        menuLaunchButton = (Button) findViewById(R.id.menuLaunchButton);

        restaurantDatabaseHelper = new RestaurantDatabaseHelper(MainActivity.this);

        if (restaurantDatabaseHelper.isAnyRestaurantExist()){
            launchMenuActivity();
        }

        //show login
        handler.postDelayed(showLogin,3000);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pending
            }
        });

        registerLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterLayout();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pending
            }
        });

        loginLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginLayout();
            }
        });

        tableNoSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMenuActivity();
            }
        });

        menuLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMenuActivity();
            }
        });
    }

    public void launchRegisterLayout(){
        loginRel.setVisibility(View.INVISIBLE);
        registerRel.setVisibility(View.VISIBLE);
    }

    public  void launchLoginLayout(){
        registerRel.setVisibility(View.INVISIBLE);
        loginRel.setVisibility(View.VISIBLE);
    }

    public void launchMenuActivity(){
        Intent menuIntent = new Intent(MainActivity.this, Menu.class);
        startActivity(menuIntent);
        finish();
    }
}