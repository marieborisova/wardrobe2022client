package com.example.wardrobe2022client.nodb;

import com.example.wardrobe2022client.domain.Clothing;
import com.example.wardrobe2022client.domain.Season;
import com.example.wardrobe2022client.domain.Sex;
import com.example.wardrobe2022client.domain.Size;

import java.util.ArrayList;
import java.util.List;

public class NoDb {
    private NoDb(){}
    public static final List<Clothing> CLOTHING_LIST = new ArrayList<>();

    public static final List<Season> SEASON_LIST = new ArrayList<>();

    public static final List<Size> SIZE_LIST = new ArrayList<>();
    public static final List<Sex> SEX_LIST = new ArrayList<>();
}

