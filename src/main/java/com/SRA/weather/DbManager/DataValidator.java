package com.SRA.weather.DbManager;


import com.SRA.weather.Model.Location;

import java.util.Locale;

public class DataValidator {

    public static boolean locationChecker(Location location){

        if (location.getCity() == null || location.getCountry() == null || location.getItem()==null || location.getAstronomy()==null || location.getAtmosphere() == null){

            return true;

        }

        return false;


    }





}
