package com.example.wardrobe2022client.rest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wardrobe2022client.MainActivity;
import com.example.wardrobe2022client.domain.Clothing;
import com.example.wardrobe2022client.domain.Season;
import com.example.wardrobe2022client.domain.Sex;
import com.example.wardrobe2022client.domain.Size;
import com.example.wardrobe2022client.domain.mapper.ClothingMapper;
import com.example.wardrobe2022client.domain.mapper.SeasonMapper;
import com.example.wardrobe2022client.domain.mapper.SexMapper;
import com.example.wardrobe2022client.domain.mapper.SizeMapper;
import com.example.wardrobe2022client.nodb.NoDb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WardrobeApiVolley implements WardrpobeApi{
    public static final String BASE_URL = "http://10.0.2.2:8081";
    private final Context context;
    public static final String API_TEST = "API_TEST";
    private Response.ErrorListener errorListener;

    public WardrobeApiVolley(Context context) {
        this.context = context;
        errorListener = error -> Log.d(API_TEST, error.toString());
    }

    @Override
    public void fillClothing() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/clothing";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        NoDb.CLOTHING_LIST.clear();
                        try {


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                Season season = new SeasonMapper().seasonFromClothingJsonArray(jsonObject);

                                Size size = new SizeMapper().sizeFromClothingJsonArray(jsonObject);

                                Sex sex = new SexMapper().sexFromClothingJsonArray(jsonObject);

                                Clothing clothing = new ClothingMapper().clothingFromJsonArray(jsonObject, season, size, sex);
                                NoDb.CLOTHING_LIST.add(clothing);
                            }
                            Log.d(API_TEST, NoDb.CLOTHING_LIST.toString());
                            ((MainActivity) context).updateAdapter();
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }

    @Override
    public void fillSeason() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/season";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        NoDb.SEASON_LIST.clear();
                        try {


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                //Genre genre = new GenreMapper().genreFromBookJsonArray(jsonObject);

                                Season season = new SeasonMapper().seasonFromJsonArray(jsonObject);

                                //Book book = new BookMapper().bookFromJsonArray(jsonObject, author, genre);
                                NoDb.SEASON_LIST.add(season);
                            }
                            Log.d(API_TEST, NoDb.SEASON_LIST.toString());
                            //  ((MainActivity) context).updateAdapter();
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }



    @Override
    public void fillSize() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/size";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        NoDb.SIZE_LIST.clear();
                        try {


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                //Genre genre = new GenreMapper().genreFromBookJsonArray(jsonObject);

                                Size size = new SizeMapper().sizeFromJsonArray(jsonObject);

                                //Book book = new BookMapper().bookFromJsonArray(jsonObject, author, genre);
                                NoDb.SIZE_LIST.add(size);
                            }
                            Log.d(API_TEST, NoDb.SIZE_LIST.toString());
                            //  ((MainActivity) context).updateAdapter();
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }


    @Override
    public void fillSex() {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/sex";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        NoDb.SEX_LIST.clear();
                        try {


                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);

                                //Genre genre = new GenreMapper().genreFromBookJsonArray(jsonObject);

                                Sex sex = new SexMapper().sexFromJsonArray(jsonObject);

                                //Book book = new BookMapper().bookFromJsonArray(jsonObject, author, genre);
                                NoDb.SEX_LIST.add(sex);
                            }
                            Log.d(API_TEST, NoDb.SEX_LIST.toString());
                            //  ((MainActivity) context).updateAdapter();
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                },

                errorListener
        );

        queue.add(jsonArrayRequest);
    }


    @Override
    public void addClothing(Clothing clothing) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/clothing";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillClothing();
                        Log.d(API_TEST, response);


                    }
                }, errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("nameClothing", clothing.getName());
                params.put("nameSeason", clothing.getSeason().getName());
                params.put("nameSize", clothing.getSize().getName());
                params.put("nameSex", clothing.getSex().getName());
                return params;
            }
        };
        queue.add(request);
    }


    @Override
    public void updateClothing(int id, String newClothingName, String newSeasonName, String newSizeName, String newSexName) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/clothing/" + id;
        StringRequest request = new StringRequest(
                Request.Method.PUT,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillClothing();
                        Log.d(API_TEST, response);


                    }
                }, errorListener
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("nameClothing", newClothingName);
                params.put("nameSeason", newSeasonName);
                params.put("nameSize", newSizeName);
                params.put("nameSex", newSexName);
                return params;
            }
        };
        queue.add(request);
    }


    @Override
    public void deleteClothing(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/clothing" + "/" + id;
        StringRequest request = new StringRequest(
                Request.Method.DELETE,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        fillClothing();
                        Log.d(API_TEST, response);


                    }
                }, errorListener
        );
        queue.add(request);
    }
}