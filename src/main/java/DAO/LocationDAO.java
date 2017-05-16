package DAO;


import Model.*;
import Connection.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class LocationDAO implements WeatherDAO {


    public void insertSQL(Location locat) throws Exception {

        String sql = "insert into location (city, country, region, title, actualday, state, temp, humidity, pressure, visibility, sunrise, sunset) "
                + "values ('"+locat.getCity()+"', '"+locat.getCountry()+"', '"+locat.getRegion()+"', '"+locat.getIt().getTitle()+"', '"
                +locat.getIt().getActualday()+"', '"+locat.getIt().getText()+"', "+locat.getIt().getTemp()+", "
                +locat.getAtm().getHumidity()+", "+locat.getAtm().getPressure()+", "+locat.getAtm().getVisibility()+", '"
                +locat.getAstro().getSunrise()+"', '"+locat.getAstro().getSunset()+"');";
        Connection c = DbConnection.getDbConnection();
        Statement st = c.createStatement();
        st.executeUpdate(sql);

    }

    public ResultSet selectSQL(String sql) throws Exception {
            Connection c = DbConnection.getDbConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;

    }

    public void insertSQL(List<Forecast> fore) throws Exception {

    }


}
