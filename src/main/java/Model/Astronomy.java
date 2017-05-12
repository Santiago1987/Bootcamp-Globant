package Model;


import Builder.AstrnomyBuilder;

public class Astronomy {
    private String sunrise, sunset;

    public Astronomy(AstrnomyBuilder builder){
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
