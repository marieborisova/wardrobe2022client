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
import com.example.wardrobe2022client.domain.Sex;
import com.example.wardrobe2022client.nodb.NoDb;

import java.util.List;

public class SexSpinnerAdapter extends ArrayAdapter<Sex> {
    public SexSpinnerAdapter(
            @NonNull Context context,
            @NonNull List<Sex> objects) {
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
                .setText(NoDb.SEX_LIST.get(position).getName());
        return convertView;


    }
}
