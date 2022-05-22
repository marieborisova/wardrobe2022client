package com.example.wardrobe2022client.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wardrobe2022client.R;
import com.example.wardrobe2022client.domain.Clothing;
import com.example.wardrobe2022client.fragment.ChangeClothingFragment;
import com.example.wardrobe2022client.nodb.NoDb;

import java.util.List;

public class ClothingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String CLOTHING_KEY = "Clothing";
    private final Context context;
    private final LayoutInflater inflater;
    private final List<Clothing> clothingList;

    public ClothingAdapter(Context context, List<Clothing> clothingList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.clothingList = clothingList;
    }

    private class MyHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvSeason, tvSize, tvSex;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSeason = itemView.findViewById(R.id.tv_season_name);
            tvSize = itemView.findViewById(R.id.tv_size_name);
            tvSex = itemView.findViewById(R.id.sp_size);

        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_clothing_item, parent, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Clothing clothing = NoDb.CLOTHING_LIST.get(position);

        ((MyHolder)holder).tvName.setText(clothing.getName());
        ((MyHolder)holder).tvSeason.setText(clothing.getSeason().getName());
        ((MyHolder)holder).tvSize.setText(clothing.getSize().getName());
        ((MyHolder)holder).tvSex.setText(clothing.getSex().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeClothingFragment changeClothingFragment = new ChangeClothingFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(CLOTHING_KEY, clothing);
                changeClothingFragment.setArguments(bundle);
                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, changeClothingFragment)
                        .commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return NoDb.CLOTHING_LIST.size();
    }
}
