package com.example.waiterapp.activitieclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.waiterapp.R;

public class ItemDetail extends AppCompatActivity {
    private ImageView itemImg;
    private Button subButton,addButton,orderListLaunchButton;
    private TextView itemCounter,itemName,itemDesc,itemPrice;

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

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            Glide.with(this).load(bundle.getString("pImage")).into(itemImg);
            itemName.setText(bundle.getString("pName"));
            itemDesc.setText(bundle.getString("pDescription"));
            itemPrice.setText(bundle.getString("pPrice"));

        }

        orderListLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pending
            }
        });


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
}