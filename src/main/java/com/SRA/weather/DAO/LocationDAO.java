package com.SRA.weather.DAO;

import com.SRA.weather.Builder.AstronomyBuilder;
import com.SRA.weather.Builder.AtmosphereBuilder;
import com.SRA.weather.Builder.ItemBuilder;
import com.SRA.weather.Builder.LocationBuilder;
import com.SRA.weather.Connection.DbConnection;
import com.SRA.weather.Model.Astronomy;
import com.SRA.weather.Model.Atmosphere;
import com.SRA.weather.Model.Item;
import com.SRA.weather.Model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class LocationDAO implements WeatherDAO{

    @Autowired
    DbConnection dbConnection;

    LocationBuilder locationBuilder = new LocationBuilder();
    AstronomyBuilder astronomyBuilder = new AstronomyBuilder();
    AtmosphereBuilder atmosphereBuilder = new AtmosphereBuilder();
    ItemBuilder itemBuilder = new ItemBuilder();

    public void insertSQL(Location locat) throws Exception {

        String sql = "insert into location (city, country, region, title, actualdate, actualday, state, temp, humidity, pressure, visibility, sunrise, sunset) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?);";

        Connection connection = dbConnection.getConnection();

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, locat.getCity());
        pst.setString(2, locat.getCountry());
        pst.setString(3, locat.getRegion());
        pst.setString(4, locat.getItem().getTitle());
        pst.setString(5,locat.getActualdate());
        pst.setString(6, locat.getItem().getActualday());
        pst.setString(7, locat.getItem().getText());
        pst.setInt(8, locat.getItem().getTemp());
        pst.setInt(9, locat.getAtmosphere().getHumidity());
        pst.setFloat(10, locat.getAtmosphere().getPressure());
        pst.setFloat(11, locat.getAtmosphere().getVisibility());
        pst.setString(12, locat.getAstronomy().getSunrise());
        pst.setString(13, locat.getAstronomy().getSunset());

        pst.executeUpdate();
        connection.close();
    }

    public Location selectSQL(String city, String country) throws Exception {

        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from location where city='"+city+"' and country='"+country+"'");
        rs.last();

        Astronomy astronomy = astronomyBuilder.sunset(rs.getString(14)).sunrise(rs.getString(13)).build();
        Atmosphere atmosphere = atmosphereBuilder.humidity(rs.getInt(10)).visibility(rs.getFloat(12)).pressure(rs.getFloat(11)).build();
        Item item = itemBuilder.title(rs.getString(5)).text(rs.getString(8)).temp(rs.getInt(9)).build();
        Location location = locationBuilder.item(item).region(rs.getString(4)).country(rs.getString(3)).city(rs.getString(2))
                                            .atmosphere(atmosphere).astronomy(astronomy).build();


        connection.close();
        return location;
    }



    public void updateSQL(Location location) throws Exception {
        Connection connection = dbConnection.getConnection();

        String country = location.getCountry();
        String city = location.getCity();
        Statement st = connection.createStatement();
        ResultSet resu = st.executeQuery("select cod_locat from location where city='" + city + "' and country='" + country + "'");
        resu.last();
        int codigo = resu.getInt(1);

        String sql = "update location set "+
                "country = ?,"+
                "title = ?," +
                "actualdate=?,"+
                "actualday = ?,"+
                "state = ?,"+
                "temp = ?,"+
                "humidity = ?,"+
                "pressure = ?,"+
                "visibility = ?,"+
                "sunrise = ?,"+
                "sunset = ?"+
                "where cod_locat=?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, location.getCountry());
        pst.setString(2,location.getItem().getTitle());
        pst.setString(3,location.getActualdate());
        pst.setString(4, location.getItem().getActualday());
        pst.setString(5, location.getItem().getText());
        pst.setInt(6, location.getItem().getTemp());
        pst.setInt(7,location.getAtmosphere().getHumidity());
        pst.setFloat(8, location.getAtmosphere().getPressure());
        pst.setFloat(9, location.getAtmosphere().getVisibility());
        pst.setString(10, location.getAstronomy().getSunrise());
        pst.setString(11, location.getAstronomy().getSunset());
        pst.setInt(12,codigo);

        pst.executeUpdate();

        connection.close();

    }

    public boolean verifyIfExists(Location location){
        int codigo = 1;
        String city = location.getCity(), country = location.getCountry();

        try {
            Connection connection = dbConnection.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from location where city='" + city + "' and country='" + country + "'");
            rs.last();
            codigo = rs.getInt(1);
            return false;

        }catch (Exception e){}

        return true;

    }
}
