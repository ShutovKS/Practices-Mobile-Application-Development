package ru.mirea.shutov.mobiletour.domain.models;

public class TourEntity {
    private String id;
    private String title;
    private String imageUrl;

    public TourEntity(String id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
