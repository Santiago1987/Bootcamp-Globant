package com.SRA.weather.DAO;

import com.SRA.weather.Model.Location;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

public interface WeatherDAO {

    void insertSQL(Location location) throws Exception ;


    Object selectSQL(String city, String country) throws Exception;


    void updateSQL(Location location) throws Exception;
}
