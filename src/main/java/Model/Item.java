package Model;


import Builder.ItemBuilder;

import java.util.ArrayList;

public class Item {
    private String title, actualday, text;
    private int temp;
    private ArrayList<Forecast> fore = new ArrayList<Forecast>();


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



    public ArrayList<Forecast> getFore() {
        return fore;
    }
}
