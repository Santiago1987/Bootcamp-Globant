package Model;


import Builder.*;
import Conecction.DataManipulation;
import Conecction.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public  static void main(String[] arg){
        int i = 0;
        Connection c = DbConnection.getDbConnection();
        DataManipulation dm = new DataManipulation();
        AstrnomyBuilder astro = new AstrnomyBuilder();
        AtmosphereBuilder atm = new AtmosphereBuilder();
        ForecastBuilder forec = new ForecastBuilder();
        ItemBuilder it = new ItemBuilder();
        LocationBuilder locat = new LocationBuilder();

        locat.city("Cordoba")
             .region("CBA")
             .country("Argentina")
             .atm(atm.humidity(66)
                     .pressure(965)
                     .visibility(22)
                     .build())
             .astro(astro.sunrise("7:30 am")
                         .sunset("6: 30 am")
                         .build())
             .it(it.title("Condition for Cordoba at 05:00 PM ART")
                   .text("Cloudy")
                   .actualday("Friday")
                   .temp(70)
                   .build());

        locat.getIt().getFore().add(forec.date("21 April 2017")
                                        .day("Saturday")
                                        .text("Claudy")
                                        .low(13)
                                        .high(25)
                                        .build());
        locat.getIt().getFore().add(forec.date("22 April 2017")
                                        .day("Sunday")
                                        .text("Partly Cloudy")
                                        .low(18)
                                        .high(27)
                                        .build());

        locat.getIt().getFore().add(forec.date("23 April 2017")
                                        .day("Monday")
                                        .text("Partly Cloudy")
                                        .low(11)
                                        .high(20)
                                        .build());
        locat.getIt().getFore().add(forec.date("24 April 2017")
                                        .day("Tuesday")
                                        .text("Scattered Showers")
                                        .low(13)
                                        .high(25)
                                        .build());
        locat.getIt().getFore().add(forec.date("25 April 2017")
                                        .day("Wednesday")
                                        .text("Sunny")
                                        .low(10)
                                        .high(20)
                                        .build());


        String sql = "insert into location (city, country, region, title, actualday, state, temp, humidity, pressure, visibility, sunrise, sunset) "
                + "values ('"+locat.getCity()+"', '"+locat.getCountry()+"', '"+locat.getRegion()+"', '"+locat.getIt().getTitle()+"', '"
                +locat.getIt().getActualday()+"', '"+locat.getIt().getText()+"', "+locat.getIt().getTemp()+", "
                +locat.getAtm().getHumidity()+", "+locat.getAtm().getPressure()+", "+locat.getAtm().getVisibility()+", '"
                +locat.getAstro().getSunrise()+"', '"+locat.getAstro().getSunset()+"');";


        if(dm.insertSQL(sql)){
            System.out.println("The data entry was successful");
        }


        ResultSet resu = dm.selectSQL("select * from location");
        int codigo = 0;

        try{
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



        }catch(SQLException ex){
            ex.printStackTrace();
        }

        for(Forecast f : locat.getIt().getFore()){

            if(locat.getIt().getFore().get(i) != null && codigo !=0){

                sql = "insert into forecast (datef, dayf, state, high, low, cod_locat) "
                        + "values ('"+locat.getIt().getFore().get(i).getDate()+"', '"+locat.getIt().getFore().get(i).getDay()+"', '"
                        +locat.getIt().getFore().get(i).getText()+"', "+locat.getIt().getFore().get(i).getHigh()+", "
                        +locat.getIt().getFore().get(i).getLow()+", "+codigo+");";
                dm.insertSQL(sql);

                i++;

            }


        }


        resu = dm.selectSQL("select datef, dayf, f.state, high, low, f.cod_locat from forecast f join location l on f.cod_locat=l.cod_locat "
                + "where f.cod_locat="+codigo);

        System.out.println("Forecast for the next five days: ");

        String list = "";

        try{
            while(resu.next()){
                list += "Date: "+resu.getString(1)+"\n"
                        +"Day: "+resu.getString(2)+"\n"
                        +"State: "+resu.getString(3)+"\n"
                        +"High: "+resu.getFloat(4)+"\n"
                        +"Low: "+resu.getFloat(5)+"\n"+"\n";
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }



        System.out.println(list);
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
