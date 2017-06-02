package com.SRA.weather.DbManager;

import com.SRA.weather.DAO.ForecastDAO;
import com.SRA.weather.DAO.LocationDAO;
import com.SRA.weather.Model.Forecast;
import com.SRA.weather.Model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class DbManager {

    @Autowired
    private LocationDAO locationDAO;

    @Autowired
    private ForecastDAO forecastDAO;

    public void setLocationDAO(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public void setForecastDAO(ForecastDAO forecastDAO) {
        this.forecastDAO = forecastDAO;
    }


    public Location locationFromDb(String city, String country){
        Location location = null;
        try {
            location = locationDAO.selectSQL(city, country);

        }catch (Exception e){}

        return location;

    }


    public Location forecastFromDb(Location location){

        if(location != null){
            try {

                ArrayList<Forecast> forecasts = forecastDAO.selectSQL(location.getCity(), location.getCountry());

                location.getItem().setFore(forecasts);

                return location;

            }catch (Exception e){}

        }
        return location;
    }


    public ResponseEntity saveLocation(Location location){

        if(DataValidator.locationChecker(location)){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong entered data");
        }else if(locationDAO.verifyIfExists(location)){
            try {
                locationDAO.insertSQL(location);
                forecastDAO.insertSQL(location);
                return ResponseEntity.status(HttpStatus.OK).body("Data saved");

            } catch (Exception e) {
            }
        }else{
            try {
                locationDAO.updateSQL(location);
                forecastDAO.updateSQL(location);
                return ResponseEntity.status(HttpStatus.OK).body("Data updated");

            } catch (Exception e) {}

        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error");
    }

















}
