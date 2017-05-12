package Model;


import Builder.ForecastBuilder;

public class Forecast {
    private String date, day, text;
    private float high, low;

    public Forecast(ForecastBuilder builder) {
        this.date = builder.getDate();
        this.day = builder.getDay();
        this.text = builder.getText();
        this.high = builder.getHigh();
        this.low = builder.getLow();
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
