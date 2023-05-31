package com.example.waiterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waiterapp.R;
import com.example.waiterapp.dataclasses.ProductData;

import java.util.List;

public class CustomerSeasonProductAdapter extends RecyclerView.Adapter<MyCustomerSeasonProductViewHOlder> {
    private Context context;
    private List<ProductData> productDataList;

    public CustomerSeasonProductAdapter(Context context, List<ProductData> productDataList) {
        this.context = context;
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public MyCustomerSeasonProductViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_recycler_item,parent,false);

        return new MyCustomerSeasonProductViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomerSeasonProductViewHOlder holder, int position) {
        Glide.with(context).load(productDataList.get(position).getProductImageUri()).into(holder.customerSeasonRecCardProductsImg);
        holder.customerSeasonRecProductName.setText(productDataList.get(position).getProductName());
        holder.customerSeasonRecProductPrice.setText(productDataList.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }
}


class MyCustomerSeasonProductViewHOlder extends RecyclerView.ViewHolder{
    ImageView customerSeasonRecCardProductsImg;
    TextView customerSeasonRecProductName,customerSeasonRecProductPrice;

    public MyCustomerSeasonProductViewHOlder(@NonNull View itemView) {
        super(itemView);
        customerSeasonRecCardProductsImg = itemView.findViewById(R.id.customerSeasonRecCardProductsImg);
        customerSeasonRecProductName = itemView.findViewById(R.id.customerSeasonRecProductName);
        customerSeasonRecProductPrice = itemView.findViewById(R.id.customerSeasonRecProductPrice);

    }
}