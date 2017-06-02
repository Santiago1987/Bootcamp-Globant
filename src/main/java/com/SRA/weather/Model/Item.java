package com.SRA.weather.Model;


import com.SRA.weather.Builder.ItemBuilder;

import java.util.ArrayList;

public class Item {
    private String title, actualday, text;
    private int temp;
    private ArrayList<Forecast> forecast = new ArrayList<Forecast>();

    public Item() {
    }

    public Item(String title, String actualday, String text, int temp, ArrayList<Forecast> fore) {
        this.title = title;
        this.actualday = actualday;
        this.text = text;
        this.temp = temp;
        this.forecast = fore;
    }

    public Item(ItemBuilder builder) {
        this.title = builder.getTitle();
        this.actualday = builder.getActualday();
        this.text = builder.getText();
        this.temp = builder.getTemp();

    }

    public String getTitle() {
        return title;
    }

    public String getActualday() {
        return actualday;
    }

    public String getText() {
        return text;
    }

    public int getTemp() {
        return temp;
    }

    public ArrayList<Forecast> getForecast() {
        return forecast;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setFore(ArrayList<Forecast> fore) {
        this.forecast = fore;
    }
}
