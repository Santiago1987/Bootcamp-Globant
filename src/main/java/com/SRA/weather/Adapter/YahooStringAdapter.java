package com.SRA.weather.Adapter;


import com.SRA.weather.Adapter.TemperatureAdapter.CelciusIMPL;
import com.SRA.weather.DbManager.DbManager;
import com.SRA.weather.Model.*;
import com.SRA.weather.Transformer.Transformer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YahooStringAdapter implements YahooString{

    @Autowired
    DbManager dbManager;

    @Autowired
    ClientYahooWeather proxyWeather;

    @Autowired
    CelciusIMPL celciusIMPL;

    @Override
    public Location yahooString(String city, String country) {
        int i = 0;
        Location location = null;

        try {

            String jsonString = proxyWeather.getWeather(city,country);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONObject jsonQuery = jsonObject.getJSONObject("query");
            JSONObject jsonResults = jsonQuery.getJSONObject("results");
            JSONObject jsonChannel = jsonResults.getJSONObject("channel");

            JSONObject jsonLocation = jsonChannel.getJSONObject("location");
            JSONObject jsonAtmosphere = jsonChannel.getJSONObject("atmosphere");
            JSONObject jsonAstronomy = jsonChannel.getJSONObject("astronomy");
            JSONObject jsonItem = jsonChannel.getJSONObject("item");
            JSONObject jsonCondition = jsonItem.getJSONObject("condition");

            JSONArray jsonArrayForecast = jsonItem.getJSONArray("forecast");

            Astronomy astronomy = Transformer.jsonAstronomyToAstronomy(jsonAstronomy);
            Atmosphere atmosphere = Transformer.jsonAtmosphereToAtmosphere(jsonAtmosphere);
            Item item = Transformer.jsonItemtoItem(jsonItem,jsonCondition);

            do{
                Forecast forecast = Transformer.jsonForecastToForecast(jsonArrayForecast.getJSONObject(i));
                item.getForecast().add(forecast);
                i++;

            }while(i < jsonArrayForecast.length());


            location = Transformer.jsonLocationToLocation(jsonLocation,astronomy,atmosphere,item);

            dbManager.saveLocation(location);


        }catch (Exception e){ }

        if(location == null){
            Location locationIncomplete = dbManager.locationFromDb(city, country);
            location = dbManager.forecastFromDb(locationIncomplete);

        }

        if(location != null){
            float tempfore;
            int in =0;
            tempfore = celciusIMPL.getTemperature((float) location.getItem().getTemp());
            location.getItem().setTemp((int) tempfore);

            for(Forecast f : location.getItem().getForecast()){

                tempfore = (int)celciusIMPL.getTemperature(location.getItem().getForecast().get(in).getHigh());
                location.getItem().getForecast().get(in).setHigh(tempfore);
                tempfore = (int)celciusIMPL.getTemperature(location.getItem().getForecast().get(in).getLow());
                location.getItem().getForecast().get(in).setLow(tempfore);

                in++;
            }
        }

        return location;
    }
}
