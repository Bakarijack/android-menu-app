package com.example.waiterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waiterapp.R;
import com.example.waiterapp.dataclasses.ProductData;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<MyProductViewHolder> {

    private Context context;
    private List<ProductData> productDataList;

    public ProductsAdapter(Context context, List<ProductData> productDataList) {
        this.context = context;
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public MyProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new MyProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductViewHolder holder, int position) {
        Glide.with(context).load(productDataList.get(position).getProductImageUri()).into(holder.cardProductsImg);
        holder.recProductName.setText(productDataList.get(position).getProductName());
        holder.recProductCount.setText(String.valueOf(productDataList.get(position).getProductQuantity()));

    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }
}

class MyProductViewHolder extends RecyclerView.ViewHolder{
    ImageView cardProductsImg;
    TextView recProductName,recProductCount;
    CardView recProductCard;
    ImageButton recProductAdd;

    public MyProductViewHolder(@NonNull View itemView) {
        super(itemView);
        cardProductsImg = itemView.findViewById(R.id.cardProductsImg);
        recProductName = itemView.findViewById(R.id.recProductName);
        recProductCount = itemView.findViewById(R.id.recProductCount);
        recProductCard = itemView.findViewById(R.id.recProductCard);
        recProductAdd = itemView.findViewById(R.id.recProductAdd);
    }
}