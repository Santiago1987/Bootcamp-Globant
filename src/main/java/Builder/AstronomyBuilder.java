package Builder;


import Model.Astronomy;

public class AstronomyBuilder {

    private  String sunrise, sunset;

    public AstronomyBuilder(String sunrise, String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public AstronomyBuilder sunrise(String sunrise){
        this.sunrise = sunrise;
        return this;
    }

    public AstronomyBuilder sunset(String sunset){
        this.sunset = sunset;
        return this;
    }

    public Astronomy build(){
        return  new Astronomy(this);
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }



}
