package com.example.wardrobe2022client.domain;

import java.io.Serializable;
import java.util.List;

public class Clothing implements Serializable {

    private int id;

    private String name;

    private Season season;

    private Size size;




    public Clothing(int id, String name, Season season, Size size) {
        this.id = id;
        this.name = name;
        this.season = season;
        this.size = size;

    }
    public Clothing(String name, Season season, Size size) {

        this.name = name;
        this.season = season;
        this.size = size;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }

    public Size getSize() {
        return size;
    }


    @Override
    public String toString() {
        return "Clothing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", season=" + season +
                ", size=" + size +
                '}';
    }
}