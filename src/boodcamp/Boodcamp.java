/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boodcamp;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Boodcamp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              
        
        Location locat = new Location("Cordoba", "Argentina", "CBA",
                new Atmosphere(66, 965, 22),
                new Astronomy("7:41 am", "6:50 pm"),                
                new Item("Condition for Cordoba at 05:00 PM ART", "Friday", "Cloudy", 70, 5));
        
        locat.getIt().addDay(1, "21 April 2017", "Saturday", "Nublado", 13, 25);
        locat.getIt().addDay(1, "22 April 2017", "Sunday", "Partly Cloudy", 13, 25);
        locat.getIt().addDay(1, "23 April 2017", "Monday", "Partly Cloudy", 13, 25);
        locat.getIt().addDay(1, "24 April 2017", "Tuesday", "Scattered Showers", 13, 25);
        locat.getIt().addDay(1, "25 April 2017", "Wednesday", "Sunny", 13, 25);
        
        
        //Data insertion in the database
        
        Conexion c = new Conexion();
        
        String sql = "insert into location (city, country, region, title, actualday, state, temp, humidity, pressure, visibility, sunrise, sunset) "
                + "values ('"+locat.getCiudad()+"', '"+locat.getPais()+"', '"+locat.getRegion()+"', '"+locat.getIt().getTitle()+"', '"
                +locat.getIt().getActualday()+"', '"+locat.getIt().getText()+"', "+locat.getIt().getTemp()+", "
                +locat.getAtm().getHumidity()+", "+locat.getAtm().getPressure()+", "+locat.getAtm().getVisibility()+", '"
                +locat.getAstro().getSunrise()+"', '"+locat.getAstro().getSunset()+"');";
        
       
        
        if(c.insertSQL(sql)){
            System.out.println("The data entry was successful");
        }
        
        
        ResultSet resu = c.selectSQL("select * from location");
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
            int i = 0;
            if(locat.getIt().getFore()[i] != null && codigo !=0){
                
                sql = "insert into forecast (datef, dayf, state, high, low, cod_locat) "
                        + "values ('"+locat.getIt().getFore()[i].getDate()+"', '"+locat.getIt().getFore()[i].getDay()+"', '"
                        +locat.getIt().getFore()[i].getText()+"', "+locat.getIt().getFore()[i].getHigh()+", "
                        +locat.getIt().getFore()[i].getLow()+", "+codigo+");";
                c.insertSQL(sql);
                i++;
                
            }     
            
            
        }        
        
        resu = c.selectSQL("select datef, dayf, f.state, high, low, f.cod_locat from forecast f join location l on f.cod_locat=l.cod_locat "
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
        
        
        
        
        
        
        
    }
    
}
