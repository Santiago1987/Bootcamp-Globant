package Conecction;



import Services.DataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static Connection cnx = null;
    private Statement st;
    private DataBase db = new DataBase();



    private DbConnection(){

        try {

            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/sys?verifyServerCertificate=false&useSSL=true";
            String pwd = "AbU422696";
            String usr = "root";
            Class.forName(driver);
            cnx = DriverManager.getConnection(url,usr,pwd);

            if(db.setDataBase()){
                System.out.println("The database was created successfully");
            }else{
                System.out.println("The database already exists");
            }
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








}
