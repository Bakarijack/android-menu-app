package com.example.waiterapp.fragments.staffhome;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.waiterapp.R;
import com.example.waiterapp.activitieclasses.RestaurantProducts;


public class RestaurantFragment extends Fragment {
    private RelativeLayout productRel;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        productRel = view.findViewById(R.id.productRel);

        productRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProductsPage();
            }
        });




        return view;
    }

    public void launchProductsPage(){
        Intent intent = new Intent(getContext(), RestaurantProducts.class);
        startActivity(intent);
    }
}