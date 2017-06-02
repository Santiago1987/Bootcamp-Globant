package com.SRA.weather.Adapter.TemperatureAdapter;

import org.springframework.stereotype.Component;

@Component
public class FarenheiteIMPL implements Farenheite {

    public float getTemperature(float temp) {
        return temp;
    }
}
