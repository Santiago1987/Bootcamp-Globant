package com.SRA.weather.Model;


import com.SRA.weather.Builder.LocationBuilder;

public class Location {
    private String city, country, region, actualdate;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Item item;


    public Location() {
    }

    public Location(String city, String country, String region, String actualdate, Atmosphere atmosphere, Astronomy astronomy, Item item) {
        this.city = city;
        this.country = country;
        this.region = region;
        this.actualdate = actualdate;
        this.atmosphere = atmosphere;
        this.astronomy = astronomy;
        this.item = item;
    }

    public Location(LocationBuilder builder) {

        this.city = builder.getCity();
        this.country = builder.getCountry();
        this.region = builder.getRegion();
        this.atmosphere = builder.getAtmosphere();
        this.astronomy = builder.getAstronomy();
        this.item = builder.getItem();
        this.actualdate = builder.getActualdate();
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
