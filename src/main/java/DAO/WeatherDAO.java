package DAO;

import Model.Forecast;
import Model.Location;

import java.sql.ResultSet;
import java.util.List;

public interface WeatherDAO {

    public void insertSQL(Location locat) throws Exception ;

    String selectSQL() throws Exception;

    public void insertSQL(List<Forecast> fore) throws Exception;


}
