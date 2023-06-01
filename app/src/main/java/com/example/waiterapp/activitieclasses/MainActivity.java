package com.example.waiterapp.activitieclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waiterapp.R;
import com.example.waiterapp.databaseclasses.RestaurantDatabaseHelper;
import com.example.waiterapp.dataclasses.ProductData;
import com.example.waiterapp.dataclasses.RestaurantData;
import com.example.waiterapp.helpers.AESCrypt;
import com.example.waiterapp.helpers.EmailVerify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // views ana viewGroup
    private RelativeLayout spinnerRel, loginRel,registerRel,tableNoRel;
    private Button loginButton, registerLaunchButton, registerButton,tableNoSkipButton, loginLaunchButton,menuLaunchButton;
    private EditText registerResName,registerResEmail,registerResPassword,registerConfResPassword,loginResEmail,loginResPassword,tableNumber;
    private TextView viewV;
    // Database
    private RestaurantDatabaseHelper restaurantDatabaseHelper;

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    private List<RestaurantData> restaurantDataList;

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
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
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
        menuLaunchButton = (Button) findViewById(R.id.menuLaunchButton);

        registerResName = (EditText) findViewById(R.id.registerResName);
        registerResEmail = (EditText) findViewById(R.id.registerResEmail);
        registerResPassword = (EditText) findViewById(R.id.registerResPassword);
        registerConfResPassword = (EditText) findViewById(R.id.registerConfResPassword);

        tableNumber = (EditText) findViewById(R.id.tableNumber);

        loginResEmail = (EditText) findViewById(R.id.loginResEmail);
        loginResPassword = (EditText) findViewById(R.id.loginResPassword);

        viewV = (TextView) findViewById(R.id.viewV);
        restaurantDatabaseHelper = new RestaurantDatabaseHelper(MainActivity.this);

        if (restaurantDatabaseHelper.isAnyRestaurantExist()){
            if (restaurantDatabaseHelper.isTableNumberSet()){
                launchMenuActivity();
            }
        }

        //show login
        handler.postDelayed(showLogin,3000);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
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
                registerRestaurant();
            }
        });

        loginLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginLayout();
            }
        });

        menuLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTableNumber();
            }
        });
    }

    public void setTableNumber(){
        String tableN = tableNumber.getText().toString().trim();

        if (tableN.isEmpty()){
            Toast.makeText(this, "Please enter tha table number", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int tN = Integer.valueOf(tableN);

            if (tN == 0){
                Toast.makeText(this, "Table Number cannot be zero", Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (Exception e){
            Toast.makeText(this, "Table Number cannot be decimal number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (restaurantDatabaseHelper.updateTableNumber(loginResEmail.getText().toString().trim(),Integer.valueOf(tableN))){
            Toast.makeText(this, "table number updated !", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,Menu.class);
            intent.putExtra("tableNumber", tableN);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Failed to update table number", Toast.LENGTH_SHORT).show();
            return;
        }

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

    public void launchTableNumberLayout(){
        loginRel.setVisibility(View.INVISIBLE);
        tableNoRel.setVisibility(View.VISIBLE);
    }


    public void loginUser(){
        String lEmail = loginResEmail.getText().toString().trim();
        String lPassword = loginResPassword.getText().toString().trim();

        if (lEmail.isEmpty()){
            Toast.makeText(this, "Please enter the email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (lPassword.isEmpty()){
            viewV.setText(lEmail);
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!EmailVerify.isEmailValid(lEmail)){
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return;
        }

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();

        restaurantDataList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurants");
        dialog.show();

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurantDataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    RestaurantData restaurantData = itemSnapshot.child("info").getValue(RestaurantData.class);

                    if (restaurantData.getRestaurantEmail().equals(lEmail) && restaurantData.getRestaurantPassword().equals(lPassword)){
                        restaurantDataList.add(restaurantData);
                    }
                }

                if (restaurantDataList.size() > 0){
                    Toast.makeText(MainActivity.this, "User exist", Toast.LENGTH_SHORT).show();

                    if (restaurantDatabaseHelper.isAnyRestaurantExist()){
                        launchTableNumberLayout();

                        //update database
                        if (restaurantDatabaseHelper.updateRestaurantData(restaurantDataList.get(0).getRestaurantName(),lEmail,lPassword)){
                            Toast.makeText(MainActivity.this, "db updated", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "failed to update db", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //insert data in db
                        if (restaurantDatabaseHelper.isRestaurantDataInserted(restaurantDataList.get(0).getRestaurantName(),lEmail,lPassword)){
                            Toast.makeText(MainActivity.this, "Data inserted id db", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Failed to insert data in db", Toast.LENGTH_SHORT).show();
                        }

                        launchTableNumberLayout();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

    }


    public void registerRestaurant(){
        String pass = registerResPassword.getText().toString().trim();
        String confPass = registerConfResPassword.getText().toString().trim();
        String resName = registerResName.getText().toString().trim();
        String resEmail = registerResEmail.getText().toString().trim();

        if (resName.isEmpty()){
            Toast.makeText(getApplicationContext(),"Restaurant name field is empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (resEmail.isEmpty()){
            Toast.makeText(getApplicationContext(),"Restaurant email field is empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Password field is empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (confPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Confirm Password field is empty!",Toast.LENGTH_SHORT).show();
            return;
        }


        if (!EmailVerify.isEmailValid(resEmail)){
            Toast.makeText(this, "Email not valid !", Toast.LENGTH_SHORT).show();
        }


        if (!pass.equals(confPass)){
            Toast.makeText(getApplicationContext(),"Passwords don't match!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length() < 8){
            Toast.makeText(this, "Password must be 8 digits character", Toast.LENGTH_SHORT).show();
        }

        RestaurantData restaurantData = new RestaurantData(resName,resEmail, pass);
        uploadData(restaurantData);
    }


    public void showLoginAfterRegistration(){
        registerRel.setVisibility(View.INVISIBLE);
        loginRel.setVisibility(View.VISIBLE);
    }

    public void uploadData(RestaurantData restaurantData){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        FirebaseDatabase.getInstance().getReference("Restaurants").child(restaurantData.getRestaurantName()).child("info")
                .setValue(restaurantData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()){
                            Toast.makeText(MainActivity.this, "Restaurant created successfully", Toast.LENGTH_SHORT).show();
                            boolean result = restaurantDatabaseHelper.isRestaurantDataInserted(restaurantData.getRestaurantName(),restaurantData.getRestaurantEmail(),restaurantData.getRestaurantPassword());

                            if (result){
                                Toast.makeText(MainActivity.this, "Data saved in SQLite db", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Failed to save in SQLite db", Toast.LENGTH_SHORT).show();
                            }
                            showLoginAfterRegistration();
                            
                            dialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}