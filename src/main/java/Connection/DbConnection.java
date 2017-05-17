package Connection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static Connection cnx = null;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/sys?verifyServerCertificate=false&useSSL=true";
    private static String pwd = "AbU422696";
    private static String usr = "root";

    private DbConnection(){
        try {
            Class.forName(driver);
            cnx = DriverManager.getConnection(url,usr,pwd);
        }catch (SQLException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDbConnection(){
        if(cnx == null){

            new DbConnection();
        }
        return cnx;
    }

    public static void disconect() throws Exception{
        if(cnx != null){
            cnx.close();
        }
    }

    public static void setDriver(String driver) {
        DbConnection.driver = driver;
    }

    public static void setUrl(String url) {
        DbConnection.url = url;
    }

    public static void setPwd(String pwd) {
        DbConnection.pwd = pwd;
    }

    public static void setUsr(String usr) {
        DbConnection.usr = usr;
    }
}
