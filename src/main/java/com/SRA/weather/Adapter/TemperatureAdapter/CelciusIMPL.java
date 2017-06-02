package com.SRA.weather.Adapter.TemperatureAdapter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CelciusIMPL implements Celcius {

    @Autowired
    private FarenheiteIMPL farenheiteIMPL;

    public float getTemperature(float temp) {

        return ((farenheiteIMPL.getTemperature(temp)-32)*5/9);
    }
}
