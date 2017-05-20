package Model;


import Builder.*;
import DAO.WeatherDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.sql.ResultSet;

public class Main {
    public  static void main(String[] arg){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        int codigo = 0;
        String header;
        AstronomyBuilder astronomyBuilder = new AstronomyBuilder();
        AtmosphereBuilder atmosphereBuilder = new AtmosphereBuilder();
        ForecastBuilder forecastBuilder = new ForecastBuilder();
        ItemBuilder itemBuilder = new ItemBuilder();
        LocationBuilder locationBuilder = new LocationBuilder();
        WeatherDAO f = new Forecast();
        ResultSet resu;

        Location l = locationBuilder.city("Cordoba").region("CBA").country("Argentina").atm(atmosphereBuilder.humidity(66).pressure(965).visibility(22).build()).astro(astronomyBuilder.sunrise("7:30 am").sunset("6:30 am").build())
                .it(itemBuilder.title("Condition for Cordoba at 05:00 PM ART").text("Cloudy").actualday("Friday").temp(70).build()).build();

        locationBuilder.getIt().getFore().add(forecastBuilder.date("21 April 2017").day("Saturday").text("Claudy").low(13).high(25).build());
        locationBuilder.getIt().getFore().add(forecastBuilder.date("22 April 2017").day("Sunday").text("Partly Cloudy").low(18).high(27).build());
        locationBuilder.getIt().getFore().add(forecastBuilder.date("23 April 2017").day("Monday").text("Partly Cloudy").low(11).high(20).build());
        locationBuilder.getIt().getFore().add(forecastBuilder.date("24 April 2017").day("Tuesday").text("Scattered Showers").low(13).high(25).build());
        locationBuilder.getIt().getFore().add(forecastBuilder.date("25 April 2017").day("Wednesday").text("Sunny").low(10).high(20).build());

        try {
            l.insertSQL(l);
            System.out.println(l.selectSQL());
            f.insertSQL(locationBuilder.getIt().getFore());

            System.out.println("Forecast for the next five days: ");
            System.out.println(f.selectSQL());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
