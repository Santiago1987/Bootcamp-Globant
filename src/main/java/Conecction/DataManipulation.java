package Conecction;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManipulation {

    private Statement st = null;
    private ResultSet resu;
    private Connection c = DbConnection.getDbConnection();

    public boolean insertSQL(String sql){

        try{
            st = c.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ResultSet selectSQL(String sql){
        try{
            st = c.createStatement();
            resu = st.executeQuery(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
        return resu;



    }




}
