package com.example.waiterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiterapp.R;
import com.example.waiterapp.dataclasses.CartItems;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartItemsAdapter extends RecyclerView.Adapter<MyCartItemsViewHolder> {
    private Context context;
    private List<CartItems> cartItemsList;


    public CartItemsAdapter(Context context, List<CartItems> cartItemsList) {
        this.context = context;
        this.cartItemsList = cartItemsList;
    }

    @NonNull
    @Override
    public MyCartItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_recycler_item,parent,false);

        return new MyCartItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartItemsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }
}


class MyCartItemsViewHolder extends RecyclerView.ViewHolder{
    CardView cartItemRec;
    CircleImageView cartItemImageRec;
    TextView itemQuantityRec,itemPriceRec,itemTotalPriceRec;
    ImageButton cartItemDeleteRec;
    Button addBtnRec,subBtnRec;
    public MyCartItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        subBtnRec = itemView.findViewById(R.id.subBtnRec);
        addBtnRec = itemView.findViewById(R.id.addBtnRec);
        cartItemDeleteRec = itemView.findViewById(R.id.cartItemDeleteRec);
        itemTotalPriceRec = itemView.findViewById(R.id.itemTotalPriceRec);
        itemQuantityRec = itemView.findViewById(R.id.itemQuantityRec);
        itemPriceRec = itemView.findViewById(R.id.itemPriceRec);
        cartItemImageRec  = itemView.findViewById(R.id.cartItemImageRec);
        cartItemRec = itemView.findViewById(R.id.cartItemRec);
    }
}