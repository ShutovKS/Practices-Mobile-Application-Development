package ru.mirea.shutov.Lession1.domain.models;

public class Movie {
    private int id;
    private String name;

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
}