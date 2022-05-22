package com.example.wardrobe2022client.domain.mapper;

import com.example.wardrobe2022client.domain.Clothing;
import com.example.wardrobe2022client.domain.Season;
import com.example.wardrobe2022client.domain.Sex;
import com.example.wardrobe2022client.domain.Size;

import org.json.JSONException;
import org.json.JSONObject;

public class ClothingMapper {

    public Clothing bookFromJsonArray(JSONObject jsonObject, Season season, Size size, Sex sex) {

        Clothing clothing = null;

        try {
            clothing = new Clothing(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name"),
                    season,
                    size,
                    sex
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clothing;
    }

}

