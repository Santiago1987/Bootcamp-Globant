package DAO;


import Model.*;
import Connection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ForecastDAO implements WeatherDAO {


    public void insertSQL(List<Forecast> fore) throws Exception {
        int i = 0, codigo;
        Connection c = DbConnection.getDbConnection();
        Statement st = c.createStatement();
        ResultSet resu = st.executeQuery("select cod_locat from location");
        resu.last();
        codigo = resu.getInt(1);

        for(Forecast f : fore){
            if(codigo !=0){
                String sql ="insert into forecast (datef, dayf, state, high, low, cod_locat) "
                        + "values (?,?,?,?,?,?);";

                PreparedStatement pst = c.prepareStatement(sql);
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
        Connection c = DbConnection.getDbConnection();
        Statement st = c.createStatement();
        ResultSet resu = st.executeQuery(sql);
        return resu;
    }
}
