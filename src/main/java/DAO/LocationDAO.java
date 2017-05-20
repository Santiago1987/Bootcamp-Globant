package DAO;


import Model.*;
import Connection.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class LocationDAO implements WeatherDAO {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
    public void insertSQL(Location locat) throws Exception {

        String sql = "insert into location (city, country, region, title, actualday, state, temp, humidity, pressure, visibility, sunrise, sunset) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?);";

        DbConnection dbConnection = (DbConnection) context.getBean("connection");
        Connection connection = dbConnection.getConnection();

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, locat.getCity());
        pst.setString(2, locat.getCountry());
        pst.setString(3, locat.getRegion());
        pst.setString(4, locat.getIt().getTitle());
        pst.setString(5, locat.getIt().getActualday());
        pst.setString(6, locat.getIt().getText());
        pst.setInt(7, locat.getIt().getTemp());
        pst.setInt(8, locat.getAtm().getHumidity());
        pst.setFloat(9, locat.getAtm().getPressure());
        pst.setFloat(10, locat.getAtm().getVisibility());
        pst.setString(11, locat.getAstro().getSunrise());
        pst.setString(12, locat.getAstro().getSunset());

        pst.executeUpdate();
        connection.close();
    }

    public String selectSQL() throws Exception {
        DbConnection dbConnection = (DbConnection) context.getBean("connection");
        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from location");
        rs.last();
        String header ="Climate in the city of: "+rs.getString(2)+"\n"
                +"Country: "+rs.getString(3)+"\n"
                +"Region: "+rs.getString(4)+"\n"
                +rs.getString(5)+"\n"
                +"Day: "+rs.getString(6)+"\n"
                +"State: "+rs.getString(7)+"\n"
                +"Temperature: "+rs.getInt(8)+"\n"
                +"Humidity: "+rs.getInt(9)+"\n"
                +"Pressure: "+rs.getFloat(10)+"\n"
                +"Visibility: "+rs.getFloat(11)+"\n"
                +"Sunrise: "+rs.getString(12)+"\n"
                +"Sunset: "+rs.getString(13);
        connection.close();
        connection.close();
        return header;
    }

    public void insertSQL(List<Forecast> fore) throws Exception {

    }


}
