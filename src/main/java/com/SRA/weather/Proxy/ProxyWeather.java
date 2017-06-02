package com.SRA.weather.Proxy;

import com.SRA.weather.Adapter.ClientYahooWeather;
import org.apache.cxf.common.i18n.Exception;
import org.json.JSONObject;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


@Component
public class ProxyWeather implements ClientYahooWeather{

    @Resource
    private ClientYahooWeather client;

    private JSONObject jsonObject = null;

    public String getWeather(String city, String country){

        try {
            String yql = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\" " + city + "," + country + "\")";

            jsonObject = new JSONObject(client.getWeather(yql, "json"));

        }catch (UnknownException e){}

        return jsonObject.toString();

    }
}

