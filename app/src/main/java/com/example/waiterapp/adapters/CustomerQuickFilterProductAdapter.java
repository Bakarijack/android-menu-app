package com.example.waiterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiterapp.R;
import com.example.waiterapp.dataclasses.CategoryData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerQuickFilterProductAdapter extends RecyclerView.Adapter<MyQuickFilterViewHolder> {
    private Context context;
    private List<CategoryData> categoryDataList;


    public CustomerQuickFilterProductAdapter(Context context, List<CategoryData> categoryDataList) {
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public MyQuickFilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_filter_recycler_item,parent,false);

        return new MyQuickFilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyQuickFilterViewHolder holder, int position) {
        holder.quickFilRecTitle.setText(categoryDataList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }
}


class MyQuickFilterViewHolder extends RecyclerView.ViewHolder{
    CardView quickFilRec;
    CircleImageView quickFilRecImg;
    TextView quickFilRecTitle;

    public MyQuickFilterViewHolder(@NonNull View itemView) {
        super(itemView);
        quickFilRec = itemView.findViewById(R.id.quickFilRec);
        quickFilRecImg = itemView.findViewById(R.id.quickFilRecImg);
        quickFilRecTitle = itemView.findViewById(R.id.quickFilRecTitle);
    }
}