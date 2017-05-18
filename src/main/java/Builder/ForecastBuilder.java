package Builder;


import Model.Forecast;

public class ForecastBuilder {
    private String date, day, text;
    private float high, low;

    public ForecastBuilder(String date, String day, String text, float high, float low) {
        this.date = date;
        this.day = day;
        this.text = text;
        this.high = high;
        this.low = low;
    }

    public ForecastBuilder date(String date){
        this.date = date;
        return this;
    }

    public ForecastBuilder day(String day){
        this.day = day;
        return this;
    }

    public ForecastBuilder text(String text){
        this.text = text;
        return this;
    }

    public ForecastBuilder high(float high){
        this.high = high;
        return this;
    }

    public ForecastBuilder low(float low){
        this.low = low;
        return this;
    }

    public Forecast build(){
        return new Forecast(this);
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getText() {
        return text;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

}
