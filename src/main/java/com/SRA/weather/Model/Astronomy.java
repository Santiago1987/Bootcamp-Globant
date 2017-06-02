package com.SRA.weather.Model;


import com.SRA.weather.Builder.AstronomyBuilder;

public class Astronomy {

    private String sunrise, sunset;

    public Astronomy() {
    }

    public Astronomy(String sunrise, String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Astronomy(AstronomyBuilder builder){
        this.sunrise = builder.getSunrise();
        this.sunset = builder.getSunset();
    }


    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
