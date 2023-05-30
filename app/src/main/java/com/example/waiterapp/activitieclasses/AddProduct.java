package com.example.waiterapp.activitieclasses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.waiterapp.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddProduct extends AppCompatActivity {
    private FloatingActionButton photoFab;
    private CircleImageView foodImage;
    private EditText foodName,foodPrice,foodQuantity,foodCategory,foodDesc;
    private Spinner categorySpinner;
    private Button createButton;


    private Uri uri;

    private String[] initialCategoryItems = {"Vegetables","Fruits","Grains","Meat and Poultry","Seafood","Dairy foods","Eggs","Fast foods"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        photoFab = (FloatingActionButton) findViewById(R.id.photoFab);
        foodImage = (CircleImageView) findViewById(R.id.foodImage);
        foodName = (EditText) findViewById(R.id.foodName);
        foodPrice = (EditText) findViewById(R.id.foodPrice);
        foodQuantity = (EditText) findViewById(R.id.foodQuantity);
        foodCategory = (EditText) findViewById(R.id.foodCategory);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        foodDesc = (EditText) findViewById(R.id.foodDesc);
        createButton = (Button) findViewById(R.id.createButton);


        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,initialCategoryItems);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(ad);

        photoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePickerAction();
            }
        });
    }

    public void imagePickerAction(){
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        foodImage.setImageURI(uri);
    }
}