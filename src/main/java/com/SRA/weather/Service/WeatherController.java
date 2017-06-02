package com.SRA.weather.Service;

import com.SRA.weather.Adapter.YahooStringAdapter;
import com.SRA.weather.Builder.*;
import com.SRA.weather.DAO.LocationDAO;
import com.SRA.weather.DbManager.DbManager;
import com.SRA.weather.Model.Location;
import com.SRA.weather.Proxy.ProxyWeather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.PathParam;
import javax.xml.ws.Response;


@Component
@RestController
public class WeatherController {

    @Autowired
    YahooStringAdapter yahooStringAdapter;

    @Autowired
    DbManager dbManager;

    @RequestMapping(value = "/location/city/{city}/country/{country}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLocation(@PathVariable String city, @PathVariable String country){

        Location location = yahooStringAdapter.yahooString(city,country);

        if(location!=null) {

            return ResponseEntity.status(HttpStatus.OK).body(location);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unreachable data");
        }
    }


    @RequestMapping(value = "/location/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveData(@RequestBody Location location){

        ResponseEntity response = dbManager.saveLocation(location);


        return response;
    }


    @RequestMapping(value = "/location/update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity uptdateData(@RequestBody Location location){


        ResponseEntity response = dbManager.saveLocation(location);


        return response;

    }
}

































