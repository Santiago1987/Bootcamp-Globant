package Model;


import Builder.*;
import Connection.DbConnection;
import DAO.WeatherDAO;

import java.sql.Connection;
import java.sql.ResultSet;

public class Main {
    public  static void main(String[] arg){
        int codigo = 0;
        String list ="";
        AstronomyBuilder astro = new AstronomyBuilder();
        AtmosphereBuilder atm = new AtmosphereBuilder();
        ForecastBuilder forec = new ForecastBuilder();
        ItemBuilder it = new ItemBuilder();
        LocationBuilder locat = new LocationBuilder();
        WeatherDAO f = new Forecast();
        ResultSet resu;

        Location l = locat.city("Cordoba").region("CBA").country("Argentina").atm(atm.humidity(66).pressure(965).visibility(22).build()).astro(astro.sunrise("7:30 am").sunset("6:30 am").build())
                .it(it.title("Condition for Cordoba at 05:00 PM ART").text("Cloudy").actualday("Friday").temp(70).build()).build();


        locat.getIt().getFore().add(forec.date("21 April 2017").day("Saturday").text("Claudy").low(13).high(25).build());
        locat.getIt().getFore().add(forec.date("22 April 2017").day("Sunday").text("Partly Cloudy").low(18).high(27).build());
        locat.getIt().getFore().add(forec.date("23 April 2017").day("Monday").text("Partly Cloudy").low(11).high(20).build());
        locat.getIt().getFore().add(forec.date("24 April 2017").day("Tuesday").text("Scattered Showers").low(13).high(25).build());
        locat.getIt().getFore().add(forec.date("25 April 2017").day("Wednesday").text("Sunny").low(10).high(20).build());

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

            f.insertSQL(locat.getIt().getFore());
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
            DbConnection.disconect();

        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
