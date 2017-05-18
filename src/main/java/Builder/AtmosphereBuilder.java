package Builder;


import Model.Atmosphere;

public class AtmosphereBuilder {

    private int humidity;
    private float pressure;
    private float visibility;

    public AtmosphereBuilder(int humidity, float pressure, float visibility) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
    }

    public AtmosphereBuilder humidity(int humidity){
        this.humidity = humidity;
        return this;
    }

    public  AtmosphereBuilder pressure(float pressure){
        this.pressure = pressure;
        return this;
    }

    public AtmosphereBuilder visibility(float visibility){
        this.visibility = visibility;
        return this;
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

    public Atmosphere build(){
        return new Atmosphere(this);
    }


}
