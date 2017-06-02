package com.SRA.weather.Connection;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbConnection {
    private Connection connection = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/Weather?verifyServerCertificate=false&;useSSL=false";
    private String pwd = "AbU422696";
    private String usr= "root";

    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,usr,pwd);
        }catch (SQLException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }
}
