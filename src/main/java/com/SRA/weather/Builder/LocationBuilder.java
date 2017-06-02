package com.SRA.weather.Builder;


import com.SRA.weather.Model.Astronomy;
import com.SRA.weather.Model.Atmosphere;
import com.SRA.weather.Model.Item;
import com.SRA.weather.Model.Location;

public class LocationBuilder {
    private String city = "", country = "", region = "", actualdate = "";
    private Atmosphere atmosphere = null;
    private Astronomy astronomy = null;
    private Item item = null;

    public LocationBuilder() {
    }

    public LocationBuilder actualdate(String actualdate){
        this.actualdate=actualdate;
        return this;
    }

    public LocationBuilder city(String city){
        this.city = city;
        return this;
    }

    public LocationBuilder country(String country){
        this.country = country;
        return this;
    }

    public LocationBuilder region(String region){
        this.region = region;
        return this;
    }

    public LocationBuilder atmosphere(Atmosphere atmosphere){
        this.atmosphere = atmosphere;
        return this;
    }

    public LocationBuilder astronomy(Astronomy astronomy){
        this.astronomy = astronomy;
        return this;
    }

    public LocationBuilder item(Item item){
        this.item = item;
        return this;
    }

    public Location build(){
        return  new Location(this);
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public String getActualdate() {
        return actualdate;
    }

    public Item getItem() {
        return item;
    }
}
