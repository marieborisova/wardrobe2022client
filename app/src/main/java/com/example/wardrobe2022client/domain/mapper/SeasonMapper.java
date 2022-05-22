package com.example.wardrobe2022client.domain.mapper;

import com.example.wardrobe2022client.domain.Season;

import org.json.JSONException;
import org.json.JSONObject;

public class SeasonMapper {
    public Season seasonFromClothingJsonArray(JSONObject jsonObject) {

        Season season = null;
        try {

            season = new Season(
                    jsonObject.getJSONObject("seasonDto").getInt("id"),
                    jsonObject.getJSONObject("seasonDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return season;
    }

    public Season seasonFromJsonArray(JSONObject jsonObject) {

        Season season = null;
        try {

            season = new Season(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return season;
    }
}


