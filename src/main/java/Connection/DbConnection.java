package Connection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    @Autowired
    private DataBase dataBase;
    private static Connection cnx = null;
    protected String driver = "com.mysql.jdbc.Driver";
    protected String url = "jdbc:mysql://localhost:3306/sys?verifyServerCertificate=false&useSSL=true";
    protected String pwd = "AbU422696";
    protected String usr = "root";

    protected DbConnection(){
        try {

            Class.forName(driver);
            cnx = DriverManager.getConnection(url,usr,pwd);

            dataBase.setDataBase();

            url = "jdbc:mysql://localhost:3306/Weather?verifyServerCertificate=false&useSSL=true";
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


}
