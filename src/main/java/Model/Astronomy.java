package Model;


import Builder.AstronomyBuilder;

public class Astronomy {

    private String sunrise, sunset;

    public Astronomy(AstronomyBuilder builder){
        this.sunrise = builder.getSunrise();
        this.sunset = builder.getSunset();
    }

    public Astronomy(){

    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }



}
