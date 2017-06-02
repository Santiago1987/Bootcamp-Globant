package com.SRA.weather.Model;


import com.SRA.weather.Builder.ForecastBuilder;

public class Forecast {
    private String date, day, text;
    private float high, low;

    public Forecast(String date, String day, String text, float high, float low) {
        this.date = date;
        this.day = day;
        this.text = text;
        this.high = high;
        this.low = low;
    }

    public Forecast() {
    }

    public Forecast(ForecastBuilder builder) {
        this.date = builder.getDate();

        this.day = builder.getDay();
        this.text = builder.getText();
        this.high = builder.getHigh();
        this.low = builder.getLow();
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getText() {
        return text;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public void setLow(float low) {
        this.low = low;
    }
}
