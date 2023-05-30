package com.example.waiterapp.activitieclasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.waiterapp.R;
import com.example.waiterapp.databaseclasses.FoodCategoryDatabaseHelper;
import com.example.waiterapp.databaseclasses.ProductDatabaseHelper;
import com.example.waiterapp.databaseclasses.RestaurantDatabaseHelper;
import com.example.waiterapp.dataclasses.FoodCategoryData;
import com.example.waiterapp.dataclasses.ProductData;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddProduct extends AppCompatActivity {
    private FloatingActionButton photoFab;
    private CircleImageView foodImage;
    private EditText foodName,foodPrice,foodQuantity,foodDesc;
    private Spinner categorySpinner;
    private Button createButton;

    //database
    private FoodCategoryDatabaseHelper foodCategoryDatabaseHelper;
    private RestaurantDatabaseHelper restaurantDatabaseHelper;
    private ProductDatabaseHelper productDatabaseHelper;

    private String fname,fprice,fquantity,fcategory,fimageUri,fdescription,fcustomcategory;


    private Uri uri;

    private String[] initialCategoryItems = {"Vegetables","Fruits","Grains","Meat and Poultry","Seafood","Dairy foods","Eggs","Fast foods"};
    private String spinnerItem;


    private String imageUri;
    private Cursor cursor;
    private ArrayList<String> dbFoodCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        photoFab = (FloatingActionButton) findViewById(R.id.photoFab);
        foodImage = (CircleImageView) findViewById(R.id.foodImage);
        foodName = (EditText) findViewById(R.id.foodName);
        foodPrice = (EditText) findViewById(R.id.foodPrice);
        foodQuantity = (EditText) findViewById(R.id.foodQuantity);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        foodDesc = (EditText) findViewById(R.id.foodDesc);
        createButton = (Button) findViewById(R.id.createButton);

        foodCategoryDatabaseHelper = new FoodCategoryDatabaseHelper(AddProduct.this);
        restaurantDatabaseHelper =  new RestaurantDatabaseHelper(AddProduct.this);
        productDatabaseHelper = new ProductDatabaseHelper(AddProduct.this);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,initialCategoryItems);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(ad);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerItem = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        photoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePickerAction();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData(){
        fname = foodName.getText().toString().trim();
        fprice = foodPrice.getText().toString().trim();
        fquantity = foodQuantity.getText().toString().trim();
        fdescription = foodDesc.getText().toString().trim();

        if (uri == null){
            Toast.makeText(this, "Please select food image", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fname.isEmpty()){
            Toast.makeText(this, "Please enter food name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fprice.isEmpty()){
            Toast.makeText(this, "Please enter food price", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fquantity.isEmpty()){
            Toast.makeText(this, "Please enter food quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fdescription.isEmpty()){
            Toast.makeText(this, "Please enter food description", Toast.LENGTH_SHORT).show();
            return;
        }


        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Restaurant Images")
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(AddProduct.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uriImage = uriTask.getResult();
                imageUri = uriImage.toString();

                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public  void uploadData(){
        ProductData productData = new ProductData(fname,fprice,Integer.valueOf(fquantity),spinnerItem,fdescription,imageUri);

        FirebaseDatabase.getInstance().getReference("Restaurants").child("Mimo Cafe").child("Products").child(productData.getProductName())
                .setValue(productData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()){
                            Toast.makeText(AddProduct.this, "Created!", Toast.LENGTH_SHORT).show();

                            //save in database
                            boolean result = productDatabaseHelper.isProductInserted(fname,fprice,Integer.valueOf(fquantity),spinnerItem,fdescription,foodImage);
                            if (!result){
                                Toast.makeText(AddProduct.this, "Failed to save i the db", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(AddProduct.this, "saved in the db", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddProduct.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void imagePickerAction(){
        ImagePicker.with(this)
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