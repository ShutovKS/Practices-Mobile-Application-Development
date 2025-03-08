package ru.mirea.shutov.data.storage.models;

public class MovieData {
    private int id;
    private String name;
    private String localDate; 

    public MovieData(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.localDate = date;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocalDate() { return localDate; }
}

