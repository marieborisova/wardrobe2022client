package com.example.wardrobe2022client.domain.mapper;

import com.example.wardrobe2022client.domain.Sex;

import org.json.JSONException;
import org.json.JSONObject;

public class SexMapper {
    public Sex sexFromClothingJsonArray(JSONObject jsonObject) {

        Sex sex = null;
        try {

            sex = new Sex(
                    jsonObject.getJSONObject("sexDto").getInt("id"),
                    jsonObject.getJSONObject("sexDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return sex;
    }
    public Sex sexFromJsonArray(JSONObject jsonObject) {

        Sex sex = null;
        try {

            sex = new Sex(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return sex;
    }
}
