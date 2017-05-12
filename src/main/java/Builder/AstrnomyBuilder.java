package Builder;


import Model.Astronomy;
import org.h2.engine.User;

public class AstrnomyBuilder {

    private  String sunrise = "", sunset= "";

    public AstrnomyBuilder(){

    }

    public AstrnomyBuilder sunrise(String sunrise){
        this.sunrise = sunrise;
        return this;
    }

    public AstrnomyBuilder sunset(String sunset){
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
