package com.example.waiterapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waiterapp.R;
import com.example.waiterapp.activitieclasses.ItemDetail;
import com.example.waiterapp.dataclasses.ProductData;

import java.util.List;

public class CustomerProductAdapter extends RecyclerView.Adapter<MyCustomerProductViewHolder> {
    private Context context;
    private List<ProductData> productDataList;

    public CustomerProductAdapter(Context context, List<ProductData> productDataList) {
        this.context = context;
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public MyCustomerProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler_item,parent,false);

        return new MyCustomerProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomerProductViewHolder holder, int position) {
        Glide.with(context).load(productDataList.get(position).getProductImageUri()).into(holder.customerRecCardProductsImg);
        holder.customerRecProductName.setText(productDataList.get(position).getProductName());
        holder.customerRecProductPrice.setText(productDataList.get(position).getProductPrice());

        holder.customerRecCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemDetail.class);
                intent.putExtra("pImage",productDataList.get(holder.getAdapterPosition()).getProductImageUri());
                intent.putExtra("pName", productDataList.get(holder.getAdapterPosition()).getProductName());
                intent.putExtra("pCategory", productDataList.get(holder.getAdapterPosition()).getProductCategory());
                intent.putExtra("pPrice", productDataList.get(holder.getAdapterPosition()).getProductPrice());
                intent.putExtra("pDescription",productDataList.get(holder.getAdapterPosition()).getProductDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }
}


class MyCustomerProductViewHolder extends RecyclerView.ViewHolder{
    ImageView customerRecCardProductsImg;
    TextView customerRecProductName,customerRecProductPrice;
    CardView customerRecCard;

    public MyCustomerProductViewHolder(@NonNull View itemView) {
        super(itemView);
        customerRecCardProductsImg = itemView.findViewById(R.id.customerRecCardProductsImg);
        customerRecProductName = itemView.findViewById(R.id.customerRecProductName);
        customerRecProductPrice = itemView.findViewById(R.id.customerRecProductPrice);
        customerRecCard = itemView.findViewById(R.id.customerRecCard);
    }
}