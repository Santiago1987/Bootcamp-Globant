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
    public void insertSQL(List<Forecast> fore) throws Exception {
        int i = 0, codigo;
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
    }


    public void insertSQL(Location locat) throws Exception {

    }

    public ResultSet selectSQL(String sql) throws Exception {
        DbConnection dbConnection = (DbConnection) context.getBean("connection");
        Connection connection = dbConnection.getConnection();
        Statement st = connection.createStatement();
        ResultSet resu = st.executeQuery(sql);
        return resu;
    }
}
