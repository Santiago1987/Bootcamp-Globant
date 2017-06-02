package com.SRA.weather.Builder;

import com.SRA.weather.Model.Item;

public class ItemBuilder {
    private String title = "", actualday= "", text= "";
    private int temp = 0;

    public ItemBuilder() {
    }

    public ItemBuilder title(String title){
        this.title = title;
        return this;
    }

    public ItemBuilder actualday(String actualday){
        this.actualday = actualday;
        return this;
    }

    public ItemBuilder text(String text){
        this.text = text;
        return this;
    }


    public Item build(){
        return new Item(this);
    }

    public ItemBuilder temp(int temp){
        this.temp = temp;
        return this;
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
}
