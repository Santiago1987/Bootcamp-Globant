package Conecction;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;


public class DbConnectionTest {

    private static Connection cnx = null;
    private Statement st;

    public DbConnectionTest(){

        try {

            Class.forName("org.h2.Driver");
            cnx = DriverManager.getConnection("jdbc:h2:~/test", "test", "test");

        }catch (SQLException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    public static Connection getCnx() {
        return cnx;
    }


    public Connection getDbConnection() throws Exception {
        if(cnx == null){
            new DbConnectionTest();

        }

        return cnx;

    }


    @Test
    public void cnxTest() throws Exception {

        Connection c1 = getDbConnection();
        Connection c2 = getDbConnection();

        assertSame(c1, c2);


    }

    @After
    public void tearDown() throws Exception {
        cnx.close();

    }
}