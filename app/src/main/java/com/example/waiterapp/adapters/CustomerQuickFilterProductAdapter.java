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

        switch (categoryDataList.get(position).getCategoryName()){
            case "Vegetables":
                holder.quickFilRecImg.setImageResource(R.drawable.imgv1);
                break;
            case "Fruits":
                holder.quickFilRecImg.setImageResource(R.drawable.fruits);
                break;
            case "Grains":
                holder.quickFilRecImg.setImageResource(R.drawable.grains);
                break;
            case "Meat and Poultry":
                holder.quickFilRecImg.setImageResource(R.drawable.meat);
                break;
            case "Seafood":
                holder.quickFilRecImg.setImageResource(R.drawable.seafood);
                break;
            case "Dairy foods":
                holder.quickFilRecImg.setImageResource(R.drawable.dairy);
                break;
            case "Eggs":
                holder.quickFilRecImg.setImageResource(R.drawable.eggs);
                break;
            case "Fast foods":
                holder.quickFilRecImg.setImageResource(R.drawable.burger);
                break;
            default:
                holder.quickFilRecImg.setImageResource(R.drawable.default_f);
                break;
        }
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