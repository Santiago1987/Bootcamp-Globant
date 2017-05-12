package Model;


import Builder.LocationBuilder;

public class Location {

    private String city, country, region;
    private Atmosphere atm;
    private Astronomy astro;
    private Item it;


    public Location(LocationBuilder builder) {
        this.city = builder.getCity();
        this.country = builder.getCountry();
        this.region = builder.getRegion();
        this.atm = builder.getAtm();
        this.astro = builder.getAstro();
        this.it = builder.getIt();
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



    public Atmosphere getAtm() {
        return atm;
    }



    public Astronomy getAstro() {
        return astro;
    }



    public Item getIt() {
        return it;
    }












}
