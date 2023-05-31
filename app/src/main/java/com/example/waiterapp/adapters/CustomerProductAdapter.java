package com.example.waiterapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiterapp.R;
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomerProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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