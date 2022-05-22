package com.example.wardrobe2022client.domain;

import java.io.Serializable;
import java.util.List;

public class Clothing implements Serializable {

    private int id;

    private String name;

    private Season season;

    private Size size;
    private Sex sex;




    public Clothing(int id, String name, Season season, Size size, Sex sex) {
        this.id = id;
        this.name = name;
        this.season = season;
        this.size = size;
        this.sex = sex;

    }
    public Clothing(String name, Season season, Size size, Sex sex) {

        this.name = name;
        this.season = season;
        this.size = size;
        this.sex = sex;


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