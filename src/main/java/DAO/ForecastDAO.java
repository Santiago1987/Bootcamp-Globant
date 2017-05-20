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

public class ForecastDAO implements WeatherDAO {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
    int codigo;
    public void insertSQL(List<Forecast> fore) throws Exception {
        int i = 0;
        DbConnection dbConnection = (DbConnection) context.getBean("connection");
        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        ResultSet resu = st.executeQuery("select cod_locat from location");
        resu.last();
        codigo = resu.getInt(1);
        String sql ="insert into forecast (datef, dayf, state, high, low, cod_locat) "
                + "values (?,?,?,?,?,?);";
        PreparedStatement pst = connection.prepareStatement(sql);

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
        connection.close();
    }


    public void insertSQL(Location locat) throws Exception {

    }

    public String selectSQL() throws Exception {
        String list = "";
        DbConnection dbConnection = (DbConnection) context.getBean("connection");
        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        ResultSet resu = st.executeQuery("select datef, dayf, f.state, high, low, f.cod_locat from forecast f join location l on f.cod_locat=l.cod_locat "
                + "where f.cod_locat="+codigo);
        while(resu.next()){
            list += "Date: "+resu.getString(1)+"\n"
                    +"Day: "+resu.getString(2)+"\n"
                    +"State: "+resu.getString(3)+"\n"
                    +"High: "+resu.getFloat(4)+"\n"
                    +"Low: "+resu.getFloat(5)+"\n"+"\n";
        }
        connection.close();
        return list;
    }
}
