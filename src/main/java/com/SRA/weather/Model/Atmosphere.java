package com.SRA.weather.Model;


import com.SRA.weather.Builder.AtmosphereBuilder;

public class Atmosphere {
    private int humidity;
    private float pressure;
    private float visibility;

    public Atmosphere(int humidity, float pressure, float visibility) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
    }

    public Atmosphere(AtmosphereBuilder builder) {
        this.humidity = builder.getHumidity();
        this.pressure = builder.getPressure();
        this.visibility = builder.getVisibility();
    }

    public Atmosphere() {
    }

    public int getHumidity() {
        return humidity;
    }



    public float getPressure() {
        return pressure;
    }



    public float getVisibility() {
        return visibility;
    }
}
