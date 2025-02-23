package ru.mirea.shutov.mobiletour.domain.models;

public class ExternalData {
    private String weather;
    private String temperature;

    public ExternalData(String weather, String temperature) {
        this.weather = weather;
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getTemperature() {
        return temperature;
    }
}
