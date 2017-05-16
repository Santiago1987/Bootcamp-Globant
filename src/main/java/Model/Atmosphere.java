package Model;


import Builder.AtmosphereBuilder;

public class Atmosphere {

    private int humidity;
    private float pressure;
    private float visibility;


    public Atmosphere(AtmosphereBuilder builder) {
        this.humidity = builder.getHumidity();
        this.pressure = builder.getPressure();
        this.visibility = builder.getVisibility();
    }

    public Atmosphere() {
    }

    public int getHumidity() {
        return humidity;
    }



    public float getPressure() {
        return pressure;
    }



    public float getVisibility() {
        return visibility;
    }
}
