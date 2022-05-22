package com.example.wardrobe2022client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wardrobe2022client.R;
import com.example.wardrobe2022client.domain.Season;
import com.example.wardrobe2022client.nodb.NoDb;

import java.util.List;

public class SeasonSpinnerAdapter extends ArrayAdapter<Season> {
    public SeasonSpinnerAdapter(
            @NonNull Context context,
            @NonNull List<Season> objects) {
        super(context, R.layout.spinner_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.spinner_item, null);
        }

        ((TextView)convertView.findViewById(R.id.tv_spinner_item))
                .setText(NoDb.SEASON_LIST.get(position).getName());
        return convertView;


    }
}
