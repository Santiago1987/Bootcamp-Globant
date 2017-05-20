package Builder;


import Model.Atmosphere;

public class AtmosphereBuilder {

    private int humidity = 0;
    private float pressure = 0;
    private float visibility = 0;

    public AtmosphereBuilder() {
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
