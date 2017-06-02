package com.SRA.weather.DAO;


import com.SRA.weather.Builder.ForecastBuilder;
import com.SRA.weather.Connection.DbConnection;
import com.SRA.weather.Model.Forecast;
import com.SRA.weather.Model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class ForecastDAO implements WeatherDAO {

    ForecastBuilder forecastBuilder = new ForecastBuilder();

    @Autowired
    private DbConnection dbConnection;

    public void insertSQL(Location location) throws Exception {
        int i = 0;
        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();

        String city = location.getCity();
        String country = location.getCountry();
        ResultSet resu = st.executeQuery("select cod_locat from location where city='" + city + "' and country='" + country + "'");
        resu.last();
        int codigo = resu.getInt(1);
        String sql = "insert into forecast (datef, dayf, state, high, low, cod_locat) "
                + "values (?,?,?,?,?,?);";
        PreparedStatement pst = connection.prepareStatement(sql);

        for (Forecast forecast : location.getItem().getForecast()) {
            if (codigo != 0) {
                pst.setString(1, location.getItem().getForecast().get(i).getDate());
                pst.setString(2, location.getItem().getForecast().get(i).getDay());
                pst.setString(3, location.getItem().getForecast().get(i).getText());
                pst.setFloat(4, location.getItem().getForecast().get(i).getHigh());
                pst.setFloat(5, location.getItem().getForecast().get(i).getLow());
                pst.setInt(6, codigo);
                pst.executeUpdate();

                i++;
            }
        }
        connection.close();
    }

    public ArrayList<Forecast> selectSQL(String city, String country) throws Exception {


        ArrayList<Forecast> forecastArray = new ArrayList<Forecast>();
        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from location where city='" + city + "' and country='" + country + "'");
        rs.last();
        int codigo = rs.getInt(1);
        ResultSet resu = st.executeQuery("select datef, dayf, f.state, high, low, f.cod_locat from forecast f join location l on f.cod_locat=l.cod_locat "
                + "where f.cod_locat=" + codigo);
        while (resu.next()) {
            Forecast forecast = forecastBuilder.text(resu.getString(3)).low(resu.getFloat(5)).high(resu.getFloat(4))
                    .day(resu.getString(2)).date(resu.getString(1)).build();
            forecastArray.add(forecast);
        }

        st.close();
        rs.close();
        resu.close();
        connection.close();
        return forecastArray;
    }

    public void updateSQL(Location location) throws Exception {

        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        String sqlss = "select cod_locat from location where city='" + location.getCity() + "' and country='" + location.getCountry() + "'";
        ResultSet rs = st.executeQuery("select cod_locat from location where city='" + location.getCity() + "' and country='" + location.getCountry() + "'");
        rs.last();
        int codigo = rs.getInt(1), i = 0;

        ResultSet resu = st.executeQuery("select cod_fore from forecast where cod_locat="+codigo);
        resu.first();
        int cod_fore = resu.getInt(1);

        String sql = "update forecast set" +
                " datef= ?," +
                " dayf=?," +
                " state=?," +
                " high=?," +
                " low=?" +
                " where cod_locat=?";
        PreparedStatement pst = connection.prepareStatement(sql);

        for (Forecast forecast : location.getItem().getForecast()) {

            pst.setString(1, location.getItem().getForecast().get(i).getDate());
            pst.setString(2, location.getItem().getForecast().get(i).getDay());
            pst.setString(3, location.getItem().getForecast().get(i).getText());
            pst.setFloat(4, location.getItem().getForecast().get(i).getHigh());
            pst.setFloat(5, location.getItem().getForecast().get(i).getLow());
            pst.setInt(6, codigo);
            pst.executeUpdate();

            i++;
        }
        connection.close();
    }
}