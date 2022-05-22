package com.example.wardrobe2022client.domain.mapper;

import com.example.wardrobe2022client.domain.Size;

import org.json.JSONException;
import org.json.JSONObject;

public class SizeMapper {
    public Size sizeFromClothingJsonArray(JSONObject jsonObject) {

        Size size = null;
        try {

            size = new Size(
                    jsonObject.getJSONObject("sizeDto").getInt("id"),
                    jsonObject.getJSONObject("sizeDto").getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return size;
    }
    public Size sizeFromJsonArray(JSONObject jsonObject) {

        Size size = null;
        try {

            size = new Size(
                    jsonObject.getInt("id"),
                    jsonObject.getString("name")
            );
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return size;
    }
}


