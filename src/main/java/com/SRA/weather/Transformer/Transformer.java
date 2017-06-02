package com.SRA.weather.Transformer;


import com.SRA.weather.Builder.*;
import com.SRA.weather.Model.*;
import org.json.JSONObject;

public class Transformer {


    public static Astronomy jsonAstronomyToAstronomy(JSONObject jsonAstronomy){

        AstronomyBuilder astronomyBuilder = new AstronomyBuilder();

        Astronomy astronomy = astronomyBuilder.sunrise(jsonAstronomy.getString("sunrise"))
                                                .sunset(jsonAstronomy.getString("sunset")).build();

        return astronomy;
    }

    public static Atmosphere jsonAtmosphereToAtmosphere(JSONObject jsonAtmosphere){

        AtmosphereBuilder atmosphereBuilder = new AtmosphereBuilder();

        Atmosphere atmosphere = atmosphereBuilder.humidity(jsonAtmosphere.getInt("humidity"))
                                                    .pressure((float) jsonAtmosphere.getDouble("pressure"))
                                                    .visibility((float) jsonAtmosphere.getDouble("visibility"))
                                                    .build();
        return atmosphere;
    }

    public static Item jsonItemtoItem(JSONObject jsonItem, JSONObject jsonCondition){

        ItemBuilder itemBuilder = new ItemBuilder();

        Item item = itemBuilder.title(jsonItem.getString("title"))
                                .actualday(jsonCondition.getString("date"))
                                .temp(jsonCondition.getInt("temp"))
                                .text(jsonCondition.getString("text"))
                                .build();

        return item;
    }



    public static Forecast jsonForecastToForecast(JSONObject jsonForecast){

        ForecastBuilder forecastBuilder = new ForecastBuilder();

        Forecast forecast = forecastBuilder.date(jsonForecast.getString("date"))
                                            .day(jsonForecast.getString("day"))
                                            .high((float) jsonForecast.getDouble("high"))
                                            .low((float) jsonForecast.getDouble("low"))
                                            .text(jsonForecast.getString("text"))
                                            .build();
        return  forecast;
    }


    public static Location jsonLocationToLocation(JSONObject jsonLocation, Astronomy astronomy, Atmosphere atmosphere, Item item){

        LocationBuilder locationBuilder = new LocationBuilder();
        String actual = item.getForecast().get(0).getDate();

        Location location = locationBuilder.astronomy(astronomy)
                                            .actualdate(actual)
                                            .atmosphere(atmosphere)
                                            .city(jsonLocation.getString("city"))
                                            .country(jsonLocation.getString("country"))
                                            .region(jsonLocation.getString("region"))
                                            .item(item)
                                            .build();
        return location;

    }





}
