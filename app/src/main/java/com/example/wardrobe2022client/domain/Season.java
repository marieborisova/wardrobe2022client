package com.example.wardrobe2022client.domain;

import java.util.Objects;

public class Season { private int id;

    private String name;

    public Season(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Season(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return id == season.id && Objects.equals(name, season.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}

