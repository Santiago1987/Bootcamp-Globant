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
        String list ="";
        AstronomyBuilder astronomyBuilder = (AstronomyBuilder) context.getBean("astronomyBuilder");
        AtmosphereBuilder atmosphereBuilder = (AtmosphereBuilder) context.getBean("atmosphereBuilder");
        ForecastBuilder forecastBuilder = (ForecastBuilder) context.getBean("forecastBuilder");
        ItemBuilder itemBuilder = (ItemBuilder) context.getBean("itemBuilder");
        LocationBuilder locationBuilder = (LocationBuilder) context.getBean("locationBuilder");
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
            resu = l.selectSQL("select * from location");
            resu.last();
            codigo = resu.getInt(1);
            System.out.println("Climate in the city of: "+resu.getString(2)+"\n"
                    +"Country: "+resu.getString(3)+"\n"
                    +"Region: "+resu.getString(4)+"\n"
                    +resu.getString(5)+"\n"
                    +"Day: "+resu.getString(6)+"\n"
                    +"State: "+resu.getString(7)+"\n"
                    +"Temperature: "+resu.getInt(8)+"\n"
                    +"Humidity: "+resu.getInt(9)+"\n"
                    +"Pressure: "+resu.getFloat(10)+"\n"
                    +"Visibility: "+resu.getFloat(11)+"\n"
                    +"Sunrise: "+resu.getString(12)+"\n"
                    +"Sunset: "+resu.getString(13));

            f.insertSQL(locationBuilder.getIt().getFore());
            resu = f.selectSQL("select datef, dayf, f.state, high, low, f.cod_locat from forecast f join location l on f.cod_locat=l.cod_locat "
                    + "where f.cod_locat="+codigo);

            System.out.println("Forecast for the next five days: ");

            while(resu.next()){
                list += "Date: "+resu.getString(1)+"\n"
                        +"Day: "+resu.getString(2)+"\n"
                        +"State: "+resu.getString(3)+"\n"
                        +"High: "+resu.getFloat(4)+"\n"
                        +"Low: "+resu.getFloat(5)+"\n"+"\n";
            }
            System.out.println(list);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
