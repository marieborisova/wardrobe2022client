package com.example.wardrobe2022client.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.example.wardrobe2022client.R;
import com.example.wardrobe2022client.adapter.SeasonSpinnerAdapter;
import com.example.wardrobe2022client.adapter.SexSpinnerAdapter;
import com.example.wardrobe2022client.adapter.SizeSpinnerAdapter;
import com.example.wardrobe2022client.domain.Clothing;
import com.example.wardrobe2022client.domain.Season;
import com.example.wardrobe2022client.domain.Sex;
import com.example.wardrobe2022client.domain.Size;
import com.example.wardrobe2022client.nodb.NoDb;
import com.example.wardrobe2022client.rest.WardrobeApiVolley;

public class AddClothingFragment extends Fragment {

    private AppCompatSpinner spSeason, spSize, spSex;
    private SeasonSpinnerAdapter seasonSpinnerAdapter;
    private SizeSpinnerAdapter sizeSpinnerAdapter;
    private SexSpinnerAdapter sexSpinnerAdapter;

    private EditText etClothingName;
    private AppCompatButton btnAdd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_clothing, container, false);

        spSeason = view.findViewById(R.id.sp_season);
        spSize = view.findViewById(R.id.sp_size);
        spSex = view.findViewById(R.id.sp_sex);
        btnAdd = view.findViewById(R.id.btn_add);
        etClothingName = view.findViewById(R.id.et_clothing_name);
        seasonSpinnerAdapter = new SeasonSpinnerAdapter(getContext(), NoDb.SEASON_LIST);
        sizeSpinnerAdapter = new SizeSpinnerAdapter(getContext(), NoDb.SIZE_LIST);
        sexSpinnerAdapter = new SexSpinnerAdapter(getContext(), NoDb.SEX_LIST);

        spSeason.setAdapter(seasonSpinnerAdapter);
        spSize.setAdapter(sizeSpinnerAdapter);
        spSex.setAdapter(sexSpinnerAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WardrobeApiVolley(getContext()).addClothing(
                        new Clothing(
                                etClothingName.getText().toString(),
                                ((Season) spSeason.getSelectedItem()),
                                ((Size) spSize.getSelectedItem()),
                                ((Sex) spSex.getSelectedItem())
                        )

                );
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .remove(AddClothingFragment.this)
                        .commit();
            }
        });


        return view;
    }
}