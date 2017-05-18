package DAO;

import Builder.*;
import Connection.DbConnection;
import Model.Forecast;
import Model.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class LocationandForecastDAOTest {
    int codigo, i = 0;
    DbConnection dbConnection;
    Connection connection;
    private boolean n;
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
    private Statement st = null;
    private ResultSet resu;
    private String sql = "create schema Weather";
    private String use = "use Weather";
    private String table1 ="create table location(" +
            "cod_locat int auto_increment not null," +
            "    city varchar(50)," +
            "    country varchar(50)," +
            "    region varchar(50)," +
            "    title varchar(50)," +
            "    actualday varchar(50)," +
            "    state varchar(50)," +
            "    temp int," +
            "    humidity int," +
            "    pressure float," +
            "    visibility float," +
            "    sunrise varchar(50)," +
            "    sunset varchar(50)," +
            "    constraint pk_cod_locat primary key (cod_locat));";

    private String table2 = "create table forecast(" +
            "cod_fore int auto_increment not null," +
            "    datef varchar(50)," +
            "    dayf varchar(50)," +
            "    state varchar(50)," +
            "    high float," +
            "    low float," +
            "    cod_locat int not null," +
            "    constraint pk_cod_fore primary key (cod_fore)," +
            "    constraint fk_cod_locat foreign key (cod_locat) " +
            "    references location (cod_locat));";
    private List<Forecast> fore = new ArrayList<Forecast>();
    private Location location;

    @Before
    public void setUp() throws Exception {
        ForecastBuilder forecastBuilder = (ForecastBuilder) context.getBean("forecastBuilder");
        LocationBuilder locationBuilder = (LocationBuilder) context.getBean("locationBuilder");
        AstronomyBuilder astronomyBuilder = (AstronomyBuilder) context.getBean("astronomyBuilder");
        AtmosphereBuilder atmosphereBuilder = (AtmosphereBuilder) context.getBean("atmosphereBuilder");
        ItemBuilder itemBuilder = (ItemBuilder) context.getBean("itemBuilder");
        location = locationBuilder.city("Cordoba").region("CBA").country("Argentina").atm(atmosphereBuilder.humidity(66).pressure(965).visibility(22).build()).astro(astronomyBuilder.sunrise("7:30 am").sunset("6:30 am").build())
                .it(itemBuilder.title("Condition for Cordoba at 05:00 PM ART").text("Cloudy").actualday("Friday").temp(70).build()).build();
        fore.add(forecastBuilder.date("10 april").day("Monday").high(60).low(30).text("Cloudy").build());
        dbConnection = (DbConnection) context.getBean("connectionH2");
        connection = dbConnection.getConnection();
        st = connection.createStatement();
        st.executeUpdate(sql);
        st.executeUpdate(use);
        st.executeUpdate(table1);
        st.executeUpdate(table2);
    }




    @Test
    public void TestInsertAndSelect() throws Exception {

        try {

        sql = "insert into location (city, country, region, title, actualday, state, temp, humidity, pressure, visibility, sunrise, sunset) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, location.getCity());
        pst.setString(2, location.getCountry());
        pst.setString(3, location.getRegion());
        pst.setString(4, location.getIt().getTitle());
        pst.setString(5, location.getIt().getActualday());
        pst.setString(6, location.getIt().getText());
        pst.setInt(7, location.getIt().getTemp());
        pst.setInt(8, location.getAtm().getHumidity());
        pst.setFloat(9, location.getAtm().getPressure());
        pst.setFloat(10, location.getAtm().getVisibility());
        pst.setString(11, location.getAstro().getSunrise());
        pst.setString(12, location.getAstro().getSunset());
        pst.executeUpdate();
        st = connection.createStatement();
        resu = st.executeQuery("select cod_locat from location");
        resu.last();
        codigo = resu.getInt(1);

        sql ="insert into forecast (datef, dayf, state, high, low, cod_locat) "
                + "values (?,?,?,?,?,?);";
        pst = connection.prepareStatement(sql);

        for(Forecast f : fore){
            if(codigo !=0){

                    pst.setString(1, fore.get(i).getDate());
                    pst.setString(2, fore.get(i).getDay());
                    pst.setString(3, fore.get(i).getText());
                    pst.setFloat(4, fore.get(i).getHigh());
                    pst.setFloat(5, fore.get(i).getLow());
                    pst.setInt(6, codigo);
                    pst.executeUpdate();
                    i++;

            }
        }

            resu = st.executeQuery("select datef, dayf, f.state, high, low, f.cod_locat from forecast f join location l on f.cod_locat=l.cod_locat "
                    + "where f.cod_locat="+codigo);
            resu.last();
            assertEquals("10 april", resu.getString(1));
            assertEquals("Monday", resu.getString(2));
            assertEquals("Cloudy", resu.getString(3));
            assertEquals(60, resu.getFloat(4), 0.001);
            assertEquals(30,resu.getFloat(5),0.001);

            n = true;
        }catch (SQLException e){
            e.printStackTrace();
            n=false;
        }

        assertTrue(n);
    }



    @After
    public void tearDown() throws Exception {
        st.executeUpdate("Drop schema Weather");
        st.close();
    }
}