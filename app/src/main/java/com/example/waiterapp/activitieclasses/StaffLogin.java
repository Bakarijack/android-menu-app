package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.waiterapp.R;

public class StaffLogin extends AppCompatActivity {

    private ImageButton menuBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        menuBackButton = (ImageButton) findViewById(R.id.menuBackButton);

        menuBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMenuAction();
            }
        });
    }


    public  void backToMenuAction(){
        Intent backToMenu = new Intent(this, Menu.class);
        startActivity(backToMenu);
        finish();
    }
}