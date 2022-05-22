package com.example.wardrobe2022client.rest;

import com.example.wardrobe2022client.domain.Clothing;

public interface WardrpobeApi {
    void fillClothing();
    void fillSeason();
    void fillSize();
    void fillSex();
    void addClothing(Clothing clothing);
    void updateClothing(
            int id,
            String newClothingName,
            String newSeasonName,
            String newSizeName,
            String newSexName
    );
    void deleteClothing(int id);
}

